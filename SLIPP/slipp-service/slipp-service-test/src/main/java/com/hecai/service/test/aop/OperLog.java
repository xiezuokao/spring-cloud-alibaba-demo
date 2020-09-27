package com.hecai.service.test.aop;

import java.lang.annotation.*;

/**
 * 自定义操作日志注解
 * @author wu
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface OperLog {
    String operName() default ""; // 操作名称
    String operType() default "";  // 操作类型
}