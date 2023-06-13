package cn.ecnu.model.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @intro:
 * @author: zachary
 * @version: 1.0
 */

@Data
@ApiModel(description = "大棚DTO")
public class GreenHouseDTO {
    //大棚编号
    @ApiModelProperty(value = "大棚编号")
    private Integer id;
    //大棚名称
    @NotBlank
    @ApiModelProperty(value = "大棚名称")
    private String name;
    //所在区域
    @NotNull
    @ApiModelProperty(value = "大棚所在地区")
    private Integer zoneId;
    //建造时间
    @ApiModelProperty(value = "建造时间")
    private Date buildTime;
    //使用状态（1：荒废 2：使用者）
    @ApiModelProperty(value = "使用状态")
    private Integer status;
    @ApiModelProperty(value = "是否删除")
    //是否删除（0：否 1：是）
    private Integer isDelete;
}
