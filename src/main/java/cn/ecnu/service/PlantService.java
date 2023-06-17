package cn.ecnu.service;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.PlantDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.PlantVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.ecnu.entity.Plant;

 /**
 * @intro: Plant表服务接口
 * @author: zachary
 * @version: 1.0
 */
 
public interface PlantService extends IService<Plant> {

     void plant(PlantDTO plantDTO);

     PageResult<PlantVO> listPlantVO(ConditionDTO condition);
 }
