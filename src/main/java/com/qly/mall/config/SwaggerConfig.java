package com.qly.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.qly.mall.controller";
    public static final String VERSION = "0.0.1";

    //配置swagger的docket的bean实例
    @Bean
    public Docket createApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("LEAVE接口")
                .apiInfo(apiInfo())
//                .enable(flag)
                .select()// 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())// 过滤路径
                .build();
    }

    //配置swagger信息apiInfo
    private ApiInfo apiInfo() {
        Contact contact = new Contact("覃乐怡","https://blog.csdn.net/qq_42677329","836955157@qq.com");

        return new ApiInfoBuilder()
                .title("RV api文档")
                .description("good good study，day day up!!!")
                .termsOfServiceUrl("")
                .version(VERSION)
                .contact(contact)
                .build();
    }
}
