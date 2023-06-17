package cn.ecnu.controller;

import cn.ecnu.annotation.OptLogger;
import cn.ecnu.entity.EnvironmentMonitor;
import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.EnvironmentMonitorVO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.Result;
import cn.ecnu.service.EnvironmentMonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.ecnu.constant.OptTypeConstant.DELETE;

/**
 * @intro:大棚数据监测控制层
 * @author: zachary
 * @version: 1.0
 */

@RestController
@RequestMapping("/environment/monitor")
@Api(tags = "大棚监测模块")
public class EnvironmentMonitorController {
    @Autowired
    private EnvironmentMonitorService environmentMonitorService;


    @ApiOperation(value = "查看大棚信息监测列表")
    @GetMapping("/list")
    public Result<PageResult<EnvironmentMonitorVO>> listEnvironmentMonitorVO(ConditionDTO condition){
        return Result.success(environmentMonitorService.listEnvironmentMonitorVO(condition));
    }

    @ApiOperation(value = "插入大棚信息监测")
    @PostMapping("/add")
    public Result<Integer> addEnvironmentMonitorVO(@RequestBody EnvironmentMonitor environmentMonitor){
        return Result.success(environmentMonitorService.addEnvironmentMonitor(environmentMonitor));
    }



    @ApiOperation(value = "删除大棚监测信息")
    @DeleteMapping("/delete")
    public Result<?> deleteEnvironmentMonitor(@RequestBody List<Integer> environmentMonitorIdList){
        environmentMonitorService.deleteEnvironmentMonitor(environmentMonitorIdList);
        return Result.success();
    }
}
