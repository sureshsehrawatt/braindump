package com.boldbit.core_spring_framework.dependencyInjection.scopeUsgae;

public class Counter {
    public static int instanceCount = 0;

    public Counter() {
        instanceCount++;
    }
}
