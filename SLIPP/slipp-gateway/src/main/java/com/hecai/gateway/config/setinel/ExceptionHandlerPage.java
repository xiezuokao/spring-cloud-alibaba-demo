//package com.hecai.gateway.config.setinel;
//
//import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
//import com.alibaba.csp.sentinel.slots.block.BlockException;
//import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
//import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
//import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
//import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
//import com.alibaba.fastjson.JSONObject;
//import com.hecai.gateway.config.restdata.Code;
//import com.hecai.gateway.config.restdata.RestData;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 自定义sentinel异常返回信息
// */
//public class ExceptionHandlerPage implements UrlBlockHandler {
//    @Override
//    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
//        // BlockException 异常接口，其子类为Sentinel五种规则异常的实现类
//        // AuthorityException 授权异常
//        // DegradeException 降级异常
//        // FlowException 限流异常
//        // ParamFlowException 参数限流异常
//        // SystemBlockException 系统负载异常
//        RestData data = new RestData();
//        if (e instanceof AuthorityException) {
//            data = new RestData(Code.INFO_GATEWAY_2002);
//        } else if (e instanceof DegradeException) {
//            data = new RestData(Code.INFO_GATEWAY_2003);
//        } else if (e instanceof FlowException) {
//            data = new RestData(Code.INFO_GATEWAY_2004);
//        } else if (e instanceof ParamFlowException) {
//            data = new RestData(Code.INFO_GATEWAY_2005);
//        } else if (e instanceof SystemBlockException) {
//            data = new RestData(Code.INFO_GATEWAY_2006);
//        }
//
//        httpServletResponse.setContentType("application/json;charset=utf-8");
//        httpServletResponse.getWriter().write(JSONObject.toJSONString(data));
//    }
//}
