# Spring Boot

Spring Boot is a framework for building production-ready applications in Java with minimal effort. It simplifies the setup of new Spring applications by offering defaults for configuration and a suite of production-ready features like metrics, health checks, and externalized configuration.

# Table of Contents

## Core Spring Framework
- [Dependency Injection](#dependency-injection)
- [Spring IoC](#spring-ioc)
- [Spring AOP](#spring-aop)
- [Spring Beans](#spring-beans)
- [Spring Bean Life Cycle](#spring-bean-life-cycle)
- [Spring Bean Scope](#spring-bean-scope)
- [Spring Configuration Styles (XML, Java-based, Annotation-based)](#spring-configuration-styles)

## Spring Boot Fundamentals
- [Spring Boot Starters](#spring-boot-starters)
- [Important Annotations](#important-annotations)
  - [`@SpringBootApplication`](#springbootapplication)
  - [`@Bean`](#bean)
  - [`@Component`](#component)
  - [`@Service`](#service)
  - [`@Component vs @Service`](#component-vs-service)
  - [`@Autowired`](#autowired)
  - [`@RestController`](#restcontroller)
  - [`@Configuration`](#configuration)
  - [`@Transactional`](#transactional)
  - [`@EnableTransactionManagement`](#enabletransactionmanagement)
  - [`@MongoTransactionManager`](#mongotransactionmanager)
  - [`@Entity`](#entity)
  - [`@Document`](#document)
  - [`@DBRef`](#dbref)
  - [`@Value`](#value)
  - [`@PostConstruct`](#postconstruct)
  - [`@Scheduled`](#scheduled)
  - [`@EnableWebSecurity`](#enablewebsecurity)
- [Autoconfiguration](#autoconfiguration)
- [Elegant Configuration Management](#elegant-configuration-management)
- [Spring Boot Actuator](#spring-boot-actuator)
- [Embedded Server](#embedded-server)
- [ApplicationContext](#applicationcontext)
- [ORM in Spring Boot](#orm-in-spring-boot)

## RESTful Services Development
- [REST APIs](#rest-apis)
  - [GET, POST, PUT, DELETE](#get-post-put-delete)
  - [`@RequestBody`, `@PathVariable`, `@Mappings`](#requestbody-pathvariable-mappings)
- [ResponseEntity](#responseentity)
- [Handling Path Variables and Request Parameters](#handling-path-variables-and-request-parameters)
- [Repository/DAO Layer](#repository-dao-layer)
  - [JPA](#jpa)
  - [JDBC](#jdbc)
  - [MongoDB](#mongodb)
- [MongoTemplate](#mongotemplate)
- [Criteria and Query](#criteria-and-query)

## Web Application Development
- [Spring Security](#spring-security)
  - [Authentication](#authentication)
  - [Authorization](#authorization)
  - [OAuth2](#oauth2)
  - [JWT Authentication](#jwt-authentication)
- [WebSecurityConfigurerAdapter](#websecurityconfigureradapter)

## Microservices Architecture
- [Microservices](#microservices)
- [Microservices Design Patterns](#microservices-design-patterns)
- [Spring Cloud Modules](#spring-cloud-modules)
  - [Gateway](#gateway)
  - [Zuul Proxy](#zuul-proxy)
  - [Config Server](#config-server)
  - [Service Registry and Discovery](#service-registry-and-discovery)
  - [Circuit Breaker](#circuit-breaker)
  - [Distributed Tracing](#distributed-tracing)
  - [OpenFeign](#openfeign)
  - [Sleuth](#sleuth)
  - [Eureka](#eureka)
- [Inter-Service Communication](#inter-service-communication)
- [Fault Tolerance with Resilience4j](#fault-tolerance-with-resilience4j)
- [RestTemplate](#resttemplate)

## Testing and DevOps
- [Testing Spring Boot Application](#testing-spring-boot-application) (`MOCKMVC`, `@SpringBootTest`, `@Test`, `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`, `@BeforeTestClass`, `@AfterTestClass`, `@BeforeTestMethod`, `@AfterTestMethod`, `@BeforeSuite`, `@AfterSuite`, `@ParameterizedTest`, `@Disabled`, `@Mock`, `@InjectMocks`)
- [DevOps (Profiles)](#devops-profiles)
- [Exception Handling](#exception-handling) (Using `@ControllerAdvice` and `@ExceptionHandler`)
  - [Custom Error Responses](#custom-error-responses)
  - [Global Exception Handling](#global-exception-handling)

## Hibernate, Persistence and Database Access
- [Transactions](#transactions)
- [Relationships](#relationships)
- [Entity Lifecycle](#entity-lifecycle)
- [PlatformTransactionManager](#platformtransactionmanager)

## Additional Topics
- [DevTools](#devtools)
- [Hot Reloading](#hot-reloading)
- [Lombok](#lombok) (`@Data`, `@Indexed`, `@NonNull`)
- [Logging and Monitoring](#logging-and-monitoring)
- [Spring Batch](#spring-batch)
- [Scheduling and cron expressions](#scheduling-and-cron-expressions)
- [Email Sending](#email-sending)
- [Redis](#redis)
- [Kafka](#kafka)

## Dependency Injection
Dependency Injection (DI) is a design pattern and a fundamental concept in software engineering, particularly in object-oriented programming (OOP) and inversion of control (IoC) frameworks like Spring. 

### Key Concepts:

1. **Inversion of Control (IoC)**: In traditional programming, the flow of control is determined by the program logic. In IoC, control is inverted: a framework or container is responsible for managing the flow of control. Dependency Injection is a way to implement IoC.

2. **Dependency**: A dependency is an object that another object depends on to perform its work. For example, a `Car` object might depend on an `Engine` object to run.

3. **Injection**: Injection is the process of supplying the dependencies of an object externally, rather than having the object create them itself. This allows for loose coupling between components and facilitates easier testing and maintenance.

### Types of Dependency Injection:

1. **Constructor Injection**: Dependencies are provided to the client through the constructor. This ensures that all required dependencies are available when the object is created.
    ```java
    public class Car {
        private final Engine engine;

        public Car(Engine engine) {
            this.engine = engine;
        }
    }
    ```

2. **Setter Injection**: Dependencies are set through setter methods. This allows for optional dependencies and can make the code more readable.
    ```java
    public class Car {
        private Engine engine;

        public void setEngine(Engine engine) {
            this.engine = engine;
        }
    }
    ```

3. **Field Injection**: Dependencies are directly injected into fields of the class. While convenient, this approach can make testing more difficult and is generally discouraged due to its potential for hidden dependencies.
    ```java
    public class Car {
        @Autowired
        private Engine engine;
    }
    ```

### Benefits of Dependency Injection:

1. **Decoupling**: DI promotes loose coupling between components by removing the responsibility of creating and managing dependencies from the client code.
2. **Testability**: Dependencies can be easily mocked or replaced with test doubles during unit testing, allowing for easier testing of individual components.
3. **Reusability**: Components become more reusable as they are not tightly bound to specific implementations of their dependencies.
4. **Maintainability**: DI makes it easier to change or update dependencies without modifying client code, improving maintainability.

### Dependency Injection in Spring:

In Spring, DI is implemented through the inversion of control (IoC) container, which manages the lifecycle of objects and their dependencies. Spring provides various mechanisms for dependency injection, including constructor injection, setter injection, and field injection, using annotations like `@Autowired`, `@Inject`, and XML configuration.

### Example:

```java
public class Car {
    private final Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

In this example, the `Car` class depends on the `Engine` class. The `Engine` object is supplied to the `Car` constructor through dependency injection.

### Configuring Dependency Injection:

1. **Using Annotations**:
    - **@Component**: Indicates that a class is a Spring-managed bean. It is a generic stereotype for any Spring-managed component.
    - **@Service**: Specialization of `@Component`. It is used to annotate service-layer classes.
    - **@Repository**: Specialization of `@Component`. It is used to annotate DAO or repository classes.
    - **@Controller**: Specialization of `@Component`. It is used to annotate controller classes in Spring MVC.
    - **@Autowired**: Used to automatically wire beans by type.

    ```java
    @Component
    public class Engine { }

    @Service
    public class CarService {
        private final Engine engine;

        @Autowired
        public CarService(Engine engine) {
            this.engine = engine;
        }
    }
    ```

2. **Using Java Configuration**:
    - **@Configuration**: Indicates that a class declares one or more `@Bean` methods and may be processed by the Spring container to generate bean definitions.
    - **@Bean**: Indicates that a method produces a bean to be managed by the Spring container.

    ```java
    @Configuration
    public class AppConfig {
        @Bean
        public Engine engine() {
            return new Engine();
        }

        @Bean
        public CarService carService() {
            return new CarService(engine());
        }
    }
    ```

3. **Using XML Configuration**:
    - Although less common in modern Spring applications, XML configuration is still supported.

    ```xml
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
                               http://www.springframework.org/schema/beans/spring-beans.xsd">
        <bean id="engine" class="com.example.Engine"/>
        <bean id="carService" class="com.example.CarService">
            <constructor-arg ref="engine"/>
        </bean>
    </beans>
    ```

### Best Practices:

1. **Prefer Constructor Injection**: Constructor injection ensures that all required dependencies are provided and allows for immutable objects.
2. **Use @Autowired for Optional Dependencies**: Setter injection can be used for optional dependencies.
3. **Avoid Field Injection**: Field injection can lead to issues with immutability and makes testing harder.
4. **Use Stereotype Annotations**: Use `@Component`, `@Service`, `@Repository`, and `@Controller` to define Spring-managed components.
5. **Keep Configuration Modular**: Use multiple configuration classes for different concerns (e.g., database configuration, service configuration).

### Advanced Topics:

1. **Qualifiers**:
    - When multiple beans of the same type exist, you can use `@Qualifier` to specify which bean to inject.

    ```java
    @Component
    @Qualifier("v8Engine")
    public class V8Engine extends Engine { }

    @Component
    @Qualifier("v6Engine")
    public class V6Engine extends Engine { }

    @Service
    public class CarService {
        private final Engine engine;

        @Autowired
        public CarService(@Qualifier("v8Engine") Engine engine) {
            this.engine = engine;
        }
    }
    ```

2. **Profiles**:
    - Use `@Profile` to define beans for different environments (e.g., development, production).

    ```java
    @Configuration
    @Profile("dev")
    public class DevConfig {
        @Bean
        public Engine engine() {
            return new V6Engine();
        }
    }

    @Configuration
    @Profile("prod")
    public class ProdConfig {
        @Bean
        public Engine engine() {
            return new V8Engine();
        }
    }
    ```

3. **Scopes**:
    - Use `@Scope` to define the scope of a bean (singleton, prototype, request, session, etc.).

    ```java
    @Component
    @Scope("prototype")
    public class PrototypeBean { }
    ```

### Summary:

Dependency Injection is a powerful design pattern that promotes loose coupling, testability, reusability, and maintainability in software applications. It allows components to be easily composed and replaced, leading to more modular and flexible codebases. In Spring, DI is a core principle that enables the creation of robust and scalable applications.

## Spring IoC

Inversion of Control (IoC) is a design principle used to invert the control of object creation and management from the program itself to a framework or container. In Spring, IoC is implemented through the Dependency Injection (DI) pattern, where the Spring IoC container is responsible for instantiating, configuring, and managing the lifecycle of beans (objects).

In simple terms..

**IOC(Inversion of Control)** is a concept that means: Instead of creating objects with the new operator,let the container do it for you.

#### Key Components of Spring IoC

1. **Spring IoC Container**: The core of Spring's IoC is the container, which is responsible for managing the lifecycle and configuration of application objects. The container uses metadata to know what objects to instantiate, configure, and assemble.

2. **Beans**: Beans are the objects that form the backbone of your application and are managed by the Spring IoC container.

3. **Configuration Metadata**: Configuration metadata can be provided in XML, annotations, or Java configuration classes. It tells the container how to instantiate, configure, and assemble the objects in your application.

#### Types of IoC Containers in Spring

1. **BeanFactory**: The simplest container providing the basic functionality. `BeanFactory` uses lazy initialization (beans are instantiated only when they are requested).

2. **ApplicationContext**: A more advanced container that includes all the functionalities of `BeanFactory` plus additional enterprise-specific functionalities such as event propagation, declarative mechanisms to create a bean, and various ways to look up. `ApplicationContext` eagerly loads all singleton beans at startup.

#### Conclusion

Spring IoC is a powerful mechanism for managing the lifecycle and dependencies of objects in your application. By leveraging Spring IoC and DI, you can achieve better modularity, easier testing, and more maintainable code. Understanding the various types of DI, bean scopes, and lifecycle management in Spring IoC will help you design and develop robust Spring applications.

## Spring AOP

Aspect-Oriented Programming (AOP) is a programming paradigm that aims to increase modularity by allowing the separation of cross-cutting concerns (such as logging, transaction management, security, etc.) from the main business logic. Spring AOP is a key component of the Spring framework that enables aspect-oriented programming in Spring applications.

#### Core Concepts of AOP

1. **Aspect**: A modularization of a concern that cuts across multiple classes. An aspect is a class that implements concerns like logging, transaction management, etc.

2. **Join Point**: A point during the execution of a program, such as the execution of a method or the handling of an exception. In Spring AOP, a join point always represents a method execution.

3. **Advice**: Action taken by an aspect at a particular join point. Different types of advice include "around," "before," and "after" advice.

4. **Pointcut**: A predicate that matches join points. Advice is associated with a pointcut expression and runs at any join point matched by the pointcut.

5. **Introduction**: Declaring additional methods or fields on behalf of a type. Spring AOP allows you to introduce new interfaces (and a corresponding implementation) to any advised object.

6. **Target Object**: The object being advised by one or more aspects. Also referred to as the proxied object in Spring AOP terminology.

7. **AOP Proxy**: An object created by the AOP framework to implement the aspect contracts (advise method executions, etc.). In Spring, an AOP proxy can be either a JDK dynamic proxy or a CGLIB proxy.

8. **Weaving**: The process of linking aspects with other application types or objects to create an advised object. This can be done at compile time, load time, or runtime.

#### Types of Advice

1. **Before Advice**: Executed before a join point.
2. **After Advice**: Executed after a join point (regardless of the outcome).
3. **After Returning Advice**: Executed after a join point completes normally.
4. **After Throwing Advice**: Executed if a method exits by throwing an exception.
5. **Around Advice**: Surrounds a join point (such as a method invocation). This is the most powerful kind of advice and can control whether the join point proceeds or not.

#### Example Scenario

Consider a scenario where you want to log the execution of methods in your service layer. We will create an aspect that logs method execution before and after the methods are called.

### Step-by-Step Guide

#### Step 1: Add Dependencies

Ensure you have the Spring AOP dependencies in your `pom.xml` if you are using Maven.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

#### Step 2: Define a Service

```java
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public void performTask() {
        System.out.println("Performing task...");
    }
}
```

#### Step 3: Define an Aspect

Create an aspect class with advice methods.

```java
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.service.MyService.performTask(..))")
    public void logBefore() {
        System.out.println("Logging before performing task");
    }

    @After("execution(* com.example.service.MyService.performTask(..))")
    public void logAfter() {
        System.out.println("Logging after performing task");
    }

    @AfterReturning("execution(* com.example.service.MyService.performTask(..))")
    public void logAfterReturning() {
        System.out.println("Logging after task completes successfully");
    }

    @AfterThrowing("execution(* com.example.service.MyService.performTask(..))")
    public void logAfterThrowing() {
        System.out.println("Logging after task throws an exception");
    }

    @Around("execution(* com.example.service.MyService.performTask(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Logging around before performing task");
        try {
            joinPoint.proceed();
        } finally {
            System.out.println("Logging around after performing task");
        }
    }
}
```

#### Step 4: Main Application

Create a main application class to run the Spring Boot application.

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        MyService myService = context.getBean(MyService.class);
        myService.performTask();
    }
}
```

### Explanation

- **@Aspect**: This annotation indicates that the class is an aspect.
- **@Component**: This annotation makes the aspect class a Spring bean, allowing Spring to detect it and manage its lifecycle.
- **@Before**: The advice annotated with `@Before` will execute before the specified join point.
- **@After**: The advice annotated with `@After` will execute after the specified join point, regardless of its outcome.
- **@AfterReturning**: The advice annotated with `@AfterReturning` will execute after the specified join point completes normally.
- **@AfterThrowing**: The advice annotated with `@AfterThrowing` will execute if the specified join point exits by throwing an exception.
- **@Around**: The advice annotated with `@Around` will surround the specified join point, allowing it to control whether the join point proceeds.

### Output

When you run the application, the following will be printed:

```
Logging before performing task
Logging around before performing task
Performing task...
Logging around after performing task
Logging after task completes successfully
Logging after performing task
```

Another example

Sure! Let's create a simple and clear example of using Spring AOP to log method execution times in a service class.

### Step-by-Step Guide

#### Step 1: Add Dependencies

Ensure you have the Spring AOP dependency in your `pom.xml` if you are using Maven.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

#### Step 2: Define a Service

Create a service class where we will log the execution time of a method.

```java
package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public void performTask() {
        // Simulating a task by sleeping for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task performed");
    }
}
```

#### Step 3: Define an Aspect

Create an aspect class with advice methods to log the execution time.

```java
package com.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example.service.MyService.performTask(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
```

#### Step 4: Main Application

Create a main application class to run the Spring Boot application.

```java
package com.example;

import com.example.service.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        MyService myService = context.getBean(MyService.class);
        myService.performTask();
    }
}
```

### Explanation

- **Service Class (`MyService`)**: This class contains a method `performTask()` which simulates a task by sleeping for one second.
- **Aspect Class (`LoggingAspect`)**: This class is annotated with `@Aspect` and `@Component` to make it a Spring-managed bean and to define it as an aspect. The `logExecutionTime` method is annotated with `@Around` to execute around the `performTask` method. It logs the time taken to execute the method.
- **Main Application (`Application`)**: This class is the entry point of the Spring Boot application. It retrieves the `MyService` bean from the application context and calls the `performTask` method.

### Output

When you run the application, the following will be printed:

```
Task performed
void com.example.service.MyService.performTask() executed in 1001ms
```

This output shows that the `performTask` method was executed, and the aspect logged the execution time of the method.

### Conclusion

Spring AOP is a powerful tool for implementing cross-cutting concerns in a clean, modular way. By using aspects, you can keep your business logic clean and separate from concerns like logging, transaction management, and security. This results in more maintainable and testable code. Understanding the core concepts of Spring AOP and how to use different types of advice can greatly enhance your ability to write clean, modular, and efficient Spring applications.