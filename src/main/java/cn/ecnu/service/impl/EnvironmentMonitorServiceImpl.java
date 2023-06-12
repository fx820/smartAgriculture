package cn.ecnu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.mapper.EnvironmentMonitorMapper;
import cn.ecnu.entity.EnvironmentMonitor;
import cn.ecnu.service.EnvironmentMonitorService;
import org.springframework.stereotype.Service;

 /**
 * @intro: EnvironmentMonitor表服务实现类
 * @author: zachary
 * @version: 1.0
 */
 
@Service("environmentMonitorService")
public class EnvironmentMonitorServiceImpl extends ServiceImpl<EnvironmentMonitorMapper, EnvironmentMonitor> implements EnvironmentMonitorService {

}

