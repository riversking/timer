package com.rivers.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.rivers.gateway.util.AesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class RequestEncryptionGlobalFilter implements GlobalFilter, Ordered {

    private static final int LOG_RECORD_SERVER_REQUEST_PARAM_FILTER_ORDER = -10;

    private GatewayFilter delegate;

    @PostConstruct
    public void init() {
        this.delegate = new ModifyRequestBodyGatewayFilterFactory().apply(this.getConfig());
    }

    private ModifyRequestBodyGatewayFilterFactory.Config getConfig() {
        ModifyRequestBodyGatewayFilterFactory.Config cf = new ModifyRequestBodyGatewayFilterFactory.Config();
        cf.setRewriteFunction(Object.class, Object.class, getRewriteFunction());
        return cf;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return delegate.filter(exchange, chain);
    }

    @Override
    public int getOrder() {
        return LOG_RECORD_SERVER_REQUEST_PARAM_FILTER_ORDER;
    }

    private RewriteFunction<Object, Object> getRewriteFunction() {
        return (serverWebExchange, body) -> {
            // 这里的body就是请求体参数, 类型是LinkedHashMap, 可以根据需要转成JSON
            HttpHeaders headers = serverWebExchange.getRequest().getHeaders();
            String authorization = Objects.requireNonNull(headers.get("Authorization")).stream().findFirst().orElse(null);
            String token = StrUtil.subAfter(authorization, "Bearer ", false);
            String claims = JwtHelper.decode(token).getClaims();
            JSONObject json = JSON.parseObject(claims);

            log.info("请求体: {}", JSON.toJSONString(body));
            return Mono.just(body);
        };
    }

    public static void main(String[] args) {
        String str = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiJ9.Inn6lChKUZeeSGc79_eesxfy5bP4gwGcdg50nuy-5gLrtZ39OWfTfFgw9O61fJyW9hGOEAiKS58NGIrvHvL6q8JpmHi2iHUZP6nFp3FUdYO8PUKULmjkr_0zGhxVR5U-Qj00cMOnhRT7BrTJCw4iLd8JFGg6lZB70rsNCCwB7BOR1z60lBZkK2QLuXOy7mVjiEnbDqrNtLsG7ahnxrQF7qyi6G0HbmAKeN5ltJ7S2WDhW64ZpemLJh_O2KXd5TbIJpr2Bp9lnXvmYH6HbMIWK-pwDSk0DB5qMMEDO4V34j-f9G51x4wPTin13M9CdJUIEBq-idfS88PrGgc9ylr4eQ";
//        str = str.substring(str.trim().indexOf("Bearer") + 1);
        System.out.println(StrUtil.subAfter(str, "Bearer ", false));
        str = "标准答案dao@ABCDABCDABCD";
        str = str.substring(str.indexOf("标准答案") + 1, str.length());
        System.out.println(str);
    }

}
