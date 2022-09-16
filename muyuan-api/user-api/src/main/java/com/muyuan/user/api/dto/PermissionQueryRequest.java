package com.muyuan.user.api.dto;

import com.muyuan.common.core.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName RolePermissionRequest
 * Description RolePermissionRequest
 * @Author 2456910384
 * @Date 2022/9/16 11:31
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionQueryRequest {

    private List<String> roleCodes;

    private UserType type;
}
