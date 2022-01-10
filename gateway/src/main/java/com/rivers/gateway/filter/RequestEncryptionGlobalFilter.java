package com.rivers.gateway.filter;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
        if (exchange.getRequest().getMethod() == HttpMethod.GET) {
            URI uri = exchange.getRequest().getURI();
            StringBuilder query = new StringBuilder();
            String originalQuery = uri.getRawQuery();
            if (org.springframework.util.StringUtils.hasText(originalQuery)) {
                query.append(originalQuery);
                if (originalQuery.charAt(originalQuery.length() - 1) != '&') {
                    query.append("&");
                }
            }
            // 添加查询参数
            query.append("&" + "userId" + "=" + "a");
            // 替换查询参数
            URI newUri = UriComponentsBuilder.fromUri(uri)
                    .replaceQuery(query.toString())
                    .build(true)
                    .toUri();
            ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();
            return chain.filter(exchange.mutate().request(request).build());
        }
        RequestPath path = exchange.getRequest().getPath();
        if (path.toString().contains("upload")) {
            return chain.filter(exchange);
        }
        //post 请求 特殊处理
        return delegate.filter(exchange, chain);
    }

    @Override
    public int getOrder() {
        return LOG_RECORD_SERVER_REQUEST_PARAM_FILTER_ORDER;
    }

    private RewriteFunction<Object, Object> getRewriteFunction() {
        return (serverWebExchange, body) -> {
            // 这里的body就是请求体参数, 类型是LinkedHashMap, 可以根据需要转成JSON
            ServerHttpRequest request = serverWebExchange.getRequest();
            HttpHeaders headers = request.getHeaders();
            if (request.getPath().toString().contains("login")) {
                return Mono.just(body);
            }
            LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) body;
            List<String> list = headers.get("Authorization");
            if (null != list) {
                String authorization = Objects.requireNonNull(list).stream().findFirst().orElse(null);
                String token = StrUtil.subAfter(authorization, "Bearer ", false);
                String claims = JwtHelper.decode(token).getClaims();
                JSONObject json = JSON.parseObject(claims);
                if (MapUtil.isEmpty(map)) {
                    map = Maps.newLinkedHashMap();
                }
                Map<String, Object> user = Maps.newLinkedHashMap();
                user.put("id", json.get("id"));
                user.put("userId", json.get("userId"));
                map.put("user", user);
                log.info("请求体: {}", JSON.toJSONString(map));
                return Mono.just(map);
            }
            return Mono.just(map);
        };
    }

}
