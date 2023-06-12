package cn.ecnu.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

 /**
 * @intro: siteConfig表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteConfig {
        //主键
        private Integer id;
        //用户头像
        private String userAvatar;
        //游客头像
        private String touristAvatar;
        //网站名称
        private String siteName;
        //网站地址
        private String siteAddress;
        //网站简介
        private String siteIntro;
        //网站公告
        private String siteNotice;
        //建站日期
        private String createSiteTime;
        //备案号
        private String recordNumber;
        //是否开启审核 (0否 1是)
        private Integer isCheck;
        //是否邮箱通知 (0否 1是)
        private Integer emailNotice;
        //登录方式
        private String loginList;
        //创建时间
        @TableField(fill = FieldFill.INSERT)
        private Date createTime;
        //更新时间
        @TableField(fill = FieldFill.UPDATE)
        private Date updateTime;
        //是否短信通知（0否 1是）
        private Integer phoneNotice;


}
