package com.boldbit.spring_boot_fundamentals.importantAnotations.Bean.subAnnotations.Lazy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class LazyConfig {
    
    @Bean
    @Lazy
    public MyLazyBean myLazyBean() {
        return new MyLazyBean();
    }
}
