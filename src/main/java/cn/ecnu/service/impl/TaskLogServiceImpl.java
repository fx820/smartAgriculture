package cn.ecnu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.entity.TaskLog;
import cn.ecnu.mapper.TaskLogMapper;
import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.TaskLogVO;
import cn.ecnu.service.TaskLogService;
import cn.ecnu.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 定时任务日志业务接口实现类
 *
 * @author zachary
 */
@Service
public class TaskLogServiceImpl extends ServiceImpl<TaskLogMapper, TaskLog> implements TaskLogService {

    @Autowired
    private TaskLogMapper taskLogMapper;

    @Override
    public PageResult<TaskLogVO> listTaskLog(ConditionDTO condition) {
        // 查询定时任务日志数量
        Long count = taskLogMapper.selectTaskLogCount(condition);
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询定时任务日志列表
        List<TaskLogVO> taskLogVOList = taskLogMapper.selectTaskLogVOList(PageUtils.getLimit(), PageUtils.getSize(), condition);
        return new PageResult<>(taskLogVOList, count);
    }

    @Override
    public void clearTaskLog() {
        taskLogMapper.delete(null);
    }
}




