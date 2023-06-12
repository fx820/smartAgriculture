package cn.ecnu.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(description = "农产品VO")
public class ProductVO {
    //农产品编号
    @ApiModelProperty(value = "农产品编号")
    private Integer id;
    //农产品名称
    @ApiModelProperty(value = "农产品名称")
    private String name;
    //种植状态（0-未种植，1-种植）
    @ApiModelProperty(value = "种植状态（0-未种植，1-种植）")
    private Integer status;
    //种植周期（单位：天）
    @ApiModelProperty(value = "种植周期（单位：天）")
    private Integer cycle;
    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //修改时间
    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    //是否删除（0：否 1：是）
    @ApiModelProperty(value = "是否删除（0：否 1：是）")
    private Integer isDelete;

    //农产品图像
    @ApiModelProperty(value = "农产品图片")
    private String photo;
}
