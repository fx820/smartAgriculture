package cn.ecnu.model.vo;

import cn.ecnu.entity.Sensor;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @intro: 大棚信息显示
 * @author: zachary
 * @version: 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "大棚VO")
public class GreenHouseVO {

    //大棚编号
    @ApiModelProperty(value = "大棚编号")
    private Integer id;
    @ApiModelProperty(value = "大棚名称")
    private String name;
    //所在区域
    @ApiModelProperty(value = "所在地区编号")
    private Integer zoneId;
    @ApiModelProperty(value = "所在地区名称")
    private String zoneName;
    //建造时间
    @ApiModelProperty(value = "大棚建造时间")
    private Date buildTime;
    //大棚使用状态（1：荒废 2：使用中）
    @ApiModelProperty(value = "大棚使用状态")
    private Integer status;
    //创建时间
    @ApiModelProperty(value = "数据创建时间")
    private Date createTime;
    //更新时间
    @ApiModelProperty(value = "上一次更新时间")
    private Date updateTime;
    
}
