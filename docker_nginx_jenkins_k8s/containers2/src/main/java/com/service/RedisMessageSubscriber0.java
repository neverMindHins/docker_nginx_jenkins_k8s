package com.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class RedisMessageSubscriber0 implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel());
        String body = new String(message.getBody());
        System.out.println("0 Received message: " + body + "\nfrom channel: " + channel);
        // 处理接收到的消息
        System.out.println("0 处理接收到的消息:"+body + "\nfrom channel: " + channel);
    }
}