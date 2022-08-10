package com.muyuan.product.domains.service.impl;

import com.muyuan.common.core.bean.SelectTree;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.exception.MuyuanException;
import com.muyuan.product.domains.assembler.CategoryAssembler;
import com.muyuan.product.domains.dto.AttributeDTO;
import com.muyuan.product.domains.dto.CategoryDTO;
import com.muyuan.product.domains.model.BrandCategory;
import com.muyuan.product.domains.model.Attribute;
import com.muyuan.product.domains.model.Category;
import com.muyuan.product.domains.repo.AttributeRepo;
import com.muyuan.product.domains.repo.CategoryRepo;
import com.muyuan.product.domains.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName ProductCategoryDomainServiceImpl
 * Description 商品分类
 * @Author 2456910384
 * @Date 2022/6/10 8:57
 * @Version 1.0
 */
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    public CategoryServiceImpl(CategoryRepo categoryRepo, AttributeRepo attributeRepo) {
        this.categoryRepo = categoryRepo;
        this.attributeRepo = attributeRepo;
    }

    protected CategoryRepo categoryRepo;

    protected AttributeRepo attributeRepo;

    @Override
    public List<Category> list(CategoryDTO categoryDTO) {
        return categoryRepo.list(categoryDTO);
    }

    @Override
    public List<SelectTree> treeSelect(Long parentId,Integer level) {
        List<Category> list = list(CategoryDTO.builder()
                .parentId(parentId)
                .level(level)
                .build());
        return CategoryAssembler.buildSelectTree(list);
    }

    @Override
    @Transactional
    public void add(CategoryDTO categoryDTO) {
        Category category = categoryDTO.convert();
        category.init();
        if (ObjectUtils.isNotEmpty(category.getParentId())) {
            Category parent = categoryRepo.selectOne(
                    Category.builder().id(categoryDTO.getParentId()).build());
            if (ObjectUtils.isEmpty(parent)) {
                addRootCategory(category);
            } else {
                addSubNodeCategory(category, parent);
            }
        } else {
            addRootCategory(category);
        }
    }

    private void addRootCategory(Category category) {
        int rootCount = categoryRepo.countAll(CategoryDTO.builder().level(1).build());
        category.initRoot(rootCount);
        category.save(categoryRepo);
        category.setAncestors(String.valueOf(category.getId()));
        category.update(categoryRepo, CategoryRepo.ANCESTORS);
    }

    private void addSubNodeCategory(Category category, Category parent) {
        category.save(categoryRepo);
        // 查询兄弟节点数量 用于生成Code
        int count = categoryRepo.countAll(CategoryDTO.builder()
                .level(parent.getLevel() + 1)
                .parentId(parent.getId())
                .build());
        category.linkParent(parent, count);
        category.save(categoryRepo);
        parent.save(categoryRepo);
    }


    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = categoryDTO.convert();
        category.save(categoryRepo);
    }

    @Override
    public String checkUnique(Category category) {
        Long id = null == category.getId() ? 0 : category.getId();
        category = categoryRepo.selectOne(
                Category.builder().name(category.getName())
                        .parentId(category.getParentId()).build());
        if (null != category && !id.equals(category.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    public Optional<Category> get(Category category) {
        Category goodsCategory = categoryRepo.selectOne(category);
        return Optional.ofNullable(goodsCategory);
    }

    @Override
    public Optional<Category> detail(Category goodsCategory) {

        Category category = categoryRepo.selectOne(Category.builder()
                .code(goodsCategory.getCode())
                .build());

        if (ObjectUtils.isEmpty(category)) {
            return Optional.empty();
        }

        List<Attribute> attributes = attributeRepo.select(AttributeDTO.builder()
                .categoryCode(goodsCategory.getCode())
                .build());

        category.setAttributes(attributes);

        return Optional.of(category);
    }

    @Override
    public void delete(Long[] ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return;
        }
        List<Long> toDelete = new ArrayList<>(Arrays.asList(ids));

        Long[] parentIds = ids;
        List<Category> sub;

        List<Category> list = categoryRepo.list(CategoryDTO.builder().ids(ids).build());
        List<Long> codes = list.stream().map(Category::getCode).collect(Collectors.toList());
        do {
            sub = categoryRepo.list(CategoryDTO.builder().parentIds(parentIds).build());
            if (!sub.isEmpty()) {
                toDelete.addAll(sub.stream().map(Category::getId).collect(Collectors.toList()));
                codes.addAll(sub.stream().map(Category::getCode).collect(Collectors.toList()));
            }
        } while (!sub.isEmpty());

        List<BrandCategory> brandCategories = categoryRepo.selectBrand(codes.toArray(new Long[0]));
        if (ObjectUtils.isNotEmpty(brandCategories)) {
            throw new MuyuanException(ResponseCode.FAIL.getCode(), "有品牌关联当前分类");
        }

        categoryRepo.delete(toDelete.toArray(new Long[0]));
    }


}