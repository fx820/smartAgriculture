package cn.ecnu.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 大棚数据监测
 *
 * @author zachary
 **/
@Data
@ApiModel(description = "大棚数据监测")
public class EnvironmentViewVO {

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private Date createTime;

    @ApiModelProperty(value = "空气温度")
    private Double airTemperature;
    //空气湿度
    @ApiModelProperty(value = "空气湿度")
    private Integer airHumidity;
    //土壤温度
    @ApiModelProperty(value = "土壤温度")
    private Double soilTemperature;
    //土壤湿度
    @ApiModelProperty(value = "土壤湿度")
    private Integer soilHumidity;
    //二氧化碳含量
    @ApiModelProperty(value = "二氧化碳含量")
    private Integer c02Level;
    //光照度
    @ApiModelProperty(value = "光照度")
    private Integer lightLevel;
}