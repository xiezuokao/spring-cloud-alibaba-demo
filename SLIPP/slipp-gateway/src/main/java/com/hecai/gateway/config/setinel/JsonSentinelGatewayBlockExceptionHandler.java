//package com.hecai.gateway.config.setinel;
//
//import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
//import com.alibaba.csp.sentinel.slots.block.BlockException;
//import com.alibaba.csp.sentinel.util.function.Supplier;
//import com.alibaba.fastjson.JSONObject;
//import com.hecai.gateway.config.restdata.RestData;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.codec.HttpMessageWriter;
//import org.springframework.http.codec.ServerCodecConfigurer;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import org.springframework.web.reactive.result.view.ViewResolver;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebExceptionHandler;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//
//public class JsonSentinelGatewayBlockExceptionHandler implements WebExceptionHandler {
//
//    private List<ViewResolver> viewResolvers;
//    private List<HttpMessageWriter<?>> messageWriters;
//    private final Supplier<ServerResponse.Context> contextSupplier = () -> {
//        return new ServerResponse.Context() {
//            @Override
//            public List<HttpMessageWriter<?>> messageWriters() {
//                return JsonSentinelGatewayBlockExceptionHandler.this.messageWriters;
//            }
//
//            @Override
//            public List<ViewResolver> viewResolvers() {
//                return JsonSentinelGatewayBlockExceptionHandler.this.viewResolvers;
//            }
//        };
//    };
//
//    public JsonSentinelGatewayBlockExceptionHandler(List<ViewResolver> viewResolvers, ServerCodecConfigurer serverCodecConfigurer) {
//        this.viewResolvers = viewResolvers;
//        this.messageWriters = serverCodecConfigurer.getWriters();
//    }
//
//    private Mono<Void> writeResponse(ServerResponse response, ServerWebExchange exchange) {
//        return response.writeTo(exchange, (ServerResponse.Context)this.contextSupplier.get());
//    }
//
//    /**
//     * 断路器返回提示信息
//     * @param exchange
//     * @param ex
//     * @return
//     */
//    @Override
//    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
//        ServerHttpResponse serverHttpResponse = exchange.getResponse();
//        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
//        byte[] datas = new JSONObject(){{put("code",403);put("message","网络出了点小问题，请稍后重试！");}}.toJSONString().getBytes(StandardCharsets.UTF_8);
//        DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(datas);
//        return serverHttpResponse.writeWith(Mono.just(buffer));
//    }
//
//    private Mono<ServerResponse> handleBlockedRequest(ServerWebExchange exchange, Throwable throwable) {
//        return GatewayCallbackManager.getBlockHandler().handleRequest(exchange, throwable);
//    }
//}