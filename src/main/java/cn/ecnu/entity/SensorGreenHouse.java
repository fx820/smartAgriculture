package cn.ecnu.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

 /**
 * @intro: sensorGreenHouse表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorGreenHouse {
        //关联表编号
        private Integer id;
        //传感器编号
        private Integer sensorId;
        //大棚编号
        private Integer greenHouseId;


}
