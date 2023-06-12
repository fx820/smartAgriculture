package cn.ecnu.service;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.OperationLogVO;
import cn.ecnu.model.vo.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.ecnu.entity.OperationLog;

/**
 * 操作日志业务接口
 *
 * @author zachary
 */
public interface OperationLogService extends IService<OperationLog> {

    /**
     * 查看操作日志列表
     *
     * @param condition 条件
     * @return 日志列表
     */
    PageResult<OperationLogVO> listOperationLogVO(ConditionDTO condition);

    /**
     * 保存操作日志
     *
     * @param operationLog 操作日志信息
     */
    void saveOperationLog(OperationLog operationLog);
}
