package cn.ecnu.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @intro:Phone信息
 * @author: zachary
 * @version: 1.0
 */

@Data
@ApiModel(description = "phone信息")
public class PhoneDTO {

    /**
     * code
     */
    @NotBlank(message = "code不能为空")
    @ApiModelProperty(value = "code")
    private String code;
}
