package cn.ecnu.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.ecnu.model.dto.EmailDTO;
import cn.ecnu.model.dto.LoginDTO;
import cn.ecnu.model.dto.PhoneLoginDTO;
import cn.ecnu.model.dto.RegisterDTO;
import cn.ecnu.model.vo.Result;
import cn.ecnu.annotation.AccessLimit;
import cn.ecnu.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 *
 * @author zachary
 */
@Api(tags = "登录模块")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户登录
     *
     * @param login 登录参数
     * @return {@link String} Token
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result<String> login(@Validated @RequestBody LoginDTO login) {
        return Result.success(loginService.login(login));
    }

    /**
     * 用户退出
     */
    @SaCheckLogin
    @ApiOperation(value = "用户退出")
    @GetMapping("/logout")
    public Result<?> logout() {
        StpUtil.logout();
        return Result.success();
    }

    /**
     * 发送邮箱验证码
     *
     * @return {@link Result<>}
     */
    @AccessLimit(seconds = 60, maxCount = 1)
    @ApiOperation(value = "发送邮箱验证码")
    @GetMapping("/code")
    public Result<?> sendCode(String username) {
        loginService.sendCode(username);
        return Result.success();
    }

    /**
     * 用户邮箱注册
     *
     * @param register 注册信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "用户邮箱注册")
    @PostMapping("/register")
    public Result<?> register(@Validated @RequestBody RegisterDTO register) {
        loginService.register(register);
        return Result.success();
    }

    /**
     * 手机号验证码登录
     *
     * @param data 手机号验证码数据
     * @return {@link Result<String>} Token
     */
    @ApiOperation(value = "手机号验证码登录")
    @PostMapping("/oauth/phone")
    public Result<String> phoneLogin(@RequestBody PhoneLoginDTO data) {
        return Result.success(loginService.phoneLogin(data));
    }

    /**
     * 发送邮箱验证码
     *
     * @return {@link Result<>}
     */
    @AccessLimit(seconds = 60, maxCount = 1)
    @ApiOperation(value = "发送短信验证码")
    @GetMapping("/phone/code")
    public Result<?> sendSmsCode(String phone) {
        loginService.sendSmsCode(phone);
        return Result.success();
    }


    /**
     * 手机号验证码登录
     *
     * @param data 手机号验证码数据
     * @return {@link Result<String>} Token
     */
    @ApiOperation(value = "邮箱验证码登录")
    @PostMapping("/oauth/email")
    public Result<String> emailLogin(@RequestBody EmailDTO data) {
        return Result.success(loginService.emailLogin(data));
    }

}

