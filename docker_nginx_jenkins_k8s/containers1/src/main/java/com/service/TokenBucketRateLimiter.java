package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenBucketRateLimiter {

    private static final String KEY = "rate_limiter:token_bucket";
    private static final long CAPACITY = 5; // 令牌桶容量
    private static final long RATE = 20; // 发放令牌速率
    private static final long INTERVAL = 1000 / RATE; // 发放令牌的时间间隔

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean allowRequest() {
        long currentTimestamp = System.currentTimeMillis();
        long tokenBucketSize = redisTemplate.opsForValue().increment(KEY);

        if (tokenBucketSize <= CAPACITY) {
            return true; // 令牌桶未满，允许请求
        } else {
            long expiration = currentTimestamp + (tokenBucketSize - CAPACITY) * INTERVAL;
            redisTemplate.expire(KEY, (tokenBucketSize - CAPACITY) * INTERVAL, TimeUnit.MILLISECONDS);
            return false; // 令牌桶已满，限制请求
        }
    }
}