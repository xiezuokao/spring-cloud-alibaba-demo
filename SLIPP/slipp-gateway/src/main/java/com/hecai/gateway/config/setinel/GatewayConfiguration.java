//package com.hecai.gateway.config.setinel;
//
//import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.codec.ServerCodecConfigurer;
//import org.springframework.web.reactive.result.view.ViewResolver;
//
//import javax.annotation.PostConstruct;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * sentinel断路器配置
// */
////@Configuration
//public class GatewayConfiguration {
//
//    private final List<ViewResolver> viewResolvers;
//    private final ServerCodecConfigurer serverCodecConfigurer;
//
//    public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
//                                ServerCodecConfigurer serverCodecConfigurer) {
//        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
//        this.serverCodecConfigurer = serverCodecConfigurer;
//    }
//
////    @Bean
////    @Order(Ordered.HIGHEST_PRECEDENCE)
////    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
////        // Register the block exception handler for Spring Cloud Gateway.
////        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
////    }
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public JsonSentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
//        // Register the block exception handler for Spring Cloud Gateway.
//        return new JsonSentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
//    }
//
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public GlobalFilter sentinelGatewayFilter() {
//        return new SentinelGatewayFilter();
//    }
//
//    @PostConstruct
//    public void doInit() {
//        initGatewayRules();
//    }
//
//    /**
//     * 配置限流规则
//     */
//    private void initGatewayRules() {
////        Set<GatewayFlowRule> rules = new HashSet<>();
////        rules.add(new GatewayFlowRule("slipp-microservice-test")
////            .setCount(1000) // 限流阈值qps
////            .setIntervalSec(1) // 统计时间窗口，单位是秒，默认是 1 秒
////        );
////        rules.add(new GatewayFlowRule("slipp-microservice-mq")
////                .setCount(2) // 限流阈值qps
////                .setIntervalSec(1) // 统计时间窗口，单位是秒，默认是 1 秒
////        );
////        GatewayRuleManager.loadRules(rules);
//    }
//
//}