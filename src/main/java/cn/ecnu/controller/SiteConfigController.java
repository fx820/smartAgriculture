package cn.ecnu.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.ecnu.model.vo.Result;
import cn.ecnu.annotation.OptLogger;
import cn.ecnu.entity.SiteConfig;
import cn.ecnu.service.SiteConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static cn.ecnu.constant.OptTypeConstant.UPDATE;
import static cn.ecnu.constant.OptTypeConstant.UPLOAD;

/**
 * 网站配置控制器
 *
 * @author zachary
 **/
@Api(tags = "网站配置模块")
@RestController
public class SiteConfigController {

    @Autowired
    private SiteConfigService webConfigService;

    /**
     * 获取网站配置
     *
     * @return {@link Result <SiteConfig>} 网站配置
     */
    @ApiOperation(value = "获取网站配置")
    @SaCheckPermission("web:site:list")
    @GetMapping("/admin/site/list")
    public Result<SiteConfig> getSiteConfig() {
        return Result.success(webConfigService.getSiteConfig());
    }

    /**
     * 更新网站配置
     *
     * @param siteConfig 网站配置
     * @return {@link Result<>}
     */
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "更新网站配置")
    @SaCheckPermission("web:site:update")
    @PutMapping("/admin/site/update")
    public Result<?> updateSiteConfig(@RequestBody SiteConfig siteConfig) {
        webConfigService.updateSiteConfig(siteConfig);
        return Result.success();
    }



}