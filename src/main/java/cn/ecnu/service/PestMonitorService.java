package cn.ecnu.service;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.PestMonitorDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.PestMonitorVO;
import cn.ecnu.model.vo.PestRecognizeVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.ecnu.entity.PestMonitor;

import java.util.List;

/**
 * @intro: PestMonitor表服务接口
 * @author: zachary
 * @version: 1.0
 */
 
public interface PestMonitorService extends IService<PestMonitor> {

     /**
      * 在线识别病害信息
      * @param imageUrl
      * @return
      */
     PestRecognizeVO recognize(String imageUrl);

    /**
     * 条件查询虫害信息
     * @param condition
     * @return
     */
     PageResult<PestMonitorVO> listPestMonitorVO(ConditionDTO condition);


    /**
     * 新增数据
     * @param pestMonitor
     */
    void addPestMonitor(PestMonitorDTO pestMonitor);


    /**
     * 删除数据
     * @param pestMonitorIdList
     */
     void deletePestMonitor(List<Integer> pestMonitorIdList);


    /**
     * 更新数据
     * @param pestMonitor
     */
    void updatePestMonitor(PestMonitorDTO pestMonitor);
}
