package com.boldbit.spring_boot_fundamentals.importantAnotations.Bean.subAnnotations.DependsOn;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.boldbit.spring_boot_fundamentals.importantAnotations.Bean.usage.MyBean;

@Configuration
public class AppConfig {

    @Bean(name = "beanOne")
    public MyBean beanOne() {
        return new MyBean();
    }

    @Bean(name = "beanTwo")
    @DependsOn("beanOne")
    public MyBean beanTwo() {
        return new MyBean();
    }
}