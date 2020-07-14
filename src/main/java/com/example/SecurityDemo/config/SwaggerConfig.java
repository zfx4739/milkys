package com.example.SecurityDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author ：zfx
 * @date ：Created in 2020/7/8 12:43
 * @description：Swagger配置类
 * @modified By：
 * @version: 1.0$
 */
//加入到配置类中
@Configuration
//开启Swagger2配置
@EnableSwagger2
public class SwaggerConfig {
    //配置了Sewaggerbean的docket实例
    @Bean
    public Docket docket(){
     /*   RequestHandlerSelector  //配置扫描包的方式
        RequestHandlerSelectors.any()  扫描全部包
        RequestHandlerSelectors.basePackage()   扫描指定的包
        RequestHandlerSelectors.none()    都不扫描
        RequestHandlerSelectors.withClassAnnotation() 扫描类上的注解
        */
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                //.enable(false)    是否启动swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.SecurityDemo.controller"))
                //.paths(PathSelectors.ant())     //不扫描
                .build();
    }
    /**
    * @description Swagger2配置信息
    *@params
    * @return
    * @author  zfx
    * @date  2020/7/8 13:00
    *
    */
    private ApiInfo apiInfo(){

        Contact DEFAULT_CONTACT = new Contact("周峰喜", "www.baidu.com", "1198038482@qq.ocm");
                return new ApiInfo(
                        //标题
                        "周峰喜的Swagger日志",
                        //简介
                        "即使再小的帆也能远航",
                        //版本号
                        "1.0",
                        //团队
                        "urn:tos", DEFAULT_CONTACT,
                        "Apache 2.0",
                        "http://www.apache.org/licenses/LICENSE-2.0",
                        new ArrayList());
    }
    }

