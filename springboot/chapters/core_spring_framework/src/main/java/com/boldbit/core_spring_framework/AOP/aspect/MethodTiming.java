package com.boldbit.core_spring_framework.AOP.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodTiming {
    @Around("execution(* com.boldbit.core_spring_framework.AOP.service.MyService.performTask(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();

        Object proceed = joinPoint.proceed();

        long executionTime = System.nanoTime() - start;

        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ns");
        return proceed;
    }
}
