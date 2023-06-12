package cn.ecnu.service;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.DeleteDTO;
import cn.ecnu.model.dto.PasswordDTO;
import cn.ecnu.model.dto.ProductDTO;
import cn.ecnu.model.vo.PageResult;
import cn.ecnu.model.vo.ProductVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.ecnu.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @intro: Product表服务接口
 * @author: zachary
 * @version: 1.0
 */
 
public interface ProductService extends IService<Product> {

    /*
     * @intro:
     * @author: zachary
     * @param: condition 条件
     * @return: PageResult<ProductVO> 农产品分页列表
     **/
     PageResult<ProductVO> listProductVO(ConditionDTO condition);

     /*
      * @intro:
      * @author: zachary
      * @param: product
      * @return: void
      **/
     void addProduct(ProductDTO product);

     /*
      * @intro: 删除农产品信息
      * @author: zachary
      * @param: productIdList
      * @return: void
      **/
    void deleteProduct(List<Integer> productIdList);

    /*
     * @intro: 回收或恢复农作物
     * @author: zachary
     * @param: delete
     * @return: void
     **/
    void updateProductDelete(DeleteDTO delete);

    /*
     * @intro: 修改农产品信息
     * @author: zachary
     * @param: product
     * @return: void
     **/
    void updateProduct(ProductDTO product);

    /*
     * @intro: 上传图片
     * @author: zachary
     * @param: file
     * @return: String
     **/
    String saveProductImages(MultipartFile file);
}
