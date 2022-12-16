package com.muyuan.manager.goods.base.persistence.mapper;

import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.manager.goods.model.AttributeValue;
import com.muyuan.common.mybatis.jdbc.GoodsBaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 商品分类属性Mapper接口
 * 
 * @author ${author}
 * @date 2022-06-23T14:17:01.512+08:00
 */
@Mapper
public interface AttributeValueMapper extends GoodsBaseMapper<AttributeValue> {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class, method = "insert")
    Integer insertAuto(AttributeValue dataObject);

}