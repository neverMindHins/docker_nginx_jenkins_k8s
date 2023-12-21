package com.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    //@Value("${spring.redis.cluster.nodes}")
    private String[] nodes;

    //@Bean
    //public RedisConnectionFactory redisConnectionFactory() {
    //    RedisClusterConfiguration config = new RedisClusterConfiguration();
    //    for (String node : nodes) {
    //        String[] split = node.split(",");
    //        RedisNode redisNode = new RedisNode(split[0],Integer.parseInt(split[1]));
    //        config.addClusterNode(redisNode);
    //    }
    //    return new JedisConnectionFactory(config);
    //}

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}