package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Autowired
    private RedisMessagePublisher messagePublisher;

    public void sendMessage(String message,String channel) {

        messagePublisher.publishMessage(channel,message);

    }

}
