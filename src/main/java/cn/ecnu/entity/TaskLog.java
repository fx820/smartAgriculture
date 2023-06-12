package cn.ecnu.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

 /**
 * @intro: taskLog表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskLog {
        //任务日志id
        private Integer id;
        //任务名称
        private String taskName;
        //任务组名
        private String taskGroup;
        //调用目标字符串
        private String invokeTarget;
        //日志信息
        private String taskMessage;
        //执行状态 (0失败 1正常)
        private Integer status;
        //错误信息
        private String errorInfo;
        //创建时间
        @TableField(fill = FieldFill.INSERT)
        private Date createTime;


}
