# Spring Boot

Spring Boot is a framework for building production-ready applications in Java with minimal effort. It simplifies the setup of new Spring applications by offering defaults for configuration and a suite of production-ready features like metrics, health checks, and externalized configuration.

# Table of Contents

## Core Spring Framework

- [Dependency Injection](#dependency-injection)
- [Spring IoC](#spring-ioc)
- [Spring AOP](#spring-aop)
- [Spring Beans, Bean Life Cycle, and Bean Scope](#spring-beans)

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

## Spring Beans, Bean Life Cycle, and Bean Scope

### Java Beans

Java Beans are classes that encapsulate multiple objects into a single object (the bean). They are designed to create reusable software components for Java.

### Spring Beans

**Spring Beans** are objects that are managed by the Spring IoC (Inversion of Control) container. They form the backbone of a Spring application. A bean is an object that is instantiated, assembled, and managed by a Spring IoC container.

- **Bean Definition**: Configuration metadata that tells the Spring container how to instantiate, configure, and assemble the beans in your application. This metadata can be provided via XML configuration, Java annotations, or Java configuration classes.
- **IoC Container**: The Spring container is responsible for managing the lifecycle and configuration of application objects. The container gets its instructions on what objects to instantiate, configure, and assemble by reading configuration metadata.

### Analogy

- **Farmer**: Spring Framework
- **Farmland**: Spring Container
- **Seeds/Beans**: Spring Beans
- **Cultivating**: Spring Processes

### Spring Bean Life Cycle

The life cycle of a Spring Bean is managed by the Spring IoC container. Here is the sequence of steps involved in the life cycle of a Spring Bean:

1. **Instantiation**: The container finds the bean's definition and instantiates the bean.
2. **Populate Properties**: The container populates all properties specified in the bean definition using dependency injection.
3. **Bean Name Awareness**: If the bean implements `BeanNameAware`, the container passes the bean's ID to the `setBeanName` method.
4. **Bean Factory Awareness**: If the bean implements `BeanFactoryAware`, the container passes the `BeanFactory` to the `setBeanFactory` method.
5. **Pre-Initialization**: If there are any `BeanPostProcessors`, the `postProcessBeforeInitialization` method is called.
6. **Initialization**: If the bean implements `InitializingBean`, its `afterPropertiesSet` method is called. If the bean has an `init-method`, that method is called.
7. **Post-Initialization**: If there are any `BeanPostProcessors`, the `postProcessAfterInitialization` method is called.
8. **Bean Ready for Use**: The bean is now ready to be used.
9. **Destruction**: If the bean implements `DisposableBean`, its `destroy` method is called. If the bean has a `destroy-method`, that method is called.

### Example of a Bean Life Cycle

```java
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ExampleBean implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {
    private String name;

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name Aware: " + name);
        this.name = name;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        System.out.println("Bean Factory Aware");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("Initializing Bean");
    }

    public void customInit() {
        System.out.println("Custom Init Method");
    }

    @Override
    public void destroy() {
        System.out.println("Disposable Bean");
    }

    public void customDestroy() {
        System.out.println("Custom Destroy Method");
    }
}

@Configuration
public class AppConfig {
    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public ExampleBean exampleBean() {
        return new ExampleBean();
    }
}
```

### Spring Bean Scope

The scope of a bean defines the life cycle and visibility of that bean in the contexts in which it is used. Spring supports several types of bean scopes:

1. **Singleton** (Default): Only one instance of the bean is created per Spring IoC container. All requests for the bean will return the same instance.
2. **Prototype**: A new instance is created every time the bean is requested.
3. **Request**: A single instance is created for each HTTP request. Only valid in a web-aware Spring ApplicationContext.
4. **Session**: A single instance is created for each HTTP session. Only valid in a web-aware Spring ApplicationContext.
5. **Global Session**: A single instance is created for the global HTTP session. Only valid in a web-aware Spring ApplicationContext.
6. **Application**: A single instance is created per ServletContext. Only valid in a web-aware Spring ApplicationContext.
7. **WebSocket**: A single instance is created per WebSocket.

### Example of Bean Scopes

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("singleton")
    public ExampleBean singletonBean() {
        return new ExampleBean();
    }

    @Bean
    @Scope("prototype")
    public ExampleBean prototypeBean() {
        return new ExampleBean();
    }
}
```

- @Bean on Methods: Used to define the creation of a specific bean. The method's return value is registered as a bean.
- Class-Level Annotations (@Configuration): Indicate that a class can be used by the Spring container to create and configure beans. The class itself is a source of bean definitions, not a bean.

### Using Scopes in Spring Boot

Spring Boot makes it easy to configure bean scopes using annotations. Here is an example of using different scopes:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Component
@Scope("singleton")
public class SingletonBean {
    // Singleton scoped bean
}

@Component
@Scope("prototype")
public class PrototypeBean {
    // Prototype scoped bean
}

@Component
@RequestScope
public class RequestScopedBean {
    // Request scoped bean
}

@Component
@SessionScope
public class SessionScopedBean {
    // Session scoped bean
}
```

### Example of Using Bean Scopes in a Controller

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private SingletonBean singletonBean;

    @Autowired
    private PrototypeBean prototypeBean;

    @Autowired
    private RequestScopedBean requestScopedBean;

    @Autowired
    private SessionScopedBean sessionScopedBean;

    @GetMapping("/beans")
    public String getBeans() {
        return "Singleton: " + singletonBean +
               "\nPrototype: " + prototypeBean +
               "\nRequest Scoped: " + requestScopedBean +
               "\nSession Scoped: " + sessionScopedBean;
    }
}
```

### Conclusion

Understanding Spring Beans, their lifecycle, and the different scopes they can have is fundamental to developing robust Spring applications. This deep dive covered the basics of Spring Beans, the lifecycle they go through, and the various scopes available to manage their lifecycle and visibility within the application context. By leveraging these features, you can create more modular, reusable, and maintainable code.

## Spring Boot Starters

**Overview:**
- Spring Boot Starter simplifies and accelerates Spring application development by providing pre-configured dependencies and auto-configuration.

**Key Features:**
- Pre-packaged Dependencies: Includes curated dependencies tailored for specific use cases or technologies.
- Auto-Configuration: Spring Boot configures dependencies based on defaults and conventions, reducing manual configuration.
- Ready-to-Use Templates: Starters often include template code to kickstart project development.
- Technology-Centric: Offers starters for various technologies, allowing developers to pick the one that suits their project needs.
- Easy Customization: While providing a quick start, Spring Boot allows extensive customization to adapt to specific requirements.
- Enables developers to focus on application logic rather than configuration details.

**Why Spring Boot Starter Projects?**
- Without starters, developers would need to manually select and configure dependencies, leading to increased complexity and configuration overhead.

**Example Scenario:**
- Consider developing a web application using Spring MVC without starters:
  - Requires selecting compatible versions of frameworks such as Spring MVC, Jackson Databind, Hibernate Validator, and Log4j.
  - Involves configuring settings for dispatcher servlet, view resolver, error pages, web jars, etc.
  - Similar setup needed for JPA, including providing jars and configuring datasource, entity manager, transaction manager, etc.

**Spring Boot Starter Web:**
- Offers essential features for developing web applications or RESTful services.
- Simplifies project setup by providing compatible dependencies and auto-configuration.
- Example of usage: Spring Initializr to create a project with Spring Boot Starter Web.

**Dependencies:**
- Includes core Spring dependencies, Spring MVC, Jackson for JSON binding, Hibernate Validator for validation, Tomcat as the embedded servlet container, and logging frameworks.

**Auto-Configuration:**
- Automatically configures key components like dispatcher servlet, error pages, and web jars.
- Eliminates the need for manual configuration, enabling quick project setup and development.

**Other Starter Project Options:**
- Various starter projects cater to different use cases:
  - spring-boot-starter-web-services
  - spring-boot-starter-test
  - spring-boot-starter-jdbc
  - spring-boot-starter-hateoas
  - spring-boot-starter-security
  - spring-boot-starter-data-jpa
  - spring-boot-starter-cache
  - spring-boot-starter-data-rest
- Additional starters cover technical aspects like monitoring, servlet containers, and logging.

## Important Annotations

## `@SpringBootApplication`

`@SpringBootApplication` is a meta-annotation that combines several annotations to simplify the configuration of Spring Boot applications. Let's dive deep into its characteristics, components, and usage:

### Characteristics:

1. **Combination of Annotations**: `@SpringBootApplication` combines three annotations: `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.

2. **Simplified Configuration**: It provides a streamlined approach to configuring Spring Boot applications by combining common annotations into a single meta-annotation.

3. **Convention over Configuration**: `@SpringBootApplication` follows the convention over configuration principle, providing sensible defaults and reducing the need for explicit configuration.

### Components:

1. **@Configuration**: Indicates that the class declares one or more `@Bean` methods and can be processed by the Spring container to generate bean definitions and service requests.

2. **@EnableAutoConfiguration**: Enables Spring Boot's auto-configuration mechanism, which automatically configures the Spring application context based on the classpath and the presence of specific beans.

3. **@ComponentScan**: Scans the specified package and its sub-packages for components, such as `@Component`, `@Service`, `@Repository`, `@Controller`, etc., and registers them with the Spring container.

#### Let's dive deeper into each of the three annotations contained within `@SpringBootApplication` — `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan` — and explore any relevant sub-annotations:

### 1. `@Configuration`:

- **Purpose**: Indicates that the class declares one or more `@Bean` methods and can be processed by the Spring container to generate bean definitions and service requests.

- **Sub-Annotations**:
  - `@Bean`: Indicates a method produces a bean to be managed by the Spring container.
  - `@Import`: Imports additional configuration classes.
  - `@PropertySource`: Specifies properties file location(s) to be loaded into the environment.

### 2. `@EnableAutoConfiguration`:

- **Purpose**: Enables Spring Boot's auto-configuration mechanism, which automatically configures the Spring application context based on the classpath and the presence of specific beans.

- **Sub-Annotations**:
  - `@ConditionalOnClass`: Specifies that the auto-configuration should be applied only if certain classes are present in the classpath.
  - `@ConditionalOnMissingClass`: Specifies that the auto-configuration should be applied only if certain classes are not present in the classpath.
  - `@ConditionalOnBean`: Specifies that the auto-configuration should be applied only if certain beans are present in the application context.
  - `@ConditionalOnMissingBean`: Specifies that the auto-configuration should be applied only if certain beans are not present in the application context.
  - `@ConditionalOnProperty`: Specifies that the auto-configuration should be applied based on the presence or value of certain properties in the environment.

### 3. `@ComponentScan`:

- **Purpose**: Scans the specified package and its sub-packages for components, such as `@Component`, `@Service`, `@Repository`, `@Controller`, etc., and registers them with the Spring container.

- **Sub-Annotations**:
  - `@Filter`: Allows customization of component scanning filters to include or exclude certain classes.
  - `@ComponentScan.Filter`: Specifies a filter for component scanning.

### Example:

```java
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAutoConfiguration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.example")
public class MyAppConfiguration {
    // Configuration code
}
```

In this example, `@Configuration` indicates that the class declares bean definitions. `@EnableAutoConfiguration` enables Spring Boot's auto-configuration mechanism, while `@ComponentScan` instructs Spring to scan the `com.example` package for components to manage. Each of these annotations can be further customized using sub-annotations to fine-tune the configuration and behavior of the Spring application.

### Usage:

1. **Basic Usage**: Annotate the main class of the Spring Boot application with `@SpringBootApplication` to enable auto-configuration, component scanning, and bean definition.

    ```java
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;

    @SpringBootApplication
    public class MyApplication {

        public static void main(String[] args) {
            SpringApplication.run(MyApplication.class, args);
        }
    }
    ```

2. **Customization**: `@SpringBootApplication` can be customized using its attributes to override default behaviors, such as disabling specific auto-configurations or specifying additional component scan base packages.

    ```java
    @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
    public class MyApplication {
        // Application code
    }
    ```

### Components Enabled by @SpringBootApplication:

1. **Auto-Configuration**: Enables Spring Boot's auto-configuration mechanism to automatically configure the Spring application context based on the classpath and predefined conditions.

2. **Component Scanning**: Scans the specified package and its sub-packages for Spring components, such as `@Component`, `@Service`, `@Repository`, `@Controller`, etc., and registers them with the Spring container.

3. **Bean Definition**: Allows the declaration of `@Bean` methods within the application class, which are processed by the Spring container to generate bean definitions.

### Conclusion:

`@SpringBootApplication` is a powerful meta-annotation that combines three commonly used annotations (`@Configuration`, `@EnableAutoConfiguration`, `@ComponentScan`) into a single annotation, simplifying the configuration of Spring Boot applications. It promotes convention over configuration and provides a streamlined approach to building Spring Boot applications with sensible defaults and minimal configuration overhead.

## `@Bean`

The `@Bean` annotation in Spring is used to declare a method as a bean producer method. When Spring processes a configuration class, it looks for `@Bean` annotations and executes the corresponding methods to produce bean instances. Let's dive deeper into the characteristics, usage, and sub-annotations of `@Bean`:

### Characteristics:

1. **Method-Level Annotation**: `@Bean` is a method-level annotation, meaning it is applied to methods within a `@Configuration` class.

2. **Manual Bean Declaration**: It allows developers to manually declare bean instances by writing factory methods.

3. **Customization**: Methods annotated with `@Bean` can be customized to provide bean instances with specific configurations, dependencies, or initialization logic.

### Usage:

1. **Basic Usage**: Declare a method annotated with `@Bean` within a `@Configuration` class. The method's return type defines the bean type, and its name defines the bean's name.

    ```java
    @Configuration
    public class AppConfig {

        @Bean
        public MyBean myBean() {
            return new MyBean();
        }
    }
    ```

2. **Dependency Injection**: `@Bean` methods can accept parameters, allowing for dependency injection of other beans or values.

    ```java
    @Configuration
    public class AppConfig {

        @Bean
        public MyService myService(MyRepository repository) {
            return new MyService(repository);
        }
    }
    ```

3. **Customization**: `@Bean` methods can include custom logic to configure or initialize the bean instance.

    ```java
    @Configuration
    public class AppConfig {

        @Bean
        public DataSource dataSource() {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
            dataSource.setUsername("username");
            dataSource.setPassword("password");
            return dataSource;
        }
    }
    ```

### Sub-Annotations:

There are no sub-annotations directly associated with `@Bean`, but it can be used in conjunction with other annotations such as:

- `@Conditional`: Allows conditional bean registration based on certain conditions.
- `@Scope`: Specifies the scope of the bean (e.g., singleton, prototype, request, session, etc.).
- `@Lazy`: Indicates that the bean should be lazily initialized.
- `@DependsOn`: Specifies dependencies between beans.

Certainly! Here are examples demonstrating the usage of `@Conditional`, `@Scope`, `@Lazy`, and `@DependsOn` annotations:

### 1. `@Conditional`:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @Conditional(MyCondition.class)
    public MyBean myBean() {
        return new MyBean();
    }
}
```

```java
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
```

### 2. `@Scope`:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("prototype")
    public MyPrototypeBean myPrototypeBean() {
        return new MyPrototypeBean();
    }
}
```

### 3. `@Lazy`:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {

    @Bean
    @Lazy
    public MyLazyBean myLazyBean() {
        return new MyLazyBean();
    }
}
```

### 4. `@DependsOn`:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class AppConfig {

    @Bean(name = "beanOne")
    public MyBean beanOne() {
        return new MyBean();
    }

    @Bean(name = "beanTwo")
    @DependsOn("beanOne")
    public MyDependentBean beanTwo() {
        return new MyDependentBean();
    }
}
```

In these examples:
- `MyCondition` represents a custom condition that determines whether the bean should be registered based on certain conditions.
- `MyPrototypeBean` is a bean with prototype scope, meaning a new instance is created each time it is requested.
- `MyLazyBean` is a lazily initialized bean, meaning it is created only when first requested.
- `MyDependentBean` depends on `beanOne` and will be initialized only after `beanOne` has been initialized.

### Conclusion:

`@Bean` is a powerful annotation in Spring that allows for manual declaration and customization of bean instances within configuration classes. It provides flexibility in defining bean dependencies, configurations, and initialization logic, making it a fundamental building block for configuring Spring applications.