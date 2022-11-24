package com.muyuan.manager.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName SysRoleDTO
 * Description 系统角色
 * @Author 2456910384
 * @Date 2022/4/26 16:51
 * @Version 1.0
 */
@ApiModel("角色")
@Data
public class RoleParams {

    /**
     * 分组
     */
    public interface Add {

    }

    public interface Update {

    }

    @NotNull(groups = Update.class)
    @ApiModelProperty(value = "角色ID")
    private Long id;

    @NotBlank(message = "角色名称不能为空",groups = {Add.class,Update.class})
    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "状态 默认 0-启用")
    private Integer status = 0;

    @NotNull(message = "角色编码不能为空",groups = {Add.class,Update.class})
    @ApiModelProperty(value = "角色编码")
    private String code;

    @NotNull(message = "平台类型不能为空",groups = {Add.class,Update.class})
    @ApiModelProperty(value = "平台类型")
    private Integer platformType;

    @ApiModelProperty(value = "权限ID数组")
    private Long[] permissionIds;

    @ApiModelProperty(value = "菜单ID数组")
    private Long[] menuIds;

    @ApiModelProperty(value = "排序 默认 -0")
    private Integer orderNum = 0;


}
