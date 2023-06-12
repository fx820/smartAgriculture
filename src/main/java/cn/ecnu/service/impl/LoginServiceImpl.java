package cn.ecnu.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.ecnu.entity.User;
import cn.ecnu.mapper.UserMapper;
import cn.ecnu.model.dto.*;
import cn.ecnu.utils.SendSmsUtils;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import cn.ecnu.entity.SiteConfig;
import cn.ecnu.entity.UserRole;
import cn.ecnu.mapper.UserRoleMapper;
import cn.ecnu.service.LoginService;
import cn.ecnu.service.RedisService;
import cn.ecnu.utils.SecurityUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static cn.ecnu.constant.CommonConstant.*;
import static cn.ecnu.constant.MqConstant.EMAIL_EXCHANGE;
import static cn.ecnu.constant.MqConstant.EMAIL_SIMPLE_KEY;
import static cn.ecnu.constant.RedisConstant.*;
import static cn.ecnu.constant.SmsConstant.CODE_EXPIRE_TIME;
import static cn.ecnu.enums.LoginTypeEnum.EMAIL;
import static cn.ecnu.enums.RoleEnum.USER;
import static cn.ecnu.utils.CommonUtils.checkEmail;
import static cn.ecnu.utils.CommonUtils.checkPhone;

/**
 * 登录业务接口实现类
 *
 * @author zachary
 * @date 2022/12/04 11:24
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisService redisService;

    @Override
    public String login(LoginDTO login) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getId)
                .eq(User::getUserName, login.getUsername())
                .eq(User::getPassword, SecurityUtils.sha256Encrypt(login.getPassword())));
        Assert.notNull(user, "用户不存在或密码错误");
        // 校验指定账号是否已被封禁，如果被封禁则抛出异常 `DisableServiceException`
        StpUtil.checkDisable(user.getId());
        // 通过校验后，再进行登录
        StpUtil.login(user.getId());
        return StpUtil.getTokenValue();
    }

    @Override
    public void sendCode(String username) {
        Assert.isTrue(checkEmail(username), "请输入正确的邮箱！");
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 6);
        String code = randomGenerator.generate();
        MailDTO mailDTO = MailDTO.builder()
                .toEmail(username)
                .subject(CAPTCHA)
                .content("您的验证码为 " + code + " 有效期为" + CODE_EXPIRE_TIME + "分钟")
                .build();
        // 验证码存入消息队列
        rabbitTemplate.convertAndSend(EMAIL_EXCHANGE, EMAIL_SIMPLE_KEY, mailDTO);
        // 验证码存入redis
        redisService.setObject(CODE_KEY + username, code, CODE_EXPIRE_TIME, TimeUnit.MINUTES);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(RegisterDTO register) {
        //验证验证码
        verifyCode(register.getUsername(), register.getCode());
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getUserName)
                .eq(User::getUserName, register.getUsername()));
        Assert.isNull(user, "邮箱已注册！");
        SiteConfig siteConfig = redisService.getObject(SITE_SETTING);
        // 添加用户
        User newUser = User.builder()
                .userName(register.getUsername())
                .email(register.getUsername())
                .nickName(USER_NICKNAME + IdWorker.getId())
                //设置默认头像
                .avatar(siteConfig.getUserAvatar())
                .password(SecurityUtils.sha256Encrypt(register.getPassword()))
                .loginType(EMAIL.getLoginType())
                .isDisable(FALSE)
                .build();
        userMapper.insert(newUser);
        // 绑定用户角色
        UserRole userRole = UserRole.builder()
                .userId(newUser.getId())
                //默认普通用户角色
                .roleId(USER.getRoleId())
                .build();
        userRoleMapper.insert(userRole);
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     */
    public void verifyCode(String username, String code) {
        String sysCode = redisService.getObject(CODE_KEY + username);
        Assert.notBlank(sysCode, "验证码未发送或已过期！");
        Assert.isTrue(sysCode.equals(code), "验证码错误，请重新输入！");
    }


    @Override
    public String phoneLogin(PhoneLoginDTO login) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getId)
                .eq(User::getPhone, login.getPhone())
        );
        Assert.notNull(user,"手机号未注册！");
        //根据已存在的手机号查询redis中的验证码
        String code = redisService.getObject(CODE_KEY + login.getPhone());
        Assert.notBlank(code, "验证码未发送或已过期！");
        Assert.isTrue(code.equals(login.getCode()),"验证码错误！");
        //校验指定账号是否已被封禁，如果被封禁则抛出异常 ’DisableServiceException'
        StpUtil.checkDisable(user.getId());
        //通过验证后，再进行登录
        StpUtil.login(user.getId());
        return StpUtil.getTokenValue();
    }

    @Override
    public void sendSmsCode(String phone) {
        Assert.isTrue(checkPhone(phone), "请输入正确的手机号！");
        //生成验证码
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 6);
        String code = randomGenerator.generate();
        //发送验证码
        SendSmsUtils.send(phone, code);
        //验证码存入redis
        redisService.setObject(CODE_KEY + phone, code, CODE_EXPIRE_TIME, TimeUnit.MINUTES);
    }

    @Override
    public String emailLogin(EmailDTO login) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getId)
                .eq(User::getEmail, login.getEmail())
        );
        Assert.notNull(user,"该邮箱不存在，请注册或绑定邮箱!");
        String code = redisService.getObject(CODE_KEY + login.getEmail());
        Assert.notBlank(code, "验证码未发送或已过期！");
        Assert.isTrue(code.equals(login.getCode()),"验证码错误!");
        //对指定账号进行校验
        StpUtil.checkDisable(user.getId());
        //通过校验后 登录
        StpUtil.login(user.getId());
        return StpUtil.getTokenValue();
    }


}