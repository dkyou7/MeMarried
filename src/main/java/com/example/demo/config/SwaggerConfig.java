package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {
        "com.example.demo.controller"
})
public class SwaggerConfig {
 
    /** swagger */
    @Bean
    public Docket ShopApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Memo API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                //.paths(PathSelectors.ant("/memo/**"))
                //.paths(PathSelectors.ant("/auth/**"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.ShopApiInfo())
                .tags(   new Tag("memo-controller", "Memo API")
                       , new Tag("user-controller", "Member API")
                );
 
    }
 
    private ApiInfo ShopApiInfo() {
        return new ApiInfoBuilder()
                .title("Memo API")
                .description("Memo API")
                .termsOfServiceUrl("https://www.naver.com")
                .version("1.0")
                .build();
    }
}