package com.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!8080";
    }
}