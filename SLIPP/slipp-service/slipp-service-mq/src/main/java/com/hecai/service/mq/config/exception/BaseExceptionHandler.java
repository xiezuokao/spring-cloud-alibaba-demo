package com.hecai.service.mq.config.exception;


import com.hecai.common.config.exception.MyException;
import com.hecai.common.config.restdata.Code;
import com.hecai.common.config.restdata.RestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 全局统一异常处理类
 * @ControllerAdvice注解，全局捕获异常类，只要作用在@RequestMapping上，所有的异常都会被捕获。
 */
@ControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    //处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public RestData BindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        log.warn("请求参数错误：{}",message);
        return new RestData(Code.INFO_ERROR_400, message,null);
    }

    //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public RestData ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        log.warn("请求参数错误：{}",message);
        return new RestData(Code.INFO_ERROR_400, message,null);
    }

    //处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestData MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        log.warn("请求参数错误：{}",message);
        return new RestData(Code.INFO_ERROR_400, message,null);
    }

    /**
     * 处理我的自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public RestData MyExceptionHandler(MyException e) {
        log.warn("MyException：{}",e.getMessage());
        return new RestData(Code.INFO_ERROR, e.getMessage(),null);
    }

    /**
     * 处理系统出错异常
     * @param e //如果铺获的是这个异常(Exception)，就执行该方法
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestData error(Exception e){
        log.error("程序运行出现异常");
        e.printStackTrace();
        log.error(e.fillInStackTrace().toString());
        return new RestData(Code.INFO_ERROR,"网络繁忙！请稍后重试！",null);
    }

}
