package cn.ecnu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录方式枚举
 *
 * @author zachary
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {

    /**
     * UserName
     */
    USER_NAME(1, "用户名密码登录", "userNameLoginStrategyImpl"),

    /**
     * 邮箱
     */
    EMAIL(2, "邮箱登录", ""),


    /**
     * Phone
     */
    PHONE(3, "手机号登录", "phoneLoginStrategyImpl");

    /**
     * 登录方式
     */
    private final Integer loginType;

    /**
     * 描述
     */
    private final String description;

    /**
     * 策略
     */
    private final String strategy;
}