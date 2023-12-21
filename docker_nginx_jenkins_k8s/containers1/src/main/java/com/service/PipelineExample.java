package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PipelineExample {
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public PipelineExample(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void executePipeline() {
        List<Object> results = redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            // 在这里添加多个命令
            connection.stringCommands().set("key1".getBytes(), "value1".getBytes());
            connection.stringCommands().get("key1".getBytes());
            connection.hashCommands().hSet("hash1".getBytes(), "field1".getBytes(), "value2".getBytes());
            connection.hashCommands().hGet("hash1".getBytes(), "field1".getBytes());
            // 返回 null，因为结果在 Pipeline 执行后通过 Response 对象获取
            return null;
        });


        // 获取每个命令的执行结果
        String setResult = (String) results.get(1);
        String getResult = (String) results.get(3);
        Long hsetResult = (Long) results.get(5);
        String hgetResult = (String) results.get(7);

        // 打印结果
        System.out.println("SET result: " + setResult);
        System.out.println("GET result: " + getResult);
        System.out.println("HSET result: " + hsetResult);
        System.out.println("HGET result: " + hgetResult);
    }
}