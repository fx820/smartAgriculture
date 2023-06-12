package cn.ecnu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.mapper.SensorMapper;
import cn.ecnu.entity.Sensor;
import cn.ecnu.service.SensorService;
import org.springframework.stereotype.Service;

 /**
 * @intro: Sensor表服务实现类
 * @author: zachary
 * @version: 1.0
 */
 
@Service("sensorService")
public class SensorServiceImpl extends ServiceImpl<SensorMapper, Sensor> implements SensorService {

}

