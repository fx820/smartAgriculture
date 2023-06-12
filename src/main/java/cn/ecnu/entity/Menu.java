package cn.ecnu.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

 /**
 * @intro: menu表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
        //主键
        private Integer id;
        //父菜单id (paren_id为0且type为M则是一级菜单)
        private Integer parentId;
        //权限类型 (M目录 C菜单 B按钮)
        private String menuType;
        //名称
        private String menuName;
        //路由地址
        private String path;
        //菜单图标
        private String icon;
        //菜单组件
        private String component;
        //权限标识
        private String perms;
        //是否隐藏 (0否 1是)
        private Integer isHidden;
        //是否禁用 (0否 1是)
        private Integer isDisable;
        //排序
        private String orderNum;
        //创建时间
        @TableField(fill = FieldFill.INSERT)
        private Date createTime;
        //更新时间
        @TableField(fill = FieldFill.UPDATE)
        private Date updateTime;


}
