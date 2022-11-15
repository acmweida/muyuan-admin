package com.muyuan.user.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName RoleDO
 * Description TODO
 * @Author 2456910384
 * @Date 2022/9/15 14:16
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = 1457932148502l;

    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    private String orderNum;

    /**
     * 状态 0-正常 1-停用
     */
    private String status;

    private Long createBy;

    private Long updateBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public RoleDTO(String code) {
        this.code = code;
    }

    public RoleDTO(Long id, String code) {
        this.id = id;
        this.code = code;
    }
}