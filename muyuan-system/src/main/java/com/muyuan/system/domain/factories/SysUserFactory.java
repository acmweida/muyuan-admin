package com.muyuan.system.domain.factories;

import com.muyuan.system.domain.entity.SysUserEntity;
import com.muyuan.system.domain.repo.SysUserRepo;
import com.muyuan.system.interfaces.dto.RegisterDTO;

public class SysUserFactory {

    /**
     *  构建一个新用户实体 并初始化
     * @return
     */
    public static SysUserEntity newSysUserEntity(RegisterDTO registerDTO, SysUserRepo sysUserRepo)  {
        SysUserEntity sysUserEntity = new SysUserEntity(registerDTO.getUsername(), registerDTO.getPassword());
        sysUserEntity.initInstance();;
        sysUserEntity.setSysUserRepo(sysUserRepo);
        return sysUserEntity;
    }
}