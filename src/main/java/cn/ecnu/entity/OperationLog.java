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
 * @intro: operationLog表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationLog {
        //操作id
        private Integer id;
        //操作模块
        private String module;
        //操作类型
        private String type;
        //操作url
        private String url;
        //方法名称
        private String name;
        //操作描述
        private String description;
        //请求参数
        private String params;
        //请求方式
        private String method;
        //返回数据
        private String data;
        //用户id
        private Integer userId;
        //用户昵称
        private String nickName;
        //操作ip
        private String ipAddress;
        //操作地址
        private String ipSource;
        //操作耗时 (毫秒)
        private Integer times;
        //操作时间
        @TableField(fill = FieldFill.INSERT)
        private Date createTime;


}
