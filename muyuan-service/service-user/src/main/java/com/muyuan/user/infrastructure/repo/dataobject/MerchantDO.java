package com.muyuan.user.infrastructure.repo.dataobject;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName User
 * Description 商户用户
 * @Author 2456910384
 * @Date 2021/12/24 10:17
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class MerchantDO {

    private Long id;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 用户名 唯一 用于登录
     */
    private String username;

    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像图片
     */
    private String avatar;

    /**
     * 加密salt
     */
    private String salt;

    /**
     * 加密key
     */
    private String encryptKey;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 账号状态 0-正常 1-删除 2-锁定
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 上次登录时间
     */
    private Date lastSignTime;

    private Long updateBy;

    private Long createBy;

    private String email;

    /**
     * 性别
     */
    private String sex;

}