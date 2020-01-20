package com.rivers.configjdbcclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ConfigJdbcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigJdbcClientApplication.class, args);
    }

}
