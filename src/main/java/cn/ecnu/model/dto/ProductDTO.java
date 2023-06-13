package cn.ecnu.model.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @intro:
 * @author: zachary
 * @version: 1.0
 */

@Data
@ApiModel(description = "农产品DTO")
public class ProductDTO {
    //农产品编号
    @ApiModelProperty(value = "农产品编号")
    private Integer id;

    //农产品名称
    @NotBlank(message = "农产品名不能为空")
    @ApiModelProperty(value = "农产品名称")
    private String name;

    //种植状态（0-未种植，1-种植）
    @ApiModelProperty(value = "种植状态（0-未种植，1-种植）")
    private Integer status;

    //种植周期（单位：天）
    @NotNull(message = "种植周期不能为空")
    @ApiModelProperty(value = "种植周期（单位：天）")
    private Integer cycle;

    @ApiModelProperty(value = "农产品图片")
    private String photo;
}
