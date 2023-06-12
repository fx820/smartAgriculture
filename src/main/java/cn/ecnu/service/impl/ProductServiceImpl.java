package cn.ecnu.service.impl;

import cn.ecnu.enums.FilePathEnum;
import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.DeleteDTO;
import cn.ecnu.model.dto.ProductDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.ProductVO;
import cn.ecnu.strategy.UploadStrategy;
import cn.ecnu.strategy.context.UploadStrategyContext;
import cn.ecnu.utils.BeanCopyUtils;
import cn.ecnu.utils.FileUtils;
import cn.ecnu.utils.PageUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ecnu.mapper.ProductMapper;
import cn.ecnu.entity.Product;
import cn.ecnu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @intro: Product表服务实现类
 * @author: zachary
 * @version: 1.0
 */
 
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

 @Autowired
 private ProductMapper productMapper;
 @Autowired
 private UploadStrategyContext uploadStrategyContext;



  @Override
  public PageResult<ProductVO> listProductVO(ConditionDTO condition) {
   //查询农产品数量
   Long count = productMapper.countProductVO(condition);
   if (count==0){
    return new PageResult<>();
   }
   //查询农产品信息（已逻辑删除的不查询）
   List<ProductVO> productVOS = productMapper.selectProductVO(PageUtils.getLimit(), PageUtils.getSize(), condition);
   return new PageResult<>(productVOS,count);
  }

  
  @Transactional
 @Override
 public void addProduct(ProductDTO product) {

   Product hasProduct = productMapper.selectOne(new LambdaQueryWrapper<Product>()
           .select(Product::getId)
           .eq(Product::getName,product.getName())
   );
   Assert.notNull(hasProduct,product.getName()+"已存在！");
   //添加农产品信息
   Product newProduct = BeanCopyUtils.copyBean(product, Product.class);
   baseMapper.insert(newProduct);
  }

    @Override
    public void deleteProduct(List<Integer> productIdList) {
      productMapper.deleteBatchIds(productIdList);
    }

    @Override
    public void updateProductDelete(DeleteDTO delete) {
      //批量更新农产品删除状态
        List<Product> productList = delete.getIdList()
                .stream()
                .map(id -> Product.builder()
                        .id(id)
                        .isDelete(delete.getIsDelete())
                        .build()
                ).collect(Collectors.toList());
        this.updateBatchById(productList);
    }

    @Override
    public void updateProduct(ProductDTO product) {
        Product updateProduct = BeanCopyUtils.copyBean(product, Product.class);
        baseMapper.updateById(updateProduct);
    }

    @Override
    public String saveProductImages(MultipartFile file) {
        //上传文件
        String url = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.PRODUCT.getFilePath());
        System.out.println(url);
        return url;
    }


}

