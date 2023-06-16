package cn.ecnu.service;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.EnvironmentMonitorVO;
import cn.ecnu.model.vo.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.ecnu.entity.EnvironmentMonitor;

import java.util.List;

/**
 * @intro: EnvironmentMonitor表服务接口
 * @author: zachary
 * @version: 1.0
 */
 
public interface EnvironmentMonitorService extends IService<EnvironmentMonitor> {

     PageResult<EnvironmentMonitorVO> listEnvironmentMonitorVO(ConditionDTO condition);

     void deleteEnvironmentMonitor(List<Integer> environmentMonitorIdList);
 }
