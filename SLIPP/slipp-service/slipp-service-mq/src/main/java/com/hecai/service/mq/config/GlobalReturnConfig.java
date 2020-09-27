package com.hecai.service.mq.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hecai.common.config.restdata.Code;
import com.hecai.common.config.restdata.RestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * controller全局返回值统一封装
 *
 * @EnableWebMvc表示完全自己控制mvc配置
 * @WebMvcAutoConfiguration springmvc默认配置
 */

@EnableWebMvc
@Configuration
public class GlobalReturnConfig extends WebMvcAutoConfiguration {

    /**
     * 处理指定包下的控制器类
     */
    @RestControllerAdvice(basePackages = "com.hecai.service.mq.controller")
    static class ResultResponseAdvice implements ResponseBodyAdvice<Object> {
        @Autowired
        private ObjectMapper objectMapper;
        @Override
        public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
            return true;
        }

        @Override
        public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
            if (body instanceof RestData) {
                return body;
            }else if(body instanceof String){ //String属于特殊情况，需要单独处理，否则会报错
                try {
                    return objectMapper.writeValueAsString(new RestData(Code.INFO_SUCCEED, body));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return new RestData(Code.INFO_ERROR, body);
                }
            }
            RestData restData = new RestData(Code.INFO_SUCCEED, body);
            return restData;
        }
    }
}