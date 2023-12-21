package com.controller;


import com.service.HelloService;
import com.service.PipelineExample;
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


    @GetMapping("/sendMessage")
    public String sendMessage(String message,String channel) {
        helloService.sendMessage(message,channel);
        return "ok";
    }

    @GetMapping("/hello")
    public String hello() {
        redisTemplate.opsForValue().set("key_auto","value_auto");
        String key_auto = (String)redisTemplate.opsForValue().get("key_auto");
        System.out.println(key_auto);

        return "Hello, World!port-8081"+key_auto;
    }


    @GetMapping("/pipeline")
    public void pipeline() {

        CallFunctional functional = new CallFunctional();
        functional.call((ListManipulator<Integer>) ArrayList::size);
        pipelineExample.executePipeline();


    }


}
