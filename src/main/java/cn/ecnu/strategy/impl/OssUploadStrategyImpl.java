package cn.ecnu.strategy.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import cn.ecnu.config.properties.OssProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;


/**
 * oss上传策略
 *
 * @author zachary
 */
@Slf4j
@Service("ossUploadStrategyImpl")
public class OssUploadStrategyImpl extends AbstractUploadStrategyImpl {

    @Autowired
    private OssProperties ossProperties;

    @Override
    public Boolean exists(String path,String fileName) {
        //错误发生在这
        Configuration cfg = new Configuration(Region.autoRegion());
        Auth auth = Auth.create(ossProperties.getAccessKey(), ossProperties.getSecretKey());
        UploadManager uploadManager = new UploadManager(cfg);
        BucketManager bucketManager = new BucketManager(auth,cfg);

        try {
            // 查询文件元数据
            FileInfo fileInfo = bucketManager.stat(ossProperties.getBucket(),fileName);
            long fileSize = fileInfo.fsize;
            System.out.println("File size: " + fileSize);
            return true;
        } catch (QiniuException ex) {
            //此时不存在
            return false;
        }
    }

    @Override
    public void upload(String path, String fileName, InputStream inputStream) {

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        UploadManager uploadManager = new UploadManager(cfg);

        path = path.substring(1,path.length());
        System.out.println(path);
        //默认不指定key的情况下，以文件内容的hash值作为文件名  可以在此指定 路径/文件名称 +后缀显示文件类型
        String key = path+fileName;

        try {
            //创建凭证
            Auth auth = Auth.create(ossProperties.getAccessKey(), ossProperties.getSecretKey());
            String upToken = auth.uploadToken(ossProperties.getBucket());

            try {
                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                //返回的路径
                String imageUrl = ossProperties.getImageUrl();
                imageUrl = "";
                System.out.println("错误在于："+imageUrl);
                imageUrl = ossProperties.getUrl()+"/"+key;
                ossProperties.setImageUrl(imageUrl);
                System.out.println("url : "+ossProperties.getImageUrl());
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                log.error("Error Message:" + ex.getMessage());
            }
        } catch (Exception ex) {
            log.error("Error Message:" + ex.getMessage());
        }
    }
    

    @Override
    public String getFileAccessUrl(String filePath) {
        return ossProperties.getImageUrl();
    }



}
