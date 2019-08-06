package com.commanod;

import com.entity.User;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

public class MyCommanod extends HystrixCommand<User> {

    private RestTemplate restTemplate;

    private Long id;

    public MyCommanod(RestTemplate restTemplate, Setter setter, Long id) {
        super(setter);
        this.id = id;
        this.restTemplate = restTemplate;
    }


    @Override
    protected User run() throws Exception {
        return restTemplate.getForObject("http://HELLOSERVICE/user/{id}", User.class, id);
    }
}
