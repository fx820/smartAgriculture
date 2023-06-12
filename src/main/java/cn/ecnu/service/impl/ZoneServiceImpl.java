package cn.ecnu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.mapper.ZoneMapper;
import cn.ecnu.entity.Zone;
import cn.ecnu.service.ZoneService;
import org.springframework.stereotype.Service;

 /**
 * @intro: Zone表服务实现类
 * @author: zachary
 * @version: 1.0
 */
 
@Service("zoneService")
public class ZoneServiceImpl extends ServiceImpl<ZoneMapper, Zone> implements ZoneService {

}

