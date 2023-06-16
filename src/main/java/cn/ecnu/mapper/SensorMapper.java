package cn.ecnu.mapper;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.ProductVO;
import cn.ecnu.model.vo.SensorsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.ecnu.entity.Sensor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @intro: Sensor表数据库访问层
 * @author: zachary
 * @version: 1.0
 */
 
public interface SensorMapper extends BaseMapper<Sensor> {
  /*
   * @intro:  查询指定条件的数据数量
   * @author: zachary
   * @param: condition
   * @return: Long
   **/
  Long countSensorVO(@Param("condition") ConditionDTO condition);

  /*
   * @intro: 查询列表
   * @author: zachary
   * @param: limit
   * @param: size
   * @param: condition
   * @return: List<ProductVO>
   **/
  List<SensorsVO> selectSensorVO(Long limit, Long size, ConditionDTO condition);
}
