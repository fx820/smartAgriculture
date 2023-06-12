package cn.ecnu.service;

import cn.ecnu.model.dto.EmailDTO;
import cn.ecnu.model.dto.LoginDTO;
import cn.ecnu.model.dto.PhoneLoginDTO;
import cn.ecnu.model.dto.RegisterDTO;

/**
 * 登录业务接口
 *
 * @author zachary
 * @date 2022/12/04 11:16
 **/
public interface LoginService {

    /**
     * 用户登录
     *
     * @param login 登录参数
     * @return token
     */
    String login(LoginDTO login);

    /**
     * 发送验证码
     *
     * @param username 用户名
     */
    void sendCode(String username);

    /**
     * 用户注册
     *
     * @param register 注册信息
     */
    void register(RegisterDTO register);

    /*
     * @intro: 手机号验证码登录
     * @author: zachary
     * @param: login
     * @return: String
     **/
    String phoneLogin(PhoneLoginDTO login);

    /*
     * @intro:发送短信验证码
     * @author: zachary
     * @param: phone
     * @return: void
     **/
    void sendSmsCode(String phone);

    /*
     * @intro: 邮箱验证码登录
     * @author: zachary
     * @param: login
     * @return: String
     **/
    String emailLogin(EmailDTO login);

}
