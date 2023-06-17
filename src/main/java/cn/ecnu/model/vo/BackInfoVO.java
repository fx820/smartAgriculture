package cn.ecnu.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 后台信息VO
 *
 * @author zachary
 **/
@Data
@Builder
@ApiModel(description = "网站后台信息VO")
public class BackInfoVO {

    /**
     * 农作物数量
     */
    @ApiModelProperty(value = "农作物数量")
    private Long productCount;

    /**
     * 大棚数量
     */
    @ApiModelProperty(value = "大棚数量")
    private Long houseCount;

    /**
     * 用户量
     */
    @ApiModelProperty(value = "用户量")
    private Long userCount;

    /**
     * 文章量
     */
    @ApiModelProperty(value = "区域数量")
    private Long zoneCount;



    /**
     * 温度湿度信息
     */
    @ApiModelProperty(value = "温度湿度信息")
    private List<EnvironmentViewVO> EnvironmentViewVOList;
}