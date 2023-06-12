package cn.ecnu.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

 /**
 * @intro: roleMenu表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenu {
        //主键
        private Integer id;
        //角色id
        private String roleId;
        //菜单id
        private Integer menuId;


}
