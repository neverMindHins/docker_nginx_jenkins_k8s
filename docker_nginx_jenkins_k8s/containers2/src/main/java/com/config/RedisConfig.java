package com.config;

import com.service.RedisMessageSubscriber0;
import com.service.RedisMessageSubscriber1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        RedisMessageSubscriber0 subscriber = redisMessageSubscriber();

        // 设置消息监听器和订阅的频道
        container.addMessageListener(subscriber, new ChannelTopic("channelMessage_1"));
        container.addMessageListener(subscriber, new ChannelTopic("channelMessage_2"));

        RedisMessageSubscriber1 subscriber1 = redisMessageSubscriber1();
        container.addMessageListener(subscriber1,new ChannelTopic("channelMessage_1"));

        return container;
    }


    public RedisMessageSubscriber0 redisMessageSubscriber() {
        return new RedisMessageSubscriber0();
    }


    public RedisMessageSubscriber1 redisMessageSubscriber1() {
        return new RedisMessageSubscriber1();
    }
}