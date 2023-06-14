package cn.ecnu.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询条件
 *
 * @author zachary
 * @date 2022/12/04 21:59
 **/
@Data
@ApiModel(description = "查询条件")
public class ConditionDTO {

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private Long current;

    /**
     * 条数
     */
    @ApiModelProperty(value = "条数")
    private Long size;

    /**
     * 搜索内容
     */
    @ApiModelProperty(value = "搜索内容")
    private String keyword;

    /**
     * 是否禁用 (0否 1是)
     */
    @ApiModelProperty(value = "是否禁用 (0否 1是)")
    private Integer isDisable;

    /**
     * 传感器类型 type
     */
    @ApiModelProperty(value = "传感器类型")
    private String type;

    /**
     * 农产品状态（0未种植 1种植）
     * 通用状态（1公开 2私密）
     */
    private Integer status;

    /**
     * 地区编号
     */
    private Integer zoneId;

    /**
     * 大棚编号
     */

    private Integer greenHouseId;

    /**
     * 农作物编号
     */
    private Integer productId;

    /**
     * 登录类型
     */
    @ApiModelProperty(value = "登录类型")
    private Integer loginType;

    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    /**
     * 操作模块
     */
    @ApiModelProperty(value = "操作模块")
    private String optModule;

    /**
     * 是否删除 (0否 1是)
     */
    @ApiModelProperty(value = "是否删除 (0否 1是)")
    private Integer isDelete;

    /**
     * 是否通过 (0否 1是)
     */
    @ApiModelProperty(value = "是否通过 (0否 1是)")
    private Integer isCheck;


    /**
     * 任务组名
     */
    @ApiModelProperty(value = "任务组名")
    private String taskGroup;

    /**
     * 调用目标
     */
    @ApiModelProperty(value = "调用目标")
    private String invokeTarget;

    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    private String filePath;

}
