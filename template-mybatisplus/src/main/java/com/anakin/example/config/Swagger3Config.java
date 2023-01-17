package com.anakin.example.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @description: Swagger 配置类
 * @author: anakin
 * @date 2020.8.20 10:53
 */
@EnableOpenApi
@Configuration
public class Swagger3Config {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(applicationName + "接口文档")
                .description("更多接口相关问题咨询服务开发者")
                .contact(new Contact("anakin", "https://anakinskyw.github.io/", "anakinsky0808@gmail.com"))
                .version("1.0")
                .build();
    }
}
