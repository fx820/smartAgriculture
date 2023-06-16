package cn.ecnu.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @intro:设备传感器返回值
 * @author: zachary
 * @version: 1.0
 */

@Data
@ApiModel(description = "设备传感器关联返回值")
public class SensorsVO {
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
    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    //更新时间
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    //状态（1：公开 2：私密）
    @ApiModelProperty(value = "设备使用状态")
    private Integer status;

}
