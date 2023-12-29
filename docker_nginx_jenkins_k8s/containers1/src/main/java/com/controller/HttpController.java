package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HttpController {

    @GetMapping("/api/myEndpoint")
    public String myEndpoint(HttpServletRequest request) {
        // 获取客户端的IP地址
        String clientIp = request.getRemoteAddr();

        // 获取客户端的主机名（如果可用）
        String clientHost = request.getRemoteHost();

        // 获取客户端的端口号
        int clientPort = request.getRemotePort();

        // 获取请求的URL
        String requestUrl = request.getRequestURL().toString();

        // 获取请求的方法（GET、POST等）
        String requestMethod = request.getMethod();

        // 获取请求的头部信息
        String userAgent = request.getHeader("User-Agent");

        // 处理正常请求逻辑
        return "IP: " + clientIp + ", Host: " + clientHost + ", Port: " + clientPort
                + ", URL: " + requestUrl + ", Method: " + requestMethod
                + ", User-Agent: " + userAgent;
    }
}