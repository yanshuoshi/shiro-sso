package com.example.project_a.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAController {

    @GetMapping("/test")
    public Object testCx(){
        return "登录成功";
    }

}
