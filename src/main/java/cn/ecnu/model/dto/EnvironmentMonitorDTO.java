package cn.ecnu.model.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* @intro: environmentMonitor表实体类
* @author: zachary
* @version: 1.0
*/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentMonitorDTO {
       //空气温度
       private Double airTemperature;
       //空气湿度
       private Integer airHumidity;
       //土壤温度
       private Double soilTemperature;
       //土壤湿度
       private Integer soilHumidity;
       //二氧化碳含量
       private Integer c02Level;
       //光照度
       private Integer lightLevel;
       //大棚编号
       private Integer greenHouseId;
       //创建时间
       private Date createTime;


}
