package cn.ecnu.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

 /**
 * @intro: product表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
        //农产品编号
        private Integer id;
        //农产品名称
        private String name;
        //种植状态（0-未种植，1-种植）
        private Integer status;
        //种植周期（单位：天）
        private Integer cycle;
        //创建时间
        @TableField(fill = FieldFill.INSERT)
        private Date createTime;
        //修改时间
        @TableField(fill = FieldFill.UPDATE)
        private Date updateTime;
        //是否删除（0：否 1：是）
        private Integer isDelete;
        //农产品图像
        private String photo;

}
