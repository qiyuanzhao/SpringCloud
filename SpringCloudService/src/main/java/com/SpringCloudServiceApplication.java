package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
@EnableCircuitBreaker
public class SpringCloudServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudServiceApplication.class, args);
    }

}
