package com.controller;

import com.entity.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.service.ConsumerService;
import com.commanod.MyCommanod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.concurrent.Future;

@RestController
public class ConsumerController {

    private Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public User getString() throws Exception{

        User user = new MyCommanod(restTemplate,
                HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupName"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("CommandKey"))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("PoolKey")),
                1L).execute();

        logger.info("执行成功");
        return user;
    }

    @GetMapping("/annotation")
    @com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
    public User getUser(){
        return restTemplate.getForObject("http://HELLOSERVICE/user/{id}", User.class, 1L);
    }

    @GetMapping("/annotation/async")
    @com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
    public Future<User> getUserAsync(){
       return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://HELLOSERVICE/user/{id}", User.class, 1L);
            }
        };
    }

    @GetMapping("/observable")
    public Observable<User> getObservable() throws Exception{

        Observable<User> user = new MyCommanod(restTemplate,
                HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupName"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("CommandKey"))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("PoolKey")),
                1L).observe();

        logger.info("执行成功");

        return user;
    }


}
