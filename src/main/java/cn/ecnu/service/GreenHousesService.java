package cn.ecnu.service;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.DeleteDTO;
import cn.ecnu.model.dto.GreenHouseDTO;
import cn.ecnu.model.vo.GreenHouseVO;
import cn.ecnu.model.vo.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.ecnu.entity.GreenHouses;

import java.util.List;

/**
 * @intro: GreenHouses表服务接口
 * @author: zachary
 * @version: 1.0
 */
 
public interface GreenHousesService extends IService<GreenHouses> {

     /**
      * 新增大棚
      * @param greenHouse
      */
     void addGreenHouse(GreenHouseDTO greenHouse);


    /**
     * 删除大棚信息
     * @param greenHouseIdList
     */
     void deleteGreenHouse(List<Integer> greenHouseIdList);


    /**
     * 更新大棚信息
     * @param greenHouse
     */
    void updateGreenHouse(GreenHouseDTO greenHouse);

    PageResult<GreenHouseVO> listGreenHouseVO(ConditionDTO condition);
}
