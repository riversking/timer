package com.rivers.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author riverskingking
 */
@Component
@Log4j2
public class WrapperResponseGlobalFilter implements GlobalFilter, Ordered {

    /**
     * HttpHeaders headers = exchange.getRequest().getHeaders();
     * List<String> list = headers.get("Authorization");
     * if (list == null) {
     * ServerHttpResponse response = exchange.getResponse();
     * JSONObject message = new JSONObject();
     * message.put("code", "401");
     * message.put("msg", "鉴权失败");
     * byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
     * DataBuffer buffer = response.bufferFactory().wrap(bits);
     * response.setStatusCode(HttpStatus.UNAUTHORIZED);
     * //指定编码，否则在浏览器中会中文乱码
     * response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
     * return response.writeWith(Mono.just(buffer));
     * }
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取response的 返回数据
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (exchange.getRequest().getPath().toString().contains("export")) {
                    return super.writeWith(body);
                }
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        //释放掉内存
                        DataBufferUtils.release(dataBuffer);
                        //responseData就是下游系统返回的内容,可以查看修改
                        String responseData = new String(content, StandardCharsets.UTF_8);
                        log.info("响应内容:{}", responseData);
                        log.info("getStatusCode() {}", getStatusCode());
                        byte[] uppedContent;
                        if (Objects.equals(getStatusCode(), HttpStatus.OK) || Objects.equals(getStatusCode(), HttpStatus.UNAUTHORIZED)) {
                            uppedContent = new String(content, StandardCharsets.UTF_8).getBytes();
                        } else {
                            JSONObject jsonObject = JSON.parseObject(responseData);
                            jsonObject.put("code", jsonObject.get("status"));
                            jsonObject.remove("status");
                            log.info("响应内容:{}", responseData);
                            uppedContent = jsonObject.toJSONString().getBytes(StandardCharsets.UTF_8);
                        }
                        return bufferFactory.wrap(uppedContent);
                    }));
                }
                return super.writeWith(body);
            }
        };
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    @Override
    public int getOrder() {
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 1;
    }
}
