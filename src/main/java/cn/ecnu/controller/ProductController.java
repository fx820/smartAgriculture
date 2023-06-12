package cn.ecnu.controller;

import cn.ecnu.annotation.OptLogger;
import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.DeleteDTO;
import cn.ecnu.model.dto.ProductDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.ProductVO;
import cn.ecnu.model.vo.Result;
import cn.ecnu.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static cn.ecnu.constant.OptTypeConstant.*;
import java.util.List;

/**
 * @intro:农产品控制器
 * @author: zachary
 * @version: 1.0
 */

@Api(tags = "农产品模块")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //TODO 权限还未配置

    /*
     * @intro:
     * @author: zachary
     * @param: condition 查询条件
     * @return: Result<PageResult<ProductVO>> 分页农产品列表
     **/
    @ApiOperation(value = "查看农产品列表")
    @GetMapping("/list")
    public Result<PageResult<ProductVO>> listProductVo(ConditionDTO condition){
        return Result.success(productService.listProductVO(condition));
    }


    /*
     * @intro: 增加农产品信息
     * @author: zachary
     * @param: product
     * @return: Result<?>
     **/
    @OptLogger(value = ADD)
    @ApiOperation(value = "添加农产品")
    @PostMapping("/add")
    public Result<?> addProduct(@Validated @RequestBody ProductDTO product){
        productService.addProduct(product);
        return Result.success();
    }


    /*
     * @intro:删除农产品
     * @author: zachary
     * @param: productIdList
     * @return: Result<?>
     **/
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除农产品")
    @DeleteMapping("/delete")
    public Result<?> deleteProduct(@RequestBody List<Integer> productIdList){
        productService.deleteProduct(productIdList);
        return Result.success();
    }

    /*
     * @intro:逻辑删除 恢复
     * @author: zachary
     * @param: delete
     * @return: Result<?>
     **/
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "回收或恢复农产品信息")
    @PutMapping("/recycle")
    public Result<?> updateProductDelete(@Validated @RequestBody DeleteDTO delete){
        productService.updateProductDelete(delete);
        return Result.success();
    }

    /*
     * @intro: 修改农作物信息
     * @author: zachary
     * @param: product
     * @return: Result<?>
     **/

    @OptLogger(value = UPDATE)
    @ApiOperation(value = "修改农产品信息")
    @PutMapping("/update")
    public Result<?> updateProduct(@Validated @RequestBody ProductDTO product){
        productService.updateProduct(product);
        return Result.success();
    }

    /*
     * @intro: 上传图片
     * @author: zachary
     * @param: file
     * @return: Result<String>
     **/
    @OptLogger(value = UPLOAD)
    @ApiOperation(value = "上传农作物图片")
    @ApiImplicitParam(name = "file",value = "农作物图片",dataType = "MultipartFile")
    @PostMapping("/upload")
    public Result<String> saveProductImages(@RequestParam("file")MultipartFile file){
        return Result.success(productService.saveProductImages(file));
    }
}
