package cn.ecnu.controller;

import cn.ecnu.model.vo.BackInfoVO;
import cn.ecnu.model.vo.Result;
import cn.ecnu.service.BackInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @intro:后台信息控制层
 * @author: zachary
 * @version: 1.0
 */

@Api(tags = "后台模块")
@RestController
public class BackInfoController {

    @Autowired
    private BackInfoService backInfoService;

    @ApiOperation(value = "查看后台信息")
    @GetMapping("/admin")
    public Result<BackInfoVO> getBackInfo() {
        return Result.success(backInfoService.getBackInfo());
    }
}
