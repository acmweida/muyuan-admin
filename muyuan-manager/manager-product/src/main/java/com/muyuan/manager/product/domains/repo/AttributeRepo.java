package com.muyuan.manager.product.domains.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.BaseRepo;
import com.muyuan.manager.product.domains.dto.AttributeDTO;
import com.muyuan.manager.product.domains.model.Attribute;

import java.util.List;

/**
 * 商品分类属性对象 t_category_attribute
 *
 * @author ${author}
 * @date 2022-06-23T14:17:01.512+08:00
 */

public interface AttributeRepo extends BaseRepo {

    String CODE = "code";

    List<Attribute> select(AttributeDTO attributeDTO, Page page);

    List<Attribute> select(AttributeDTO attributeDTO);

    void insert(Attribute attribute);

    void update(Attribute attribute);

    void delete(String... ids);

    Attribute selectOne(Attribute attribute);


}
