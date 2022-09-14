package com.muyuan.manager.auth.controller;

import com.muyuan.common.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController()
@RequestMapping("/login")
@Api(tags = {"登录接口"})
public interface LoginController {

    @GetMapping("/captchaImage")
    @ApiOperation(value = "获取登录验证码")
    Result captchaImage(HttpServletRequest httpServletRequest) throws IOException;


    @GetMapping("/logout")
    @ApiOperation(value = "注销登录")
    Result logout();
}