package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    /**
     * 로드 벨런싱은 / 경로를 통해 동작하는지 확인한다.
     * @return
     */
    @GetMapping("/")
    public String healthCheck(){
        return "The Service is up and running healthy!";
    }
}
