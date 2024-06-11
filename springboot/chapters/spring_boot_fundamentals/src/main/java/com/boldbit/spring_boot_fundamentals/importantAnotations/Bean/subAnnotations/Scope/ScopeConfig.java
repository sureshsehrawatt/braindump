package com.boldbit.spring_boot_fundamentals.importantAnotations.Bean.subAnnotations.Scope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.boldbit.spring_boot_fundamentals.importantAnotations.Bean.usage.MyBean;

@Configuration
public class ScopeConfig {

    @Bean(name = "demo")
    @Scope("prototype")
    public MyBean myBean() {
        return new MyBean();
    }
}