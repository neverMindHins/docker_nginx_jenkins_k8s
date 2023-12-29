package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedisTransactionService {

    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisTransactionService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Transactional(rollbackFor = Exception.class)
    public void performTransaction() {
        // 在事务中执行Redis操作
        redisTemplate.opsForValue().set("key5", "value1");
        redisTemplate.opsForValue().set("key6", "value2");

        int i = 10/0;
        //抛出异常事务没有回滚


        // 执行其他业务逻辑
        
        // 如果抛出异常，事务会回滚
        // throw new RuntimeException("Transaction error");
    }
}