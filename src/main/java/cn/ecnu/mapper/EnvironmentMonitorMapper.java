package cn.ecnu.mapper;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.EnvironmentMonitorVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.ecnu.entity.EnvironmentMonitor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @intro: EnvironmentMonitor表数据库访问层
 * @author: zachary
 * @version: 1.0
 */
 
public interface EnvironmentMonitorMapper extends BaseMapper<EnvironmentMonitor> {

     Long countEnvironmentMonitorVO(@Param("condition") ConditionDTO condition);

     List<EnvironmentMonitorVO> selectEnvironmentMonitorVO(Long limit, Long size, ConditionDTO condition);
 }
