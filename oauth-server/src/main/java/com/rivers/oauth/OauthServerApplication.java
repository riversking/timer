package com.rivers.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 由于使用pom/gradle引入service jar包，如果不加basePackage，会找不到包所在路径
 * @author riversking
 */
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.rivers.user.api.client")
@MapperScan(basePackages = {"com.rivers.oauth.mapper"})
@RefreshScope
@SpringBootApplication
public class OauthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthServerApplication.class, args);
    }
}
