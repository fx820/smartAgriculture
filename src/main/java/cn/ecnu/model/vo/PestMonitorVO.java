package cn.ecnu.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @intro: 虫害监测VO
 * @author: zachary
 * @version: 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "虫害监测信息")
public class PestMonitorVO {

    //虫情监测数据编号
    @ApiModelProperty(value = "虫情监测数据编号")
    private Integer id;
    //大棚编号
    @ApiModelProperty(value = "大棚编号")
    private Integer greenHouseId;
    @ApiModelProperty(value = "大棚名")
    private String greenHouseName;
    //监测图片信息
    @ApiModelProperty(value = "监测图片信息url")
    private String imageSrc;
    //监测结果
    @ApiModelProperty(value = "监测结果")
    private String result;
    //农产品编号
    @ApiModelProperty(value = "农产品编号")
    private Integer productId;
    @ApiModelProperty(value = "农作物名")
    private String productName;
    //准确率
    @ApiModelProperty(value = "准确率")
    private Double accuracy;
    //状态（1：公开 2：私密）
    @ApiModelProperty(value = "数据可见状态（1：公开 2：私密）")
    private Integer status;
    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
