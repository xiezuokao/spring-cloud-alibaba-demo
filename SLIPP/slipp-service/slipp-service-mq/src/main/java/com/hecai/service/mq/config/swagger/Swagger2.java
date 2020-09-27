package com.hecai.service.mq.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger自动生成api文档 swagger-ui.html
 * @author xzk
 * 
 */
@Configuration
@EnableSwagger2

@Conditional(SwaggerEnableCondition.class)
@ApiIgnore
@RestController
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
    	
        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("information-release-mq")
                .apiInfo(apiInfo())
                //.globalOperationParameters(pars)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hecai.service.mq.controller"))
                .paths(PathSelectors.any())
                //.paths(PathSelectors.regex("^(?!auth).*$"))
                .build()
                .securitySchemes(securitySchemes())//非全局、无需重复输入的Head参数（Token）配置
                .securityContexts(securityContexts());//非全局、无需重复输入的Head参数（Token）配置
    }

    private List<ApiKey> securitySchemes() {
    	List<ApiKey> list=new ArrayList<>();
    	list.add(new ApiKey("Authorization", "Authorization", "header"));
        return list;
    }

    private List<SecurityContext> securityContexts() {
    	List<SecurityContext> list=new ArrayList<>();
    	list.add(SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return list;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> list=new ArrayList<>();
        list.add(new SecurityReference("Authorization", authorizationScopes));
        return list;
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().license("")
                .title("云南体彩信息发布系统-MQ API文档说明")
                .description("")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
    
    @RequestMapping("/")//重定向url
    public ModelAndView forwardSwagger() {
    	ModelAndView mvc=new ModelAndView();
    	mvc.setViewName("redirect:/swagger-ui.html");
		return mvc;
    }
}