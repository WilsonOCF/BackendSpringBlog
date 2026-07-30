package com.example.spring_curso.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    };

    @RequestMapping("/bye")
    public String bye(){
        return "Goodbye, World";
    }
}