package cn.ecnu.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @intro: 大棚信息监测信息VO
 * @author: zachary
 * @version: 1.0
 */

@Data
public class EnvironmentMonitorVO {
    //环境监测数据编号
    @ApiModelProperty(value = "环境监测数据编号")
    private Integer id;
    //空气温度
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
    //大棚编号
    @ApiModelProperty(value = "大棚编号")
    private Integer greenHouseId;
    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    //更新时间
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    //大棚名
    @ApiModelProperty(value = "大棚名称")
    private String name;
}
