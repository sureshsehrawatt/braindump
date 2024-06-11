package com.boldbit.spring_boot_fundamentals.importantAnotations.Bean.subAnnotations.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.boldbit.spring_boot_fundamentals.importantAnotations.Bean.usage.MyBean;

@Configuration
public class ConditionalImpl {
    @Bean
    @Conditional(MyCondition.class)
    public MyBean myBean() {
        return new MyBean();
    }
}
