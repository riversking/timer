package com.riversking.eurekaadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@EnableEurekaClient
@EnableAdminServer
@Configuration
@EnableAutoConfiguration
public class EurekaAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaAdminApplication.class, args);
    }
}
