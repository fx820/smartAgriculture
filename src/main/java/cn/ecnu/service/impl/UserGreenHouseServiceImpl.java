package cn.ecnu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.mapper.UserGreenHouseMapper;
import cn.ecnu.entity.UserGreenHouse;
import cn.ecnu.service.UserGreenHouseService;
import org.springframework.stereotype.Service;

 /**
 * @intro: UserGreenHouse表服务实现类
 * @author: zachary
 * @version: 1.0
 */
 
@Service("userGreenHouseService")
public class UserGreenHouseServiceImpl extends ServiceImpl<UserGreenHouseMapper, UserGreenHouse> implements UserGreenHouseService {

}

