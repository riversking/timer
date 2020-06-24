package com.rivers.gateway.filter;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.collect.Maps;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
            if (serverWebExchange.getRequest().getPath().toString().contains("login")) {
                return Mono.just(body);
            }
            String authorization = Objects.requireNonNull(headers.get("Authorization")).stream().findFirst().orElse(null);
            String token = StrUtil.subAfter(authorization, "Bearer ", false);
            String claims = JwtHelper.decode(token).getClaims();
            JSONObject json = JSON.parseObject(claims);
            LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) body;
            if (MapUtil.isEmpty(map)) {
                map = Maps.newLinkedHashMap();
            }
            map.put("id", json.get("id"));
            map.put("userId", json.get("userId"));
            log.info("请求体: {}", JSON.toJSONString(map));
            return Mono.just(map);
        };
    }

}
