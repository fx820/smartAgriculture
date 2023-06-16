package cn.ecnu.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

 /**
 * @intro: environmentMonitor表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentMonitor {
        //环境监测数据编号
        private Integer id;
        //空气温度
        private Double airTemperature;
        //空气湿度
        private Double airHumidity;
        //土壤温度
        private Double soilTemperature;
        //土壤湿度
        private Double soilHumidity;
        //二氧化碳含量
        private Double c02Level;
        //光照度
        private Double lightLevel;
        //传感器编号(可能对应多个）
        private String sensorIds;
        //大棚编号
        private Integer greenHouseId;
        //创建时间
        @TableField(fill = FieldFill.INSERT)
        private Date createTime;
        //更新时间
        @TableField(fill = FieldFill.UPDATE)
        private Date updateTime;
        //是否删除（0：否 1：是）
        private Integer isDelete;


}
