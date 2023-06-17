package cn.ecnu.model.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @intro: 种植表DTO
 * @author: zachary
 * @version: 1.0
 */

@Data
@ApiModel(value = "种植表DTO")
public class PlantDTO {

    //农作物编号
    @ApiModelProperty(value = "农作物编号")
    private Integer productId;
    //农作物收获状态 0：成长中 1：等待收割 2：完成
    @ApiModelProperty(value = "农作物收获状态 0：成长中 1：等待收割 2：完成")
    private Integer status;
    //大棚编号
    @ApiModelProperty(value = "大棚编号")
    private Integer greenHouseId;
    //种植时间
    @ApiModelProperty(value = "种植时间")
    private Date plantTime;

}
