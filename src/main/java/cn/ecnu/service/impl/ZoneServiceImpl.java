package cn.ecnu.service.impl;

import cn.ecnu.constant.CommonConstant;
import cn.ecnu.entity.GreenHouses;
import cn.ecnu.mapper.GreenHousesMapper;
import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.ZoneDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.ZoneVO;
import cn.ecnu.utils.BeanCopyUtils;
import cn.ecnu.utils.PageUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.mapper.ZoneMapper;
import cn.ecnu.entity.Zone;
import cn.ecnu.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @intro: Zone表服务实现类
 * @author: zachary
 * @version: 1.0
 */
 
@Service("zoneService")
public class ZoneServiceImpl extends ServiceImpl<ZoneMapper, Zone> implements ZoneService {

 @Autowired
 private ZoneMapper zoneMapper;
 @Autowired
 private GreenHousesMapper greenHousesMapper;



 @Override
 public void addZone(ZoneDTO zone) {
  Zone addZone = BeanCopyUtils.copyBean(zone, Zone.class);
  baseMapper.insert(addZone);
 }

 @Override
 public void deleteZone(List<Integer> zoneIdList) {
  getBaseMapper().deleteBatchIds(zoneIdList);
 }

 @Override
 public void updateZone(ZoneDTO zone) {
  Zone updateZone = BeanCopyUtils.copyBean(zone, Zone.class);
  baseMapper.updateById(updateZone);
 }

 @Override
 public PageResult<ZoneVO> listZoneVO(ConditionDTO condition) {
  Long count = zoneMapper.countZone(condition);
  if (count==0){
   return new PageResult<>();
  }

  List<ZoneVO> zoneVOS = zoneMapper.selectZoneVO(PageUtils.getLimit(),PageUtils.getSize(),condition);
  return new PageResult<>(zoneVOS,count);
 }

 @Override
 public List<ZoneVO> selectEnableAll() {
  LambdaQueryWrapper<Zone> queryWrapper = new LambdaQueryWrapper();
  queryWrapper.
          select(Zone::getId,Zone::getName)
          .eq(Zone::getStatus, CommonConstant.ZONE_ENABLE_STATUS);
  List<Zone> zones = zoneMapper.selectList(queryWrapper);
  List<ZoneVO> zoneVOS = BeanCopyUtils.copyBeanList(zones, ZoneVO.class);
  return zoneVOS;
 }

}

