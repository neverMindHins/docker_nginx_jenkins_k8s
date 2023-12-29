package com.service.aop;

import com.api.RateLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class RateLimitAspect {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Around(value = "@annotation(rateLimit)")
    public Object around(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String key = rateLimit.key();
        long limit = rateLimit.limit();
        long timeout = rateLimit.timeout();
        TimeUnit timeUnit = rateLimit.timeUnit();
        String message = rateLimit.message();

        // 生成Redis存储的Key
        String redisKey = "rate_limit:" + key;

        // 获取当前时间戳
        long currentTimestamp = System.currentTimeMillis();

        // 计算限流时间窗口的过期时间
        long expiration = currentTimestamp + timeUnit.toMillis(timeout);

        // 获取当前请求的IP地址或其他标识符
        String identifier = getIdentifier();

        // 判断当前请求是否超过限流阈值
        long count = redisTemplate.opsForValue().increment(redisKey, 1);
        if (count == 1) {
            // 第一次请求，设置过期时间

            redisTemplate.expireAt(redisKey, new Date(expiration));
        } else if (count > limit) {
            // 超过限流阈值，限制请求
            throw new RuntimeException(message);
        }

        // 执行目标方法
        Object result = joinPoint.proceed();

        return result;
    }

    // 获取当前请求的IP地址或其他标识符
    private String getIdentifier() {
        // 根据具体场景获取请求的IP地址或其他标识符
        // 例如从HttpServletRequest中获取IP地址或从Spring Security的上下文获取用户信息等
        // 这里只是示例，需要根据实际情况进行实现
        return "127.0.0.1";
    }
}