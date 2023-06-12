package cn.ecnu.service;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.TaskLogVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.ecnu.entity.TaskLog;

/**
 * 定时任务日志业务接口
 *
 * @author zachary
 */
public interface TaskLogService extends IService<TaskLog> {

    /**
     * 查看后台定时任务日志
     *
     * @param condition 条件
     * @return 后台定时任务日志
     */
    PageResult<TaskLogVO> listTaskLog(ConditionDTO condition);

    /**
     * 清空定时任务日志
     */
    void clearTaskLog();
}
