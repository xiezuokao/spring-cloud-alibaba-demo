package com.hecai.service.test.aop;//package com.hecai.web.test.aop;
//
//import com.hecai.admin.util.HttpContextUtils;
//import com.hecai.entity.Result;
//import com.hecai.user.feign.LogRecordFeign;
//import com.hecai.user.pojo.LogRecord;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//import java.util.Date;
//
//
///**
// * 切面处理类，操作日志异常日志记录处理
// */
//@Aspect
//@Component
//public class OperLogAspect {
//
//
//    @Autowired
//    LogRecordFeign logRecordFeign;
//
//    /**
//     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
//     */
//    @Pointcut("@annotation(com.hecai.admin.aop.OperLog)")
//    public void operLogPoinCut() {
//    }
//
//    /**
//     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
//     */
//    @Pointcut("execution(* com.hecai.admin.controller..*.*(..))")
//    public void operExceptionLogPoinCut() {
//    }
//
//    /**
//     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
//     *
//     * @param joinPoint 切入点
//     * @param keys      返回结果
//     */
//    @AfterReturning(value = "operLogPoinCut()", returning = "keys")
//    public void saveOperLog(JoinPoint joinPoint, Object keys) {
//        // 获取RequestAttributes
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        // 从获取RequestAttributes中获取HttpServletRequest的信息
//        HttpServletRequest request = (HttpServletRequest) requestAttributes
//                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
//
//        try {
//
//            // 从切面织入点处通过反射机制获取织入点处的方法
//            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//            // 获取切入点所在的方法
//            Method method = signature.getMethod();
//            // 获取操作
//            OperLog opLog = method.getAnnotation(OperLog.class);
//            if (opLog != null) {
//                String operName = opLog.operName();
//                String operType = opLog.operType();
//
//                // 请求的参数
//                String parameter = HttpContextUtils.getBodyString(request);
//
//                String userId = request.getHeader("userId");
//                String proxyId = request.getHeader("proxyId");
//
//                LogRecord logRecord = new LogRecord() {{
//                    setIp(request.getHeader("ipAddr"));
//                    setName(operName);
//                    setParameter(parameter);
//                    setType(operType);
//                    setUri(request.getRequestURI());
//                    setCreateTime(new Date());
//                }};
//                if (userId!=null){
//                    logRecord.setUserId(Integer.parseInt(userId));
//                }
//                if (proxyId!=null){
//                    logRecord.setProxyId(Integer.parseInt(proxyId));
//                }
//
//                Result result = logRecordFeign.add(logRecord);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
//     *
//     * @param joinPoint 切入点
//     * @param e         异常信息
//     */
//    @AfterThrowing(pointcut = "operExceptionLogPoinCut()", throwing = "e")
//    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
//
//
//    }
//
//
//    /**
//     * 转换异常信息为字符串
//     *
//     * @param exceptionName    异常名称
//     * @param exceptionMessage 异常信息
//     * @param elements         堆栈信息
//     */
//    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
//        StringBuffer strbuff = new StringBuffer();
//        for (StackTraceElement stet : elements) {
//            strbuff.append(stet + "\n");
//        }
//        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
//        return message;
//    }
//}