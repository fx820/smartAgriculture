package cn.ecnu.mapper;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.PlantVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.ecnu.entity.Plant;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @intro: Plant表数据库访问层
 * @author: zachary
 * @version: 1.0
 */
 
public interface PlantMapper extends BaseMapper<Plant> {

     Long countPlantVO(@Param("condition") ConditionDTO condition);

    List<PlantVO> selectPlantVO(Long limit, Long size, ConditionDTO condition);
}
