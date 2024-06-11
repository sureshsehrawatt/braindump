package com.boldbit.spring_boot_fundamentals.importantAnotations.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Application configuration class
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.boldbit")
public class MyApp {

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}