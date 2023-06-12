package cn.ecnu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.mapper.PestMonitorMapper;
import cn.ecnu.entity.PestMonitor;
import cn.ecnu.service.PestMonitorService;
import org.springframework.stereotype.Service;

 /**
 * @intro: PestMonitor表服务实现类
 * @author: zachary
 * @version: 1.0
 */
 
@Service("pestMonitorService")
public class PestMonitorServiceImpl extends ServiceImpl<PestMonitorMapper, PestMonitor> implements PestMonitorService {

}

