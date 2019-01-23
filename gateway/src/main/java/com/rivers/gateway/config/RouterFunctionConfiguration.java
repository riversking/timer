package com.rivers.gateway.config;

import com.rivers.gateway.handle.ImageCodeHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class RouterFunctionConfiguration {

	private final ImageCodeHandler imageCodeHandler;

//	@Bean
//	public RouterFunction routerFunction() {
//		return RouterFunctions.route(
//			RequestPredicates.GET("/fallback")
//				.and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), imageCodeHandler)
//			.andRoute(RequestPredicates.path("/code")
//				.and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), imageCodeHandler);
//
//	}


	@Bean
	public RouterFunction<ServerResponse> testFunRouterFunction() {
		RouterFunction<ServerResponse> route = RouterFunctions.route(
				RequestPredicates.path("/testfun"),
				request -> ServerResponse.ok().body(BodyInserters.fromObject("hello")));
		return route;
	}


}
