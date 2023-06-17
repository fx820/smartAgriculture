package cn.ecnu.quartz.task;

import cn.ecnu.entity.EnvironmentMonitor;
import cn.ecnu.mapper.EnvironmentMonitorMapper;
import cn.ecnu.model.dto.EnvironmentMonitorDTO;
import cn.ecnu.model.vo.Result;
import cn.ecnu.utils.BeanCopyUtils;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.ecnu.mapper.VisitLogMapper;
import cn.ecnu.service.RedisService;
import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

import static cn.ecnu.constant.RedisConstant.UNIQUE_VISITOR;

/**
 * 执行定时任务
 *
 * @author zachary
 */
@SuppressWarnings(value = "all")
@Component("timedTask")
public class TimedTask {
    @Autowired
    private RedisService redisService;

    @Autowired
    private VisitLogMapper visitLogMapper;

    @Autowired
    private EnvironmentMonitorMapper environmentMonitorMapper;

    /**
     * 清除访问记录
     */
    public void clear() {
        redisService.deleteObject(UNIQUE_VISITOR);
    }

    /**
     * 测试任务
     */
    public void test() {
        System.out.println("测试任务");
    }

    /**
     * 清除一周前的访问日志
     */
    public void clearVistiLog() {
        DateTime endTime = DateUtil.beginOfDay(DateUtil.offsetDay(new Date(), -7));
        visitLogMapper.deleteVisitLog(endTime);
    }

    /**
     * 模拟大棚数据监测信息 每隔一小时就生成一条数据进入数据库
     */
    public void generateEnvironment(){
        System.out.println("生成监测信息");
        DecimalFormat df = new DecimalFormat("0.0");
        Double airTemperature = RandomUtil.randomDouble(0.0,40.0,3,RoundingMode.FLOOR);
        Integer airHumidity = RandomUtil.randomInt(40,100);
        Double solidTemperature = RandomUtil.randomDouble(0.0,35.0,3,RoundingMode.FLOOR);
        Integer solidHumidity = RandomUtil.randomInt(40,100);
        Integer CO2Level = RandomUtil.randomInt(800,1200);
        Integer lightLevel = RandomUtil.randomInt(300,500);
        EnvironmentMonitorDTO environmentMonitorDTO = new EnvironmentMonitorDTO()
                .builder().airTemperature(Double.parseDouble(df.format(airTemperature))).airHumidity(airHumidity)
                .soilTemperature(Double.parseDouble(df.format(solidTemperature))).soilHumidity(solidHumidity)
                .c02Level(CO2Level).lightLevel(lightLevel)
                .greenHouseId(19)
                .build();
        EnvironmentMonitor environmentMonitor = BeanCopyUtils.copyBean(environmentMonitorDTO, EnvironmentMonitor.class);
        environmentMonitorMapper.insert(environmentMonitor);
    }


}