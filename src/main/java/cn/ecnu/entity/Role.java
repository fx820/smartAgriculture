package cn.ecnu.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

 /**
 * @intro: role表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
        //主键id
        private String id;
        //角色名称
        private String roleName;
        //角色描述
        private String roleDescription;
        //是否禁用 (0否 1是)
        private Integer isDisable;
        //创建时间
        @TableField(fill = FieldFill.INSERT)
        private Date createTime;
        //更新时间
        @TableField(fill = FieldFill.UPDATE)
        private Date updateTime;


}
