package com.boldbit.spring_boot_fundamentals.importantAnotations.Bean.usage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Basic {
    @Bean(name = "demo2")
    public MyBean myBean() {
        return new MyBean();
    }
}
