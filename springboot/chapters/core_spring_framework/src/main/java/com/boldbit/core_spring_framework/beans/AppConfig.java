package com.boldbit.core_spring_framework.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public ExampleBean exampleBean() {
        return new ExampleBean();
    }
}
