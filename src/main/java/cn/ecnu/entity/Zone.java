package cn.ecnu.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

 /**
 * @intro: zone表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Zone {
        //区域编号
        private Integer id;
        //区域名称
        private String name;
        //区域地址
        private String address;
        //创建时间
        @TableField(fill = FieldFill.INSERT)
        private Date createTime;
        //更新时间
        @TableField(fill = FieldFill.UPDATE)
        private Date updateTime;
        //状态（1：公开 2：私密）
        private Integer status;
        //是否删除（0：否 1：是）
        private Integer isDelete;


}
