package com.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFullback")
    public String helloService(){

        String body = restTemplate.getForEntity("http://HELLOSERVICE/hello", String.class).getBody();

        return body;
    }

    public String helloFullback(){

        return "错误";
    }

}
