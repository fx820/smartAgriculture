package cn.ecnu.service;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.ecnu.entity.ExceptionLog;

/**
 * 异常日志业务接口
 *
 * @author zachary
 */
public interface ExceptionLogService extends IService<ExceptionLog> {

    /**
     * 查看异常日志列表
     *
     * @param condition 条件
     * @return 日志列表
     */
    PageResult<ExceptionLog> listExceptionLog(ConditionDTO condition);

    /**
     * 保存异常日志
     *
     * @param exceptionLog 异常日志信息
     */
    void saveExceptionLog(ExceptionLog exceptionLog);
}
