package com.muyuan.member.interfaces.facade.api;

import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.member.application.service.UserService;
import com.muyuan.member.api.UserInterface;
import com.muyuan.member.interfaces.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Set;

/**
 * @ClassName UserInterfaceApi
 * Description 内部接口  用户
 * @Author 2456910384
 * @Date 2022/3/2 17:12
 * @Version 1.0
 */
@AllArgsConstructor
@Service(group = ServiceTypeConst.MEMBER_SERVICE,version = "1.0",interfaceClass = UserInterface.class)
public class UserInterfaceApi implements UserInterface {

    private UserService sysUserService;

    @Override
    public Result<UserDTO> getUserByUsername(String username) {
        UserDTO userByUsername = sysUserService.getUserByUsername(username);
        if (null == userByUsername) {
            return ResultUtil.fail("用户信息不存在");
        }

        return ResultUtil.success(userByUsername);
    }

    @Override
    public Set<String> getMenuPermissionByRoleNames(List<String> roleIds) {
        return sysUserService.getMenuPermissionByRoleNames(roleIds);
    }


}
