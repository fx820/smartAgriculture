package cn.ecnu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.entity.ExceptionLog;
import cn.ecnu.mapper.ExceptionLogMapper;
import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.service.ExceptionLogService;
import cn.ecnu.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 异常日志业务接口实现类
 *
 * @author zachary
 */
@Service
public class ExceptionLogServiceImpl extends ServiceImpl<ExceptionLogMapper, ExceptionLog> implements ExceptionLogService {

    @Autowired
    private ExceptionLogMapper exceptionLogMapper;

    @Override
    public PageResult<ExceptionLog> listExceptionLog(ConditionDTO condition) {
        // 查询异常日志数量
        Long count = exceptionLogMapper.selectCount(new LambdaQueryWrapper<ExceptionLog>()
                .like(StringUtils.hasText(condition.getOptModule()), ExceptionLog::getModule, condition.getOptModule())
                .or()
                .like(StringUtils.hasText(condition.getKeyword()), ExceptionLog::getDescription, condition.getKeyword())
        );
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询异常日志列表
        List<ExceptionLog> operationLogVOList = exceptionLogMapper.selectExceptionLogList(PageUtils.getLimit(),
                PageUtils.getSize(), condition);
        return new PageResult<>(operationLogVOList, count);
    }

    @Override
    public void saveExceptionLog(ExceptionLog exceptionLog) {
        // 保存异常日志
        exceptionLogMapper.insert(exceptionLog);
    }
}




