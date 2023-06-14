package cn.ecnu.mapper;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.GreenHouseVO;
import cn.ecnu.model.vo.PestMonitorVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.ecnu.entity.PestMonitor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @intro: PestMonitor表数据库访问层
 * @author: zachary
 * @version: 1.0
 */
 
public interface PestMonitorMapper extends BaseMapper<PestMonitor> {

 Long countPestMonitor(@Param("condition") ConditionDTO condition);

 List<PestMonitorVO> selectPestMonitorVO(Long limit, Long size, ConditionDTO condition);

}
