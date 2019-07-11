package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringCloudService2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudService2Application.class, args);
    }

}
