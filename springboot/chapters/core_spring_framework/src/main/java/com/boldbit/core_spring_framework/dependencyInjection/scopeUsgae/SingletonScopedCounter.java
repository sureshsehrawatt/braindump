package com.boldbit.core_spring_framework.dependencyInjection.scopeUsgae;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SingletonScopedCounter extends Counter{
    
}
