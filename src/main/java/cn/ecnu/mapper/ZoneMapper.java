package cn.ecnu.mapper;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.ZoneVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.ecnu.entity.Zone;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @intro: Zone表数据库访问层
 * @author: zachary
 * @version: 1.0
 */
 
public interface ZoneMapper extends BaseMapper<Zone> {

     Long countZone(@Param("condition")ConditionDTO condition);

     List<ZoneVO> selectZoneVO(Long limit, Long size, ConditionDTO condition);
 }
