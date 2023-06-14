package cn.ecnu.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @intro: 图像识别返回VO
 * @author: zachary
 * @version: 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "图像识别返回VO")
public class PestRecognizeVO {

    //监测结果
    @ApiModelProperty(value = "识别结果")
    private String result;

    //准确率
    @ApiModelProperty(value = "识别准确率")
    private String accuracy;
}
