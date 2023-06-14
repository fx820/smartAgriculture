package cn.ecnu.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @intro: 虫害信息DTO
 * @author: zachary
 * @version: 1.0
 */

@Data
@ApiModel(description = "虫害DTO")
public class PestMonitorDTO {
    //虫情监测数据编号
    @ApiModelProperty(value = "虫情监测数据编号")
    private Integer id;
    //大棚编号
    @ApiModelProperty(value = "大棚编号")
    private Integer greenHouseId;
    //监测图片信息
    @ApiModelProperty(value = "监测图片信息url")
    private String imageSrc;
    //监测结果
    @ApiModelProperty(value = "监测结果")
    private String result;
    //农产品编号
    @ApiModelProperty(value = "农产品编号")
    private Integer productId;
    //准确率
    @ApiModelProperty(value = "准确率")
    private String accuracy;
    //状态（1：公开 2：私密）
    @ApiModelProperty(value = "数据可见状态（1：公开 2：私密）")
    private Integer status;
    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
