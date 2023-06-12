package cn.ecnu.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

 /**
 * @intro: userRole表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
        //主键
        private Integer id;
        //用户id
        private Integer userId;
        //角色id
        private String roleId;


}
