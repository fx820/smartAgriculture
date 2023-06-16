package cn.ecnu.service.impl;

import cn.ecnu.enums.FilePathEnum;
import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.SensorDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.SensorsVO;
import cn.ecnu.strategy.context.UploadStrategyContext;
import cn.ecnu.utils.BeanCopyUtils;
import cn.ecnu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.mapper.SensorMapper;
import cn.ecnu.entity.Sensor;
import cn.ecnu.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @intro: Sensor表服务实现类
 * @author: zachary
 * @version: 1.0
 */
 
@Service("sensorService")
public class SensorServiceImpl extends ServiceImpl<SensorMapper, Sensor> implements SensorService {

 @Autowired
 private SensorMapper sensorMapper;
 @Autowired
 private UploadStrategyContext uploadStrategyContext;

 @Override
 public PageResult<SensorsVO> listSensorVO(ConditionDTO condition) {
  Long count = sensorMapper.countSensorVO(condition);
  if (count==0){
   return new PageResult<>();
  }
  List<SensorsVO> sensorsVOS = sensorMapper.selectSensorVO(PageUtils.getLimit(), PageUtils.getSize(), condition);
  return new PageResult<>(sensorsVOS,count);
 }

 @Override
 public void addSensor(SensorDTO sensor) {
  Sensor add = BeanCopyUtils.copyBean(sensor, Sensor.class);
  baseMapper.insert(add);
 }

 @Override
 public void deleteSensor(List<Integer> sensorIdList) {
  sensorMapper.deleteBatchIds(sensorIdList);
 }

 @Override
 public void updateSensor(SensorDTO sensor) {
  Sensor update = BeanCopyUtils.copyBean(sensor, Sensor.class);
  baseMapper.updateById(update);
 }

 @Override
 public String saveSensorImages(MultipartFile file) {
  String url = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.SENSOR.getFilePath());
  return url;
 }

}

