package com.muyuan.user.infrastructure.repo.impl;

import com.muyuan.user.domain.model.entity.user.Permission;
import com.muyuan.user.domain.model.valueobject.RoleId;
import com.muyuan.user.domain.repo.PermissionRepo;
import com.muyuan.user.infrastructure.repo.converter.PermissionConverter;
import com.muyuan.user.infrastructure.repo.converter.PermissionConverterImpl;
import com.muyuan.user.infrastructure.repo.dataobject.PermissionDO;
import com.muyuan.user.infrastructure.repo.mapper.PermissionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PermissionRepoImpl implements PermissionRepo {

    private PermissionMapper permissionMapper;

    private static final PermissionConverter converter = new PermissionConverterImpl();

    @Override
    public List<Permission> selectByRoles(RoleId roleId){
        List<PermissionDO> permissionDOS = permissionMapper.selectByRoleId(roleId.getValue());
        return converter.to(permissionDOS);
    }
}
