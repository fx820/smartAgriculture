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
 * @intro: greenHouses表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GreenHouses {
        //大棚编号
        private Integer id;
        //大棚名称
        private String name;
        //所在区域
        private Integer zoneId;
        //建造时间
        private Date buildTime;
        //使用状态（1：荒废 2：使用中）
        private Integer status;
        //是否删除（0：否 1：是）
        private Integer isDelete;
        //创建时间
        @TableField(fill = FieldFill.INSERT)
        private Date createTime;
        //更新时间
        @TableField(fill = FieldFill.UPDATE)
        private Date updateTime;


}
