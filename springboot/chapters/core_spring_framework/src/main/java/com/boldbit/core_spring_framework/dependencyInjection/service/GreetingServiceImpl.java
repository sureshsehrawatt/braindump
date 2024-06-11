package com.boldbit.core_spring_framework.dependencyInjection.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {
    public String greet() {
        return "Hello, ";
    }
}
