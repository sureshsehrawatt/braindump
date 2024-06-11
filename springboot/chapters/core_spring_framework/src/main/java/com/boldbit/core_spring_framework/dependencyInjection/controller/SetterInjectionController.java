package com.boldbit.core_spring_framework.dependencyInjection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boldbit.core_spring_framework.dependencyInjection.service.GreetingService;

@RestController
@RequestMapping("/di/set")
public class SetterInjectionController {
    private GreetingService greetingService;

    @Autowired
    public void setterInjection(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/setter")
    String greetings() {
        return greetingService.greet() + "Setter!";
    }
}
