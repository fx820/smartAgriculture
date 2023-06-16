package cn.ecnu.controller;

import cn.ecnu.annotation.OptLogger;
import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.SensorDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.Result;
import cn.ecnu.model.vo.SensorsVO;
import cn.ecnu.service.SensorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static cn.ecnu.constant.OptTypeConstant.*;

/**
 * @intro:设备控制器
 * @author: zachary
 * @version: 1.0
 */

@Api(tags = "设备模块")
@RestController
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    //TODO 权限还未配置

    /*
     * @intro:
     * @author: zachary
     * @param: condition 查询条件
     * @return: Result<PageResult<sensorsVO>> 分页设备列表
     **/
    @ApiOperation(value = "查看设备列表")
    @GetMapping("/list")
    public Result<PageResult<SensorsVO>> listSensorVo(ConditionDTO condition){
        return Result.success(sensorService.listSensorVO(condition));
    }


    /*
     * @intro: 增加设备信息
     * @author: zachary
     * @param: sensor
     * @return: Result<?>
     **/
    @OptLogger(value = ADD)
    @ApiOperation(value = "添加设备")
    @PostMapping("/add")
    public Result<?> addSensor(@RequestBody @Validated SensorDTO sensor){
        sensorService.addSensor(sensor);
        return Result.success();
    }


    /*
     * @intro:删除设备
     * @author: zachary
     * @param: sensorIdList
     * @return: Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除设备")
    @DeleteMapping("/delete")
    public Result<?> deleteSensor(@RequestBody List<Integer> sensorIdList){
        sensorService.deleteSensor(sensorIdList);
        return Result.success();
    }

    /*
     * @intro: 修改设备信息
     * @author: zachary
     * @param: sensor
     * @return: Result<?>
     **/

    @OptLogger(value = UPDATE)
    @ApiOperation(value = "修改设备信息")
    @PutMapping("/update")
    public Result<?> updateSensor(@RequestBody @Validated SensorDTO sensor){
        sensorService.updateSensor(sensor);
        return Result.success();
    }

    /*
     * @intro: 上传图片
     * @author: zachary
     * @param: file
     * @return: Result<String>
     **/
    @OptLogger(value = UPLOAD)
    @ApiOperation(value = "上传设备图片")
    @ApiImplicitParam(name = "file",value = "设备图片",dataType = "MultipartFile")
    @PostMapping("/upload")
    public Result<String> saveSensorImages(@RequestParam("file")MultipartFile file){
        return Result.success(sensorService.saveSensorImages(file));
    }
}
