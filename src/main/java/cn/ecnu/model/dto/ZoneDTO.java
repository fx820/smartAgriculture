package cn.ecnu.model.dto;

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
* @intro: zone表实体类
* @author: zachary
* @version: 1.0
*/

@Data
@ApiModel(description = "区域DTO")
public class ZoneDTO {
       //区域编号
       @ApiModelProperty(value = "区域编号")
       private Integer id;
       //区域名称
       @ApiModelProperty(value = "区域名")
       private String name;
       //区域地址
       @ApiModelProperty(value = "区域地址")
       private String address;
       //状态（1：不可用 2：可用）
       @ApiModelProperty(value = "区域是否可用")
       private Integer status;


}
