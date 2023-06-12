package cn.ecnu.mapper;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.ProductVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.ecnu.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @intro: Product表数据库访问层
 * @author: zachary
 * @version: 1.0
 */
 
public interface ProductMapper extends BaseMapper<Product> {

    /*
     * @intro:  查询指定条件的数据数量
     * @author: zachary
     * @param: condition
     * @return: Long
     **/
     Long countProductVO(@Param("condition") ConditionDTO condition);

     /*
      * @intro: 查询农产品列表
      * @author: zachary
      * @param: limit
      * @param: size
      * @param: condition
      * @return: List<ProductVO>
      **/
     List<ProductVO> selectProductVO(Long limit, Long size, ConditionDTO condition);
 }
