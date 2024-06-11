package com.boldbit.core_spring_framework.AOP.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    public void performTask() {
        System.out.println("Performing task...");
    }
}
