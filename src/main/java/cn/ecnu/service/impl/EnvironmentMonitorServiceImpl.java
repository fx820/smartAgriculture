package cn.ecnu.service.impl;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.EnvironmentMonitorVO;
import cn.ecnu.model.vo.GreenHouseVO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.mapper.EnvironmentMonitorMapper;
import cn.ecnu.entity.EnvironmentMonitor;
import cn.ecnu.service.EnvironmentMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @intro: EnvironmentMonitor表服务实现类
 * @author: zachary
 * @version: 1.0
 */
 
@Service("environmentMonitorService")
public class EnvironmentMonitorServiceImpl extends ServiceImpl<EnvironmentMonitorMapper, EnvironmentMonitor> implements EnvironmentMonitorService {

 @Autowired
 private EnvironmentMonitorMapper environmentMonitorMapper;

 @Override
 public PageResult<EnvironmentMonitorVO> listEnvironmentMonitorVO(ConditionDTO condition) {
  Long count = environmentMonitorMapper.countEnvironmentMonitorVO(condition);
  if (count==0){
   return new PageResult<>();
  }

  List<EnvironmentMonitorVO> environmentMonitorVOS = environmentMonitorMapper.selectEnvironmentMonitorVO(PageUtils.getLimit(),PageUtils.getSize(),condition);
  return new PageResult<>(environmentMonitorVOS,count);
 }

 @Override
 public void deleteEnvironmentMonitor(List<Integer> environmentMonitorIdList) {
   environmentMonitorMapper.deleteBatchIds(environmentMonitorIdList);
 }
}

