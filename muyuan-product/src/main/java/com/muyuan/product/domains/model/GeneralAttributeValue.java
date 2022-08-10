package com.muyuan.product.domains.model;

import lombok.Data;

/**
 * @ClassName GeneralAttribute
 * Description 通用类目属性值
 * @Author 2456910384
 * @Date 2022/8/10 11:20
 * @Version 1.0
 */
@Data
public class GeneralAttributeValue {

    private Long id;

    private Long attributeId;

    private String value;

    private Integer status;
}