package cn.ecnu.entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

 /**
 * @intro: user表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
        //用户编号
        private Integer id;
        //用户名
        private String userName;
        //用户昵称
        private String nickName;
        //用户密码
        private String password;
        //用户邮箱
        private String email;
        //联系方式
        private String phone;
        //用户地址
        private String address;
        //头像
        private String avatar;
        //登录ip
        private String ipAddress;
        //登录地址
        private String ipSource;
        //登录方式（1：用户名密码 2：邮箱  3：手机号验证码)
        private Integer loginType;
        //是否禁用（0：否 1：是）
        private Integer isDisable;
        //登录时间
        private LocalDateTime loginTime;
        //创建时间
        @TableField(fill = FieldFill.INSERT)
        private Date createTime;
        //更新时间
        @TableField(fill = FieldFill.UPDATE)
        private Date updateTime;
        //是否删除（0：否 1：是）
        private Integer isDelete;


}
