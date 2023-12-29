package com.controller;


import com.service.*;
import com.util.functional.CallFunctional;
import com.util.functional.ListManipulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
class HelloController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private HelloService helloService;

    @Autowired
    private PipelineExample pipelineExample;

    @Autowired
    private LimiterService limiterService;

    @Autowired
    TokenBucketRateLimiter tokenBucketRateLimiter;

    @Autowired
    RedisTransactionService redisTransactionService;

    @GetMapping("/sendMessage")
    public String sendMessage(String message,String channel) {
        helloService.sendMessage(message,channel);
        return "ok";
    }

    @GetMapping("/hello")
    public String hello(String key,String value) {
        redisTemplate.opsForValue().set(key,value);
        value = (String)redisTemplate.opsForValue().get(key);
        System.out.println(value);

        return "Hello, World!\n"+key+":"+value;
    }


    @GetMapping("/pipeline")
    public void pipeline() {

        CallFunctional functional = new CallFunctional();
        functional.call((ListManipulator<Integer>) ArrayList::size);
        pipelineExample.executePipeline();


    }

    @GetMapping("/limiter")
    public String limit() {

        //return limiterService.limiter();
        String flag = tokenBucketRateLimiter.allowRequest() ? "ok" : "no";
        System.out.println(flag);
        return flag;
    }

    @GetMapping("/transaction")
    public String transaction() {
        redisTransactionService.performTransaction();
        return "";
    }


}
