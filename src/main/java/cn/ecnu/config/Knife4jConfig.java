package cn.ecnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Collections;

/**
 * Knife4j配置
 *
 * @author zachary
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(Collections.singleton("https"))
                .host("192.168.150.128")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.ecnu.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //TODO 修改自己信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("博客API文档")
                .description("基于SpringBoot + Vue开发的博客项目")
                .termsOfServiceUrl("192.168.150.128/api")
                .contact(new Contact("阿白", "https://github.com/azhiyaya", "2739902096@qq.com"))
                .version("1.0")
                .build();
    }

}