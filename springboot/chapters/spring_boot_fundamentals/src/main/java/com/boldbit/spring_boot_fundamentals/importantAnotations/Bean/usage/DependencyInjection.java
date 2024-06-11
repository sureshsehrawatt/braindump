package com.boldbit.spring_boot_fundamentals.importantAnotations.Bean.usage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependencyInjection {

    @Bean(name = "demo3")
    public MyBean myBean(MyBean repository) {
        return new MyBean(repository);
    }
}
