package cn.ecnu.controller;

import cn.ecnu.annotation.OptLogger;
import cn.ecnu.entity.Plant;
import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.PlantDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.PlantVO;
import cn.ecnu.model.vo.Result;
import cn.ecnu.service.PlantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @intro:种植控制层
 * @author: zachary
 * @version: 1.0
 */

@RestController
@RequestMapping("/plant")
@Api(tags = "种植模块")
public class PlantController {

    @Autowired
    private PlantService plantService;

    /**
     *种植农作物
     * @param plantDTO
     * @return
     */
    @PostMapping("/plant")
    @ApiOperation(value = "种植农作物")
    public Result<?> plant(@RequestBody PlantDTO plantDTO){
        plantService.plant(plantDTO);
        return Result.success();
    }

    @ApiOperation(value = "查看大棚列表")
    @GetMapping("/list")
    public Result<PageResult<PlantVO>> listPlantVO(ConditionDTO condition){
        return Result.success(plantService.listPlantVO(condition));
    }

    @ApiOperation(value = "收割")
    @RequestMapping("/cut")
    public Result<?> cut(@RequestParam("id") Integer id){
        Plant plant = new Plant();
        plant.setId(id);
        plant.setStatus(2);
        plantService.updateById(plant);
        return Result.success();
    }
}
