package com.api;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {
    String key(); // Redis存储的Key
    long limit(); // 限流阈值
    long timeout(); // 限流时间窗口
    TimeUnit timeUnit() default TimeUnit.SECONDS; // 时间单位，默认为秒
    String message() default "Request limit exceeded"; // 超过限流阈值时的提示信息
}