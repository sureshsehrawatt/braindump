package com.boldbit.core_spring_framework.dependencyInjection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boldbit.core_spring_framework.dependencyInjection.service.GreetingService;

@RestController()
@RequestMapping("/di/cons")
public class ConstructorInjectionController {
    private final GreetingService greetingService;
    String name;

    @Autowired
    public ConstructorInjectionController(GreetingService greetingService){
        this.greetingService = greetingService;
    }
    
    public ConstructorInjectionController(GreetingService greetingService, String name){
        this.greetingService = greetingService;
        this.name = name;
    }

    @GetMapping("/constructor")
    String greetings(){
        return greetingService.greet()+ "Constructor!";
    }
}
