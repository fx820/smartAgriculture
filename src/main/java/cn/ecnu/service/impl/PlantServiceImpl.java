package cn.ecnu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.mapper.PlantMapper;
import cn.ecnu.entity.Plant;
import cn.ecnu.service.PlantService;
import org.springframework.stereotype.Service;

 /**
 * @intro: Plant表服务实现类
 * @author: zachary
 * @version: 1.0
 */
 
@Service("plantService")
public class PlantServiceImpl extends ServiceImpl<PlantMapper, Plant> implements PlantService {

}

