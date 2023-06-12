package cn.ecnu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.entity.VisitLog;
import cn.ecnu.mapper.VisitLogMapper;
import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.service.VisitLogService;
import cn.ecnu.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 访问业务接口实现类
 *
 * @author zachary
 */
@Service
public class VisitLogServiceImpl extends ServiceImpl<VisitLogMapper, VisitLog> implements VisitLogService {

    @Autowired
    private VisitLogMapper visitLogMapper;

    @Override
    public void saveVisitLog(VisitLog visitLog) {
        // 保存访问日志
        visitLogMapper.insert(visitLog);
    }

    @Override
    public PageResult<VisitLog> listVisitLog(ConditionDTO condition) {
        // 查询访问日志数量
        Long count = visitLogMapper.selectCount(new LambdaQueryWrapper<VisitLog>()
                .like(StringUtils.hasText(condition.getKeyword()), VisitLog::getPage, condition.getKeyword())
        );
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询访问日志列表
        List<VisitLog> visitLogVOList = visitLogMapper.selectVisitLogList(PageUtils.getLimit(),
                PageUtils.getSize(), condition.getKeyword());
        return new PageResult<>(visitLogVOList, count);
    }
}




