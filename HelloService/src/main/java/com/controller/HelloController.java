package com.controller;

import com.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/user/{id}")
    public User hello(Long id) throws Exception {

        Random random = new Random();
        int sleepTime = random.nextInt(3000);
        logger.info("sleepTime " + sleepTime);
        Thread.sleep(sleepTime);

        List<ServiceInstance> instances = discoveryClient.getInstances("helloService");

        for (ServiceInstance serviceInstance : instances) {
            logger.info("host:" + serviceInstance.getHost() + "service_id:" + serviceInstance.getServiceId());
        }

        User user = new User();
        user.id = 1L;
        user.userName = "张三";

        return user;
    }

}
