package cn.ecnu.service;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.DeleteDTO;
import cn.ecnu.model.dto.SensorDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.SensorVO;
import cn.ecnu.model.vo.SensorsVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.ecnu.entity.Sensor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @intro: Sensor表服务接口
 * @author: zachary
 * @version: 1.0
 */
 
public interface SensorService extends IService<Sensor> {

 /*
  * @intro:
  * @author: zachary
  * @param: condition 条件
  * @return: PageResult<SensorVO> 设备分页列表
  **/
 PageResult<SensorsVO> listSensorVO(ConditionDTO condition);

 /*
  * @intro:
  * @author: zachary
  * @param: Sensor
  * @return: void
  **/
 void addSensor(SensorDTO Sensor);

 /*
  * @intro: 删除设备信息
  * @author: zachary
  * @param: SensorIdList
  * @return: void
  **/
 void deleteSensor(List<Integer> SensorIdList);


 /*
  * @intro: 修改设备信息
  * @author: zachary
  * @param: Sensor
  * @return: void
  **/
 void updateSensor(SensorDTO Sensor);

 /*
  * @intro: 上传图片
  * @author: zachary
  * @param: file
  * @return: String
  **/
 String saveSensorImages(MultipartFile file);

 
}
