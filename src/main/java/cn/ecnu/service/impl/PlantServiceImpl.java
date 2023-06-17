package cn.ecnu.service.impl;

import cn.ecnu.entity.Product;
import cn.ecnu.mapper.ProductMapper;
import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.PlantDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.PlantVO;
import cn.ecnu.utils.BeanCopyUtils;
import cn.ecnu.utils.PageUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.mapper.PlantMapper;
import cn.ecnu.entity.Plant;
import cn.ecnu.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @intro: Plant表服务实现类
 * @author: zachary
 * @version: 1.0
 */
 
@Service("plantService")
public class PlantServiceImpl extends ServiceImpl<PlantMapper, Plant> implements PlantService {

 @Autowired
 private PlantMapper plantMapper;

 @Autowired
 private ProductMapper productMapper;

  @Override
  public void plant(PlantDTO plantDTO) {
   Plant plant = BeanCopyUtils.copyBean(plantDTO, Plant.class);
   baseMapper.insert(plant);
   //更改种植状态
   Product product = new Product();
   product.setId(plantDTO.getProductId());
   product.setStatus(1);
   productMapper.updateById(product);
  }

  @Override
  public PageResult<PlantVO> listPlantVO(ConditionDTO condition) {
   //查询数据总数
   Long count = plantMapper.countPlantVO(condition);
   if (count==0){
    return new PageResult<>();
   }
   //查询数据信息 并封装
   List<PlantVO> plantVOS = plantMapper.selectPlantVO(PageUtils.getLimit(),PageUtils.getSize(),condition);
   return new PageResult<>(plantVOS,count);
  }

 }

