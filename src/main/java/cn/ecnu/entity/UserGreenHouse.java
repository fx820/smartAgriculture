package cn.ecnu.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

 /**
 * @intro: userGreenHouse表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGreenHouse {
        //用户大棚关联表编号
        private Integer id;
        //用户编号
        private Integer userId;
        //大棚编号
        private Integer greenHouseId;


}
