package cn.ecnu.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @intro:设备传感器关联返回值
 * @author: zachary
 * @version: 1.0
 */

@Data
@Builder
@ApiModel(description = "设备传感器关联返回值")
public class SensorVO {
    //设备编号
    @ApiModelProperty(value = "设备编号")
    private Integer id;
    //设备名称
    @ApiModelProperty(value = "设备名称")
    private String name;
    //设备类型
    @ApiModelProperty(value = "设备类型")
    private String type;
    //图片路径
    @ApiModelProperty(value = "图片路径")
    private String image;

}
