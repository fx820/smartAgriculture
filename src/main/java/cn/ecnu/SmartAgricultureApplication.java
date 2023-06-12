package cn.ecnu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @intro: 项目启动类
 * @author: zachary
 * @version: 1.0
 */

@SpringBootApplication
@MapperScan("cn.ecnu.mapper")
public class SmartAgricultureApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartAgricultureApplication.class,args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
