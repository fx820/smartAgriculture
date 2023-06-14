package cn.ecnu.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

 /**
 * @intro: pestMonitor表实体类
 * @author: zachary
 * @version: 1.0
 */
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PestMonitor {
        //虫情监测数据编号
        private Integer id;
        //大棚编号
        private Integer greenHouseId;
        //监测图片信息
        private String imageSrc;
        //监测结果
        private String result;
        //虫害描述
        private String description;
        //农产品编号
        private Integer productId;
        //准确率
        private Double accuracy;
        //状态（1：公开 2：私密）
        private Integer status;
        //是否删除（0：否 1：是）
        private Integer isDelete;
        //创建时间
        @TableField(fill = FieldFill.INSERT)
        private Date createTime;


}
