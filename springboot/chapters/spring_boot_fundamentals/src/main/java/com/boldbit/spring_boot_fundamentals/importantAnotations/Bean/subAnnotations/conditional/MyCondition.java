package com.boldbit.spring_boot_fundamentals.importantAnotations.Bean.subAnnotations.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MyCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // Condition logic here
        return true; // Example condition always returns true
    }
}