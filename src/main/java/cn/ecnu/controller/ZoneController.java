package cn.ecnu.controller;

import cn.ecnu.annotation.OptLogger;
import cn.ecnu.entity.GreenHouses;
import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.ZoneDTO;
import cn.ecnu.model.vo.GreenHouseVO;
import cn.ecnu.model.vo.ZoneVO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.Result;
import cn.ecnu.service.GreenHousesService;
import cn.ecnu.service.ZoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.ecnu.constant.OptTypeConstant.*;

;

/**
 * @intro:区域控制层
 * @author: zachary
 * @version: 1.0
 */

@Api(tags = "区域模块")
@RestController
@RequestMapping("/zone")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @Autowired
    private GreenHousesService greenHousesService;

    /*
     * @intro:
     * @author: zachary
     * @param: condition 查询条件
     * @return: Result<PageResult<ZoneVO>> 分页区域列表
     **/
    @ApiOperation(value = "查看区域列表")
    @GetMapping("/list")
    public Result<PageResult<ZoneVO>> listZoneVO(ConditionDTO condition){
        return Result.success(zoneService.listZoneVO(condition));
    }

    /**
     * 查看所有可用地区编号和名称
     * @return
     */
    @ApiOperation(value = "查看所有可用地区编号和名称")
    @GetMapping("/selectEnableAll")
    public Result<List<ZoneVO>> selectEnableAll(){
        return Result.success(zoneService.selectEnableAll());
    }

    /**
     * 查看该地区是否还有大棚
     * @param ids
     * @return
     */
    @ApiOperation(value = "查看该地区是否还有大棚")
    @GetMapping("/hasHouse")
    public Result<List<GreenHouseVO>> hasHouse(Integer[] ids){
        return Result.success(greenHousesService.hasHouse(ids));
    }


    /*
     * @intro: 增加区域信息
     * @author: zachary
     * @param: Zone
     * @return: Result<?>
     **/
    @OptLogger(value = ADD)
    @ApiOperation(value = "添加区域")
    @PostMapping("/add")
    public Result<?> addZone(@RequestBody @Validated ZoneDTO Zone){
        zoneService.addZone(Zone);
        return Result.success();
    }


    /*
     * @intro:删除区域
     * @author: zachary
     * @param: ZoneIdList
     * @return: Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除区域")
    @DeleteMapping("/delete")
    public Result<?> deleteZone(@RequestBody List<Integer> ZoneIdList){
        zoneService.deleteZone(ZoneIdList);
        return Result.success();
    }


    /*
     * @intro: 修改区域信息
     * @author: zachary
     * @param: Zone
     * @return: Result<?>
     **/

    @OptLogger(value = UPDATE)
    @ApiOperation(value = "修改区域信息")
    @PutMapping("/update")
    public Result<?> updateZone(@RequestBody @Validated ZoneDTO Zone){
        zoneService.updateZone(Zone);
        return Result.success();
    }

}
