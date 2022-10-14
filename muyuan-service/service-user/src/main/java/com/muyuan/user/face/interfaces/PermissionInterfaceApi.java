package com.muyuan.user.face.interfaces;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.user.api.PermissionInterface;
import com.muyuan.user.api.dto.PermissionQueryRequest;
import com.muyuan.user.domain.model.entity.Permission;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.service.PermissionDomainService;
import com.muyuan.user.domain.service.RoleDomainService;
import com.muyuan.user.face.dto.PermissionQueryCommand;
import com.muyuan.user.face.dto.mapper.PermissionMapper;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName UserInterfaceApi
 * Description 内部接口  权限
 * @Author 2456910384
 * @Date 2022/3/2 17:12
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.USER, version = "1.0"
        , interfaceClass = PermissionInterface.class
)
public class PermissionInterfaceApi implements PermissionInterface {

    private PermissionMapper PERMISSION_MAPPER;

    private PermissionDomainService permissionDomainService;

    private RoleDomainService roleDomainService;

    @Override
    public Result<Set<String>> getPermissionByUserID(PermissionQueryRequest request) {
        PermissionQueryCommand permissionQueryCommand = PERMISSION_MAPPER.toCommand(request);

        List<Role> roles = roleDomainService.selectRoleByUserId(permissionQueryCommand.getUserId(), permissionQueryCommand.getPlatformType());

        List<Permission> permissions = permissionDomainService.getPermissionByRoles(roles);

        Set<String> parms = new HashSet<>();
        for (Permission permission : permissions) {
            parms.add(permission.getPerms());
        }

        return ResultUtil.success(parms);
    }
}
