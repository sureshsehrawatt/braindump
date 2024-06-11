package com.boldbit.core_spring_framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.boldbit.core_spring_framework.AOP.service.MyService;
import com.boldbit.core_spring_framework.dependencyInjection.scopeUsgae.PrototypeScopedCounter;
import com.boldbit.core_spring_framework.dependencyInjection.scopeUsgae.SingletonScopedCounter;

@SpringBootApplication
public class CoreSpringFrameworkApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CoreSpringFrameworkApplication.class, args);


		// ---------------------- Dependency Injection Section ------------------------
		SingletonScopedCounter singletonScopedCounter1 = context.getBean(SingletonScopedCounter.class);
		SingletonScopedCounter singletonScopedCounter2 = context.getBean(SingletonScopedCounter.class);
		SingletonScopedCounter singletonScopedCounter3 = context.getBean(SingletonScopedCounter.class);
		System.out.println("Singleton objects count: " + singletonScopedCounter1.instanceCount);
		
		PrototypeScopedCounter prototypeScopedCounter1 = context.getBean(PrototypeScopedCounter.class);
		PrototypeScopedCounter prototypeScopedCounter2 = context.getBean(PrototypeScopedCounter.class);
		PrototypeScopedCounter prototypeScopedCounter3 = context.getBean(PrototypeScopedCounter.class);
		System.out.println("Prototype objects count: " + prototypeScopedCounter1.instanceCount);
		
		// ---------------------- AOP Section ------------------------
		MyService myService = context.getBean(MyService.class);
		System.out.println();
        myService.performTask();


		
	}
}
