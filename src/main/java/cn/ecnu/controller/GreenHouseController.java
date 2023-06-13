package cn.ecnu.controller;

import cn.ecnu.annotation.OptLogger;
import cn.ecnu.model.dto.ConditionDTO;
;
import cn.ecnu.model.dto.GreenHouseDTO;
import cn.ecnu.model.vo.GreenHouseVO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.Result;
import cn.ecnu.service.GreenHousesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static cn.ecnu.constant.OptTypeConstant.*;

/**
 * @intro:大棚控制层
 * @author: zachary
 * @version: 1.0
 */

@Api(tags = "大棚模块")
@RestController
@RequestMapping("/house")
public class GreenHouseController {

    @Autowired
    private GreenHousesService greenHousesService;

    /*
     * @intro:
     * @author: zachary
     * @param: condition 查询条件
     * @return: Result<PageResult<GreenHouseVO>> 分页大棚列表
     **/
    @ApiOperation(value = "查看大棚列表")
    @GetMapping("/list")
    public Result<PageResult<GreenHouseVO>> listGreenHouseVO(ConditionDTO condition){
        return Result.success(greenHousesService.listGreenHouseVO(condition));
    }


    /*
     * @intro: 增加大棚信息
     * @author: zachary
     * @param: GreenHouse
     * @return: Result<?>
     **/
    @OptLogger(value = ADD)
    @ApiOperation(value = "添加大棚")
    @PostMapping("/add")
    public Result<?> addGreenHouse(@RequestBody @Validated GreenHouseDTO GreenHouse){
        greenHousesService.addGreenHouse(GreenHouse);
        return Result.success();
    }


    /*
     * @intro:删除大棚
     * @author: zachary
     * @param: GreenHouseIdList
     * @return: Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除大棚")
    @DeleteMapping("/delete")
    public Result<?> deleteGreenHouse(@RequestBody List<Integer> GreenHouseIdList){
        greenHousesService.deleteGreenHouse(GreenHouseIdList);
        return Result.success();
    }


    /*
     * @intro: 修改农作物信息
     * @author: zachary
     * @param: GreenHouse
     * @return: Result<?>
     **/

    @OptLogger(value = UPDATE)
    @ApiOperation(value = "修改大棚信息")
    @PutMapping("/update")
    public Result<?> updateGreenHouse(@RequestBody @Validated GreenHouseDTO GreenHouse){
        greenHousesService.updateGreenHouse(GreenHouse);
        return Result.success();
    }

}
