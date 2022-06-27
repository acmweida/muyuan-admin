package com.muyuan.product.domains.dto;

import com.muyuan.common.core.bean.BaseDTO;
import com.muyuan.product.domains.model.CategoryAttribute;
import lombok.Data;

import java.util.Date;


/**
 * 商品分类属性对象 t_category_attribute
 *
 * @author ${author}
 * @date 2022-06-23T14:17:01.512+08:00
 */
@Data
public class CategoryAttributeDTO extends BaseDTO<CategoryAttributeDTO,CategoryAttribute> {

    /**  */
    private Long id;

    /** 属性名称 */
    private String name;

    /** 商品分类ID */
    private Long categoryId;

    /** 属性类型 1:关键属性 2:销售属性 3:非关键属性 */
    private Long type;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

    /**  */
    private Long createBy;

    /**  */
    private String creator;

    /**  */
    private Long updateBy;

    /**  */
    private String updater;


}