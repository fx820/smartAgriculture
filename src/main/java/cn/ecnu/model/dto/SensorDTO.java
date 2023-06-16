package cn.ecnu.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @intro:
 * @author: zachary
 * @version: 1.0
 */

@Data
@ApiModel(description = "设备DTO")
public class SensorDTO {
    //设备编号
    @ApiModelProperty(value = "设备编号")
    private Integer id;
    //设备名称
    @ApiModelProperty(value = "设备名称")
    private String name;
    //设备类型
    @ApiModelProperty(value = "设备类型")
    private String type;
    //状态（1：公开 2：私密）
    @ApiModelProperty(value = "设备使用状态")
    private Integer status;
    //图片路径
    @ApiModelProperty(value = "图片路径")
    private String image;
}
