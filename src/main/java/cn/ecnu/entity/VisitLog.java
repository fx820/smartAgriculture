package cn.ecnu.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

 /**
 * @intro: visitLog表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitLog {
        //id
        private Integer id;
        //访问页面
        private String page;
        //访问ip
        private String ipAddress;
        //访问地址
        private String ipSource;
        //操作系统
        private String os;
        //浏览器
        private String browser;
        //访问时间
        @TableField(fill = FieldFill.INSERT)
        private Date createTime;


}
