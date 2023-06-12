package cn.ecnu.service;

import cn.ecnu.entity.SiteConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 网站配置业务接口
 *
 * @author zachary
 */
public interface SiteConfigService extends IService<SiteConfig> {

    /**
     * 获取网站配置
     *
     * @return 网站配置
     */
    SiteConfig getSiteConfig();

    /**
     * 更新网站配置
     *
     * @param siteConfig 网站配置
     */
    void updateSiteConfig(SiteConfig siteConfig);

}
