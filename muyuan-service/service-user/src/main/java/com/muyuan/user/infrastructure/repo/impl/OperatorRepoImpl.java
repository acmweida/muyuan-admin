package com.muyuan.user.infrastructure.repo.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.user.domain.model.entity.Operator;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.model.valueobject.Username;
import com.muyuan.user.domain.repo.OperatorRepo;
import com.muyuan.user.face.dto.UserQueryCommand;
import com.muyuan.user.infrastructure.repo.converter.UserConverter;
import com.muyuan.user.infrastructure.repo.dataobject.OperatorDO;
import com.muyuan.user.infrastructure.repo.dataobject.RoleDO;
import com.muyuan.user.infrastructure.repo.dataobject.UserRoleDO;
import com.muyuan.user.infrastructure.repo.mapper.OperatorMapper;
import com.muyuan.user.infrastructure.repo.mapper.RoleMapper;
import com.muyuan.user.infrastructure.repo.mapper.UserRoleMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.muyuan.common.mybatis.jdbc.JdbcBaseMapper.*;
import static com.muyuan.user.infrastructure.repo.mapper.OperatorMapper.PHONE;
import static com.muyuan.user.infrastructure.repo.mapper.OperatorMapper.USERNAME;


/**
 * @ClassName OperatorRepoImpl
 * Description
 * @Author 2456910384
 * @Date 2022/9/14 10:28
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class OperatorRepoImpl implements OperatorRepo {

    private UserConverter converter;

    private OperatorMapper mapper;
    
    private RoleMapper roleMapper;

    private UserRoleMapper userRoleMapper;

    @Override
    public Page<Operator> select(UserQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(OperatorDO.class)
                .like(USERNAME, command.getUsername())
                .eq(STATUS, command.getStatus())
                .eq(PHONE, command.getPhone())
                .orderByDesc(CREATE_TIME);

        Page<Operator> page = Page.<Operator>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<OperatorDO> list = mapper.selectList(sqlBuilder.build());

        page.setRows(converter.toUsers(list));

        return page;
    }

    @Override
    public Operator selectOneByUsername(Username username, PlatformType platformType) {
        OperatorDO operatorDO = mapper.selectOne(new SqlBuilder(OperatorDO.class)
                .eq(OperatorMapper.USERNAME, username.getValue())
                .eq(OperatorMapper.STATUS, OperatorMapper.STATUS_OK)
                .build());
        Operator operator = converter.to(operatorDO);
        if (null != operatorDO) {
            List<RoleDO> roleDOS = roleMapper.selectRoleByUserId(operatorDO.getId(),platformType.getCode());
            operator.setRoles(converter.toRoles(roleDOS));
        }

        return operator;
    }

    @Override
    public Operator selectOneByID(UserID userID, PlatformType platformType) {
        OperatorDO operatorDO = mapper.selectOne(new SqlBuilder(OperatorDO.class)
                .eq(OperatorMapper.ID, userID.getValue())
                .eq(OperatorMapper.STATUS, OperatorMapper.STATUS_OK)
                .build());
        Operator operator = converter.to(operatorDO);
        if (null != operatorDO) {
            List<RoleDO> roleDOS = roleMapper.selectRoleByUserId(operatorDO.getId(),platformType.getCode());
            operator.setRoles(converter.toRoles(roleDOS));
        }

        return operator;
    }

    @Override
    public Operator selectOperator(Operator.Identify identify) {
        OperatorDO operatorDO = mapper.selectOne(new SqlBuilder(OperatorDO.class).select(ID)
                .eq(USERNAME, identify.getUsername().getValue())
                .eq(ID, ObjectUtils.isEmpty(identify.getUserID()) ? identify.getUserID() : identify.getUserID().getValue())
                .build());

        return converter.to(operatorDO);
    }

    @Override
    public void insert(Operator operator) {
        OperatorDO to = converter.to(operator);
        mapper.insert(to);
        operator.setId(new UserID(to.getId()));
    }

    @Override
    public boolean insertRef(UserID userID, RoleID... roleIds) {
        if (ObjectUtils.isEmpty(roleIds)) {
            return true;
        }
        List<UserRoleDO> ref = new ArrayList<>();
        for (RoleID roleId : roleIds) {
            ref.add(UserRoleDO.builder()
                    .roleId(roleId.getValue())
                    .userId(userID.getValue())
                    .build());
        }

        return userRoleMapper.batchInsert(ref) > 0;
    }

    @Override
    public void deleteRef(UserID userID) {
        userRoleMapper.deleteBy(new SqlBuilder()
                .eq(USER_ID,userID.getValue())
                .build()) ;
    }

    @Override
    public Page<Operator> selectAllocatedList(UserQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(OperatorDO.class)
                .like(USERNAME, command.getUsername())
                .eq(PHONE, command.getPhone());

        Page<Operator> page = Page.<Operator>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<OperatorDO> list = mapper.selectAllocatedList(command.getRoleId(),sqlBuilder.build());

        page.setRows(converter.toUsers(list));

        return page;
    }

    @Override
    public Page<Operator> selectUnallocatedList(UserQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(OperatorDO.class)
                .like(USERNAME, command.getUsername())
                .eq(PHONE, command.getPhone());

        Page<Operator> page = Page.<Operator>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<OperatorDO> list = mapper.selectUnallocatedList(command.getRoleId(),sqlBuilder.build());

        page.setRows(converter.toUsers(list));

        return page;
    }
}