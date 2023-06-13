package cn.ecnu.mapper;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.GreenHouseVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.ecnu.entity.GreenHouses;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @intro: GreenHouses表数据库访问层
 * @author: zachary
 * @version: 1.0
 */
 
public interface GreenHousesMapper extends BaseMapper<GreenHouses> {

    Long countGreenHouse(@Param("condition") ConditionDTO condition);

    List<GreenHouseVO> selectGreenHouseVO(Long limit, Long size, ConditionDTO condition);
}
