package cn.ecnu.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * oss配置属性
 *
 * @author zachary
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upload.oss")
public class OssProperties {

    /**
     * 七牛云url
     */
    private  final String url = "http://rv5avdw47.bkt.clouddn.com";

    /**
     * 图片url路径
     */
    private String imageUrl;

    /**
     * accessKey 访问key
     */
    private String accessKey;

    /**
     * secretKey 私有key
     */
    private String secretKey;

    /**
     * bucket名称
     */
    private String bucket;
}