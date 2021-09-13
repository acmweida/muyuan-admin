package com.muyuan.member.domain.vo;

import lombok.Data;

@Data
public class UserVO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户编号
     */
    private long userNo;

    /**
     * 账号名唯一用于登录
     */
    private String account;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 是否删除（0-否 1-是）
     */
    private boolean isDelete;

    /**
     * 账号状态 0-正常 1-锁定
     */
    private short status;

}