package cn.ecnu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.mapper.GreenHousesMapper;
import cn.ecnu.entity.GreenHouses;
import cn.ecnu.service.GreenHousesService;
import org.springframework.stereotype.Service;

 /**
 * @intro: GreenHouses表服务实现类
 * @author: zachary
 * @version: 1.0
 */
 
@Service("greenHousesService")
public class GreenHousesServiceImpl extends ServiceImpl<GreenHousesMapper, GreenHouses> implements GreenHousesService {

}

