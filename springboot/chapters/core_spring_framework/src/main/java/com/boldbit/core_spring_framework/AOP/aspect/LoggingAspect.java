package com.boldbit.core_spring_framework.AOP.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.boldbit.core_spring_framework.AOP.service.MyService.performTask(..))")
    public void logBefore() {
        System.out.println("Logging before performing task");
    }

    @After("execution(* com.boldbit.core_spring_framework.AOP.service.MyService.performTask(..))")
    public void logAfter() {
        System.out.println("Logging after performing task");
    }

    @AfterReturning("execution(* com.boldbit.core_spring_framework.AOP.service.MyService.performTask(..))")
    public void logAfterReturning() {
        System.out.println("Logging after task completes successfully");
    }

    @AfterThrowing("execution(* com.boldbit.core_spring_framework.AOP.service.MyService.performTask(..))")
    public void logAfterThrowing() {
        System.out.println("Logging after task throws an exception");
    }

    @Around("execution(* com.boldbit.core_spring_framework.AOP.service.MyService.performTask(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Logging around before performing task");
        try {
            joinPoint.proceed();
        } finally {
            System.out.println("Logging around after performing task");
        }
    }
}
