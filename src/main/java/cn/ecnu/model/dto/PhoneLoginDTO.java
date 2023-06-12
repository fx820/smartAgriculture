package cn.ecnu.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @intro:手机号验证码登录信息
 * @author: zachary
 * @version: 1.0
 */

@Data
@ApiModel(description = "手机号验证码登录信息")
public class PhoneLoginDTO {

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 用户密码
     */
    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码")
    private String code;
}
