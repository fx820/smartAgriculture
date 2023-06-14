package cn.ecnu.service;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.ZoneDTO;
import cn.ecnu.model.vo.ZoneVO;
import cn.ecnu.model.vo.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.ecnu.entity.Zone;

import java.util.List;

/**
 * @intro: Zone表服务接口
 * @author: zachary
 * @version: 1.0
 */
 
public interface ZoneService extends IService<Zone> {

    /**
     * 新增区域
     * @param zone
     */
    void addZone(ZoneDTO zone);


    /**
     * 删除区域信息
     * @param zoneIdList
     */
    void deleteZone(List<Integer> zoneIdList);


    /**
     * 更新区域信息
     * @param zone
     */
    void updateZone(ZoneDTO zone);

    /**
     * 分页查询区域信息
     * @param condition
     * @return
     */
    PageResult<ZoneVO> listZoneVO(ConditionDTO condition);

    /**
     * 查询所有可用的区域信息
     * @return
     */
    List<ZoneVO> selectEnableAll();

}
