package cn.ecnu.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

 /**
 * @intro: plant表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Plant {
        //种植表id
        private Integer id;
        //农产品id
        private Integer productId;
         //大棚编号
         private Integer greenHouseId;
        //种植状态（0：成长中 1：等待收割 2：完成）
        private Integer status;
         //种植时间
         @TableField(fill = FieldFill.INSERT)
         private Date plantTime;
        //收割时间
        @TableField(fill = FieldFill.UPDATE)
        private Date harvestTime;

}
