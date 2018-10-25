package com.riversking.zipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin2.server.internal.EnableZipkinServer;

@EnableEurekaClient
@EnableZipkinServer
@SpringBootApplication
public class ZipkinserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinserverApplication.class, args);
    }
}
