package com.muyuan.store.system.domains.dto;

import com.muyuan.common.core.constant.GlobalConst;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Schema(name = "注册信息请求提")
@Data
public class RegisterDTO {

    @NotBlank(message = "账号名不能为空")
    @Length(min = 8,max=16,message = "用戶名长度要8~16位之间")
    @Schema(name = "账号")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Schema(name = "密码")
    private String password;

    @NotBlank(message = "手机号不能为空")
    @Pattern( message = "手机号格式错误",regexp = GlobalConst.DEFAULT_PHONE_REGEX)
    @Schema(name = "手机号")
    private String phone;

    @NotBlank(message = "验证码不能为空")
    private String code;

    private String uuid;

}
