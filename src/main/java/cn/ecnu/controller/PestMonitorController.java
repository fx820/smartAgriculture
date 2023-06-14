package cn.ecnu.controller;

import cn.ecnu.annotation.OptLogger;
import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.PestMonitorDTO;
import cn.ecnu.model.vo.PestMonitorVO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.PestRecognizeVO;
import cn.ecnu.model.vo.Result;
import cn.ecnu.service.PestMonitorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.ecnu.constant.OptTypeConstant.*;

/**
 * @intro:农作物虫害监控控制层
 * @author: zachary
 * @version: 1.0
 */

@RestController
@RequestMapping("/pest/monitor")
public class PestMonitorController {

    @Autowired
    private PestMonitorService pestMonitorService;

    /**
     * 识别在线图片 返回虫害信息
     * @param imageUrl
     * @return
     */

    @ApiOperation(value = "识别虫害信息")
    @RequestMapping("/recognize")
    public Result<PestRecognizeVO> recognize(String imageUrl){
        return Result.success(pestMonitorService.recognize(imageUrl));
    }


    /*
     * @intro:
     * @author: zachary
     * @param: condition 查询条件
     * @return: Result<PageResult<PestMonitorVO>> 分页虫害列表
     **/
    @ApiOperation(value = "查看虫害列表")
    @GetMapping("/list")
    public Result<PageResult<PestMonitorVO>> listPestMonitorVO(ConditionDTO condition){
        return Result.success(pestMonitorService.listPestMonitorVO(condition));
    }


    /*
     * @intro: 增加虫害信息
     * @author: zachary
     * @param: PestMonitor
     * @return: Result<?>
     **/
    @OptLogger(value = ADD)
    @ApiOperation(value = "添加虫害")
    @PostMapping("/add")
    public Result<?> addPestMonitor(@RequestBody @Validated PestMonitorDTO PestMonitor){
        pestMonitorService.addPestMonitor(PestMonitor);
        return Result.success();
    }


    /*
     * @intro:删除虫害
     * @author: zachary
     * @param: PestMonitorIdList
     * @return: Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除虫害")
    @DeleteMapping("/delete")
    public Result<?> deletePestMonitor(@RequestBody List<Integer> PestMonitorIdList){
        pestMonitorService.deletePestMonitor(PestMonitorIdList);
        return Result.success();
    }


    /*
     * @intro: 修改虫害信息
     * @author: zachary
     * @param: PestMonitor
     * @return: Result<?>
     **/

    @OptLogger(value = UPDATE)
    @ApiOperation(value = "修改虫害信息")
    @PutMapping("/update")
    public Result<?> updatePestMonitor(@RequestBody @Validated PestMonitorDTO PestMonitor){
        pestMonitorService.updatePestMonitor(PestMonitor);
        return Result.success();
    }

}
