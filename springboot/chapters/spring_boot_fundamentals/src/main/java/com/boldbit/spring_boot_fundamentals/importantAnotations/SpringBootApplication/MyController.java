package com.boldbit.spring_boot_fundamentals.importantAnotations.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MyController {

    @GetMapping("/")
    public String hello() {
        return "Hello, Spring Boot!";
    }
}