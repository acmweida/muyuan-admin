package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.manager.system.dto.DictTypeDTO;
import com.muyuan.manager.system.domains.factories.DictTypeFactory;
import com.muyuan.manager.system.domains.model.DictType;
import com.muyuan.manager.system.domains.repo.DictTypeRepo;
import com.muyuan.manager.system.service.DictTypeDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName DictTypeControlerImpl
 * Description 指点类型
 * @Author 2456910384
 * @Date 2022/3/31 11:43
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class DictTypeDomainServiceImpl implements DictTypeDomainService {

    private DictTypeRepo dictTypeRepo;

    // ##############################  query ########################## //

    @Override
    public String checkUnique(DictType dictType) {
        Long id = null == dictType.getId() ? 0 : dictType.getId();
        dictType = dictTypeRepo.selectOne(new SqlBuilder(DictType.class).select("id")
                .eq("name", dictType.getName())
                .eq("type", dictType.getType())
                .build());
        if (null != dictType && !id.equals(dictType.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    /**
     * 通过DataType 查询字典数据
     *
     * @param dictTypeDTO
     * @return
     */
    @Override
    public Page<DictType> list(DictTypeDTO dictTypeDTO) {

        SqlBuilder sqlBuilder = new SqlBuilder(DictType.class)
                .eq("name", dictTypeDTO.getName())
                .eq("type", dictTypeDTO.getType())
                .eq("status", dictTypeDTO.getStatus())
                .orderByDesc("updateTime", "createTime");

        Page<DictType> page = Page.<DictType>builder().pageSize(dictTypeDTO.getPageSize())
                .pageNum(dictTypeDTO.getPageNum()).build();
        sqlBuilder.page(page);

        List<DictType> list = dictTypeRepo.select(sqlBuilder.build());

        page.setRows(list);

        return page;
    }

    @Override
    public List<DictType> selectDictTypeAll() {
        return dictTypeRepo.select(new SqlBuilder(DictType.class).build());
    }

    /**
     * 字典类类型详情查询
     *
     * @param id
     * @return
     */
    @Override
    public Optional<DictType> getById(String id) {
        DictType dictType = dictTypeRepo.selectOne(new SqlBuilder(DictType.class)
                .eq("id", id)
                .build());

        if (null == dictType) {
            return Optional.empty();
        }

        return Optional.of(dictType);

    }

    // ##############################  query ########################## //

    @Override
    public int add(DictTypeDTO dictTypeDTO) {
        DictType dictType = DictTypeFactory.newInstance(dictTypeDTO);
        return dictTypeRepo.insert(dictType);
    }


}