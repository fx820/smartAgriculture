package cn.ecnu.service.impl;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.DeleteDTO;
import cn.ecnu.model.dto.GreenHouseDTO;
import cn.ecnu.model.vo.GreenHouseVO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.utils.BeanCopyUtils;
import cn.ecnu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.mapper.GreenHousesMapper;
import cn.ecnu.entity.GreenHouses;
import cn.ecnu.service.GreenHousesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @intro: GreenHouses表服务实现类
 * @author: zachary
 * @version: 1.0
 */
 
@Service("greenHousesService")
public class GreenHousesServiceImpl extends ServiceImpl<GreenHousesMapper, GreenHouses> implements GreenHousesService {

 @Autowired
 private GreenHousesMapper greenHousesMapper;

 @Override
 public void addGreenHouse(GreenHouseDTO greenHouse) {
  //大棚名称可重复
  GreenHouses addGreenHouse = BeanCopyUtils.copyBean(greenHouse, GreenHouses.class);
  baseMapper.insert(addGreenHouse);
 }

 @Override
 public void deleteGreenHouse(List<Integer> greenHouseIdList) {
  greenHousesMapper.deleteBatchIds(greenHouseIdList);
 }


 @Override
 public void updateGreenHouse(GreenHouseDTO greenHouse) {
  GreenHouses updateGreenHouse = BeanCopyUtils.copyBean(greenHouse, GreenHouses.class);
  baseMapper.updateById(updateGreenHouse);
 }

 @Override
 public PageResult<GreenHouseVO> listGreenHouseVO(ConditionDTO condition) {
  Long count = greenHousesMapper.countGreenHouse(condition);
  if (count==0){
   return new PageResult<>();
  }

  List<GreenHouseVO> greenHouseVOS = greenHousesMapper.selectGreenHouseVO(PageUtils.getLimit(),PageUtils.getSize(),condition);
  return new PageResult<>(greenHouseVOS,count);
 }
}

