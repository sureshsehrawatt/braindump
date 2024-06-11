package com.boldbit.core_spring_framework.dependencyInjection.scopeUsgae;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeScopedCounter extends Counter {
}
