package cn.ecnu.service.impl;

import cn.ecnu.entity.EnvironmentMonitor;
import cn.ecnu.mapper.*;
import cn.ecnu.model.vo.BackInfoVO;
import cn.ecnu.model.vo.EnvironmentViewVO;
import cn.ecnu.service.BackInfoService;
import cn.ecnu.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * @intro:
 * @author: zachary
 * @version: 1.0
 */

@Service
public class BackInfoServiceImpl implements BackInfoService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private GreenHousesMapper greenHousesMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ZoneMapper zoneMapper;
    @Autowired
    private EnvironmentMonitorMapper environmentMonitorMapper;
    @Override
    public BackInfoVO getBackInfo() {

        //农作物量
        Long productCount = productMapper.selectCount(null);
        //大棚量
        Long houseCount = greenHousesMapper.selectCount(null);
        // 用户量
        Long userCount = userMapper.selectCount(null);
        // 地区量
        Long zoneCount = zoneMapper.selectCount(null);

        List<EnvironmentMonitor> environmentMonitors = environmentMonitorMapper.selectList(null);
        List<EnvironmentViewVO> environmentViewVOList = BeanCopyUtils.copyBeanList(environmentMonitors, EnvironmentViewVO.class);


        BackInfoVO backInfoVO = BackInfoVO.builder()
                .EnvironmentViewVOList(environmentViewVOList)
                .houseCount(houseCount)
                .productCount(productCount)
                .zoneCount(zoneCount)
                .userCount(userCount)
                .build();
        return backInfoVO;
    }
}
