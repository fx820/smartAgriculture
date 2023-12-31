package cn.ecnu.manager.factory;


import cn.ecnu.entity.ExceptionLog;
import cn.ecnu.entity.OperationLog;
import cn.ecnu.entity.VisitLog;
import cn.ecnu.service.ExceptionLogService;
import cn.ecnu.service.OperationLogService;
import cn.ecnu.service.VisitLogService;
import cn.ecnu.utils.SpringUtils;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author zachary
 */
public class AsyncFactory {

    /**
     * 记录操作日志
     *
     * @param operationLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOperation(OperationLog operationLog) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(OperationLogService.class).saveOperationLog(operationLog);
            }
        };
    }

    /**
     * 记录异常日志
     *
     * @param exceptionLog 异常日志信息
     * @return 任务task
     */
    public static TimerTask recordException(ExceptionLog exceptionLog) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(ExceptionLogService.class).saveExceptionLog(exceptionLog);
            }
        };
    }

    /**
     * 记录访问日志
     *
     * @param visitLog 访问日志信息
     * @return 任务task
     */
    public static TimerTask recordVisit(VisitLog visitLog) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(VisitLogService.class).saveVisitLog(visitLog);
            }
        };
    }
}