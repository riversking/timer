package com.rivers.gateway.config;

import com.rivers.gateway.handle.ImageCodeHandler;
import com.rivers.gateway.handle.ImageHandle;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 路由配置信息
 * @author riverskingking
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class RouterFunctionConfiguration {


	@Autowired
    private ImageHandle imageHandle;


	@Bean
	public RouterFunction<ServerResponse> getImage() {
		return RouterFunctions.route(
                RequestPredicates.path("/api/v1/image/{filename}"),
                imageHandle);
	}


}
