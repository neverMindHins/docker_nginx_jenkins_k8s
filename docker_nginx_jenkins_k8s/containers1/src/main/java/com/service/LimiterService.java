package com.service;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LimiterService {

    private static final long CAPACITY = 5; // 令牌桶容量
    private static final String KEY = "rate_limiter:token_bucket";

    public LimiterService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;

    }

    @Autowired
    private RateLimiter rateLimiter;

    private RedisTemplate redisTemplate;

    public String limiter() {

        // 尝试获取令牌，如果获取失败则进行限流处理
        if (!rateLimiter.tryAcquire()) {
            return "请求频率过高，请稍后再试！";
        }

        // 处理正常请求逻辑
        return "正常响应";
    }

















}
