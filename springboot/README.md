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

## `@Component`

The `@Component` annotation in Spring is a crucial part of the framework's support for dependency injection and component scanning. It marks a Java class as a Spring component, allowing Spring to automatically detect and register it as a bean within the application context. Here, we'll dive deep into the `@Component` annotation, its subtypes, usage, and related concepts.

### Key Features of `@Component`

1. **Role in Spring**: The `@Component` annotation is a generic stereotype for any Spring-managed component. It is a primary way to indicate that a class should be considered as a Spring bean.

2. **Automatic Detection**: Classes annotated with `@Component` can be automatically detected by Spring's component scanning mechanism. This eliminates the need for explicit bean definitions in XML configuration or `@Configuration` classes.

3. **Meta-Annotation**: `@Component` is a meta-annotation, which means other annotations can be built on top of it. For instance, `@Service`, `@Repository`, and `@Controller` are specialized forms of `@Component`.

### Specialized Stereotype Annotations

These annotations are specialized versions of `@Component`, each serving a distinct role:

- **@Service**: Indicates that a class performs some service, such as business logic or calculations.
- **@Repository**: Marks a class as a Data Access Object (DAO) and provides an abstraction for data access operations.
- **@Controller**: Indicates that a class serves as a Spring MVC controller, handling web requests.

### Usage of `@Component`

#### Basic Example

```java
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    public void performTask() {
        System.out.println("Task performed by MyComponent.");
    }
}
```

#### Spring Boot Application

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ComponentDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ComponentDemoApplication.class, args);
        MyComponent myComponent = context.getBean(MyComponent.class);
        myComponent.performTask();
    }
}
```

### Configuration and Component Scanning

#### Automatic Component Scanning

In a typical Spring Boot application, component scanning is enabled by default through the `@SpringBootApplication` annotation, which implicitly includes `@ComponentScan`. This means Spring Boot will scan the package of the main application class and its sub-packages for components.

#### Customizing Component Scanning

If you need to customize the component scanning behavior, you can use the `@ComponentScan` annotation.

```java
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.components")
public class AppConfig {
    // Custom configuration
}
```

### Sub-Annotations and Their Use Cases

#### @Service

```java
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public void executeService() {
        System.out.println("Service executed by MyService.");
    }
}
```

#### @Repository

```java
import org.springframework.stereotype.Repository;

@Repository
public class MyRepository {

    public void accessData() {
        System.out.println("Data accessed by MyRepository.");
    }
}
```

#### @Controller

```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/perform")
    public String perform() {
        return "Task performed by MyController.";
    }
}
```

### Summary

- **`@Component`**: A general-purpose stereotype annotation for Spring-managed components.
- **Specialized Annotations**: `@Service`, `@Repository`, `@Controller` provide more semantic meaning and are used for specific purposes.
- **Automatic Detection**: Classes annotated with `@Component` and its specialized forms are automatically detected and registered by Spring's component scanning mechanism.
- **Customization**: You can customize component scanning with the `@ComponentScan` annotation if needed.

By understanding and utilizing `@Component` and its specialized annotations effectively, you can create a well-structured, maintainable Spring application.

## `@Service`

The `@Service` annotation in Spring is a specialized form of the `@Component` annotation. It is used to mark a class as a service layer component, which typically contains business logic. Here’s a deep dive into the `@Service` annotation, its purpose, usage, and related concepts.

### Purpose of `@Service`

- **Semantic Clarity**: While `@Service` functions similarly to `@Component`, it provides a clearer semantic meaning, indicating that the class performs service tasks, typically containing business logic.
- **Layered Architecture**: In a layered architecture, `@Service` helps in organizing and distinguishing between different layers, like controllers, services, and repositories.

### Usage of `@Service`

#### Basic Example

```java
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public void executeService() {
        System.out.println("Service executed by MyService.");
    }
}
```

In this example:

- The `MyService` class is annotated with `@Service`, marking it as a service layer component.
- The `executeService` method contains the business logic.

#### Spring Boot Application

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ServiceDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ServiceDemoApplication.class, args);
        MyService myService = context.getBean(MyService.class);
        myService.executeService();
    }
}
```

### Dependency Injection with `@Service`

You can inject a `@Service` annotated bean into other components using `@Autowired`.

#### Example with Dependency Injection

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    private final MyService myService;

    @Autowired
    public MyComponent(MyService myService) {
        this.myService = myService;
    }

    public void performTask() {
        myService.executeService();
    }
}
```

In this example:

- `MyComponent` class is a Spring-managed component that depends on `MyService`.
- The `MyService` instance is injected into `MyComponent` using constructor injection.

### Configuration and Component Scanning

Spring automatically detects classes annotated with `@Service` during component scanning. In a Spring Boot application, this is typically handled by the `@SpringBootApplication` annotation, which includes `@ComponentScan` by default.

#### Custom Component Scanning

If you need to customize component scanning, you can use the `@ComponentScan` annotation in your configuration class.

```java
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.services")
public class AppConfig {
    // Custom configuration
}
```

### Transaction Management with `@Service`

In service layer components, it is common to manage transactions. You can use the `@Transactional` annotation to manage transactions.

#### Example with `@Transactional`

```java
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalService {

    @Transactional
    public void executeTransaction() {
        // Business logic with transactional support
        System.out.println("Transactional service executed.");
    }
}
```

### Specialized Stereotype Annotations

`@Service` is one of the specialized stereotype annotations in Spring, along with `@Controller`, `@Repository`, and `@Component`.

- **`@Controller`**: Used for Spring MVC controllers, handling web requests.
- **`@Repository`**: Indicates a Data Access Object (DAO) component and provides a cleaner separation of data access logic.
- **`@Component`**: A generic stereotype for any Spring-managed component.

### Summary

- **`@Service` Annotation**: Indicates that a class is a service layer component, containing business logic.
- **Semantic Clarity**: Helps in distinguishing service layer beans from other components.
- **Dependency Injection**: `@Service` beans can be injected into other components using `@Autowired`.
- **Transaction Management**: Commonly used with `@Transactional` to manage transactions in service layer components.
- **Component Scanning**: Automatically detected by Spring's component scanning mechanism, especially in Spring Boot applications.

By using the `@Service` annotation appropriately, you can create a well-structured, maintainable service layer in your Spring application.

## `@Component` vs `@Service`

In Spring Framework, both `@Component` and `@Service` are used to declare beans and register them with the Spring container. However, they serve slightly different purposes and are used to indicate different roles within a Spring application. Understanding the differences between these annotations can help in organizing and managing your code more effectively.

### @Component

- **Purpose**: General-purpose stereotype annotation.
- **Usage**: Used to annotate any class that should be managed by the Spring container.
- **Flexibility**: Can be used for any Spring-managed component, such as utility classes, helper classes, etc.
- **Example**:

```java
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    public void doSomething() {
        System.out.println("Component logic executed!");
    }
}
```

### @Service

- **Purpose**: Specialization of `@Component` to indicate a service class that contains business logic.
- **Usage**: Used to annotate service-layer classes.
- **Semantics**: Conveys a more specific role and intent within the application architecture, which is to hold business logic.
- **Example**:

```java
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public String performServiceLogic() {
        return "Service logic executed!";
    }
}
```

### Differences and When to Use:

1. **Semantics**:

   - `@Component`: Indicates a general-purpose Spring-managed bean.
   - `@Service`: Specifically indicates that the annotated class holds business logic and is part of the service layer.

2. **Documentation and Clarity**:

   - Using `@Service` instead of `@Component` can make your code more readable and understandable. It provides a clear indication that the class is a service and contains business logic, helping developers to quickly understand the role of the class within the application.

3. **Technical Differences**:
   - There are no technical differences in terms of functionality. Both annotations result in the class being registered as a bean in the Spring container. The primary difference is semantic and organizational.

### Practical Example:

Let's consider an example with both `@Component` and `@Service` to illustrate their usage in a Spring Boot application.

#### 1. Component Class:

```java
import org.springframework.stereotype.Component;

@Component
public class UtilityComponent {

    public String getUtilityData() {
        return "Utility data";
    }
}
```

#### 2. Service Class:

```java
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    private final UtilityComponent utilityComponent;

    public BusinessService(UtilityComponent utilityComponent) {
        this.utilityComponent = utilityComponent;
    }

    public String performBusinessOperation() {
        return "Business operation with " + utilityComponent.getUtilityData();
    }
}
```

#### 3. Controller Class:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private BusinessService businessService;

    @GetMapping("/execute")
    public String execute() {
        return businessService.performBusinessOperation();
    }
}
```

### Summary:

- **@Component**:
  - **General Purpose**: Used for any Spring-managed bean.
  - **Example**: Utility classes, helper classes.
- **@Service**:
  - **Specific Purpose**: Used for service-layer classes containing business logic.
  - **Example**: Service classes handling business operations.

In practice, using `@Service` for service-layer classes and `@Component` for general-purpose beans helps in organizing the application structure and making the codebase more maintainable and understandable.

## `@Autowired`

The `@Autowired` annotation in Spring Boot is used for automatic dependency injection. It allows Spring to resolve and inject collaborating beans into your bean.

### Key Features

1. **Dependency Injection**: Automatically injects required dependencies.
2. **Reduced Boilerplate**: Eliminates the need for manual bean wiring.
3. **Flexibility**: Can be used on constructors, fields, and setter methods.
4. **Optional Dependency**: Can handle optional dependencies using `required = false`.

### Example

Here’s how to use `@Autowired` in a Spring Boot application.

1. **Add Dependencies**: Ensure you have the Spring Boot Starter dependency in your `pom.xml`.

#### Maven

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

2. **Create a Service Class**:

```java
package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String getUser() {
        return "John Doe";
    }
}
```

3. **Create a Controller and Use @Autowired**:

```java
package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getUser() {
        return userService.getUser();
    }
}
```

### Explanation

- **@Service**: Marks `UserService` as a Spring service component.
- **@RestController**: Marks `UserController` as a Spring MVC controller.
- **@Autowired**: Injects the `UserService` bean into the `UserController`.

### Usage

When the application is run, Spring will automatically inject an instance of `UserService` into the `UserController`. Accessing the `/user` endpoint will return the string "John Doe".

### Constructor Injection

For better testability and immutability, constructor injection is often preferred over field injection.

```java
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUser() {
        return userService.getUser();
    }
}
```

### Optional Dependency

If you have an optional dependency, you can set `required = false`.

```java
@Autowired(required = false)
private UserService userService;
```

### Summary

- **@Autowired**: Automatically injects dependencies.
- **Flexibility**: Can be used on fields, constructors, or setters.
- **Reduced Boilerplate**: Simplifies dependency management in Spring applications.

Using `@Autowired` in Spring Boot streamlines dependency injection, making it easier to manage and test your components.

## `@Configuration`

The `@Configuration` annotation in Spring is an important part of the framework's configuration mechanism. It is used to define a configuration class that provides bean definitions to the Spring container. This approach is part of Spring's Java-based configuration model, which offers a type-safe alternative to traditional XML-based configuration. Here’s a deep dive into the `@Configuration` annotation, its usage, features, and benefits.

### Purpose and Role of `@Configuration`

- **Define Beans**: The primary purpose of a `@Configuration` class is to define beans using methods annotated with `@Bean`.
- **Source of Configuration**: It acts as a source of bean definitions and configurations for the Spring IoC container.
- **Java-based Configuration**: Provides a type-safe and more maintainable alternative to XML configuration.

### Basic Usage

#### Example Configuration Class

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
```

In this example:

- The `AppConfig` class is annotated with `@Configuration`, indicating that it is a source of bean definitions.
- The `myService` method is annotated with `@Bean`, meaning it returns a bean that will be managed by the Spring container.

### Features and Characteristics

#### Singleton Scope

By default, beans defined in a `@Configuration` class are singletons. This means that the same instance of the bean will be returned each time it is requested from the container.

#### Proxy and CGLIB Enhancements

When a class is annotated with `@Configuration`, Spring enhances it using CGLIB to create a subclass that manages the lifecycle of the beans. This proxy ensures that bean methods are called only once per context and handle dependency injection properly.

### Advanced Configuration

#### Defining Dependencies

Beans can be defined with dependencies on other beans, and the Spring container will manage the creation and injection of these dependencies.

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MyRepository myRepository() {
        return new MyRepositoryImpl();
    }

    @Bean
    public MyService myService(MyRepository myRepository) {
        return new MyServiceImpl(myRepository);
    }
}
```

In this example:

- The `myService` bean depends on the `myRepository` bean.
- Spring ensures that `myRepository` is created and injected into `myService`.

#### Conditional Configuration

Spring provides conditional annotations to control the creation of beans based on certain conditions.

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;

@Configuration
public class AppConfig {

    @Bean
    @Conditional(MyCondition.class)
    public MyService myService() {
        return new MyServiceImpl();
    }
}
```

In this example:

- The `myService` bean will only be created if `MyCondition` is met.

### Profiles

Configuration classes can be associated with specific profiles, allowing different beans to be created based on the active profile.

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {

    @Bean
    @Profile("dev")
    public MyService devService() {
        return new DevServiceImpl();
    }

    @Bean
    @Profile("prod")
    public MyService prodService() {
        return new ProdServiceImpl();
    }
}
```

In this example:

- The `devService` bean is created when the `dev` profile is active.
- The `prodService` bean is created when the `prod` profile is active.

### Combining with Other Annotations

`@Configuration` can be combined with other Spring annotations to create a comprehensive configuration.

#### Importing Other Configurations

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({OtherConfig.class})
public class AppConfig {
    // Bean definitions
}
```

In this example:

- The `AppConfig` class imports bean definitions from `OtherConfig`.

#### Property Sources

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    // Bean definitions
}
```

In this example:

- The `AppConfig` class loads properties from the `application.properties` file.

### Benefits of `@Configuration`

- **Type-Safety**: Java-based configuration is type-safe, providing compile-time checking of bean definitions and dependencies.
- **Refactoring**: Easier to refactor and maintain compared to XML configuration.
- **IDE Support**: Better support from IDEs for code completion, navigation, and refactoring.
- **Conditional Configuration**: Provides powerful mechanisms for conditional bean creation and profile-based configurations.
- **Centralized Configuration**: Allows for centralized management of bean definitions and application settings.

### Summary

- **`@Configuration`**: Marks a class as a source of Spring bean definitions.
- **`@Bean` Methods**: Methods within `@Configuration` classes annotated with `@Bean` define the beans.
- **Singleton Scope**: Beans are singletons by default, managed by a CGLIB proxy.
- **Advanced Features**: Supports dependency injection, conditional configuration, profiles, and property sources.
- **Benefits**: Type-safety, maintainability, IDE support, and centralized configuration.

By leveraging `@Configuration`, you can create robust, maintainable, and flexible Spring applications with Java-based configuration, ensuring better control over your application's components and their lifecycle.

## `@PostConstruct`

The `@PostConstruct` annotation in Spring is used to annotate a method that should be executed after the bean's properties have been set and the dependency injection is complete. This is particularly useful for performing any initialization logic that requires access to the injected dependencies.

### Key Features:

- **Initialization Logic**: Allows you to perform any custom initialization after dependency injection.
- **Lifecycle Callback**: Part of the bean lifecycle callbacks defined by JSR-250.
- **Automatic Execution**: The method annotated with `@PostConstruct` is automatically called by the Spring container once the bean is fully initialized.

### Usage:

#### 1. Basic Example:

Here's a simple example to demonstrate the usage of `@PostConstruct`.

```java
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    @PostConstruct
    public void init() {
        // Initialization logic
        System.out.println("Bean is fully initialized and dependencies are injected.");
    }

    public void doSomething() {
        System.out.println("Doing something...");
    }
}
```

In this example, the `init` method will be called automatically after the `MyBean` instance is created and its dependencies are injected.

#### 2. Using with Dependency Injection:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyService {

    @Autowired
    private MyRepository myRepository;

    @PostConstruct
    public void initialize() {
        // Perform initialization that requires myRepository
        System.out.println("Initializing MyService with MyRepository");
    }

    public void performService() {
        // Business logic
    }
}
```

In this example, the `initialize` method is called after `myRepository` is injected into `MyService`.

### When to Use `@PostConstruct`:

- **Initialization Logic**: When you need to perform some setup or initialization logic that requires access to the injected dependencies.
- **Setup Non-Bean Resources**: When you need to initialize non-bean resources that the bean depends on.
- **Validation**: When you need to validate the bean's state after all properties have been set.

### Key Points to Remember:

- **Method Signature**: The method annotated with `@PostConstruct` should be `public` or `protected`, should not take any arguments, and should not return any value (`void`).
- **Single Execution**: The `@PostConstruct` method is executed only once during the bean lifecycle.
- **Exception Handling**: If the `@PostConstruct` method throws an exception, it may prevent the bean from being initialized properly and can cause the Spring context to fail to start.

### Practical Example in a Spring Boot Application:

#### 1. `application.properties`:

```properties
app.init.message=Application Initialized
```

#### 2. Configuration Class:

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class AppConfig {

    @Value("${app.init.message}")
    private String initMessage;

    @PostConstruct
    public void init() {
        System.out.println(initMessage);
    }
}
```

#### 3. Controller Class:

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
```

In this example, when the Spring Boot application starts, the message "Application Initialized" will be printed to the console as part of the initialization logic defined in the `@PostConstruct` method of the `AppConfig` class.

### Summary:

- **`@PostConstruct` Annotation**: Used to define initialization logic that runs after dependency injection.
- **Lifecycle Management**: Helps in managing the bean lifecycle effectively by executing custom logic after the bean is fully initialized.
- **Use Cases**: Ideal for setting up resources, performing validations, and executing any required setup tasks that depend on injected properties.

By using `@PostConstruct`, you can ensure that your initialization logic is executed at the right time in the bean lifecycle, enhancing the maintainability and reliability of your Spring application.

## `@Value`

The `@Value` annotation in Spring Boot is used to inject values into fields, methods, and constructors from a variety of sources, such as properties files, system properties, environment variables, and more. This makes it a powerful tool for externalizing configuration and ensuring that application settings can be easily changed without modifying the code.

### Key Features:

- **Injection of Property Values**: Inject values from properties files, environment variables, or system properties.
- **Default Values**: Supports default values if the specified property is not found.
- **Type Conversion**: Automatically converts the injected value to the type of the target field.

### Usage:

#### 1. Injecting Values from Properties Files:

To inject a value from a properties file, you can use the `@Value` annotation with a placeholder for the property key.

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    @Value("${my.property}")
    private String myProperty;

    public void printProperty() {
        System.out.println("Property value: " + myProperty);
    }
}
```

Ensure you have the property defined in your `application.properties` or `application.yml` file:

**application.properties**:

```properties
my.property=Hello, World!
```

**application.yml**:

```yaml
my:
  property: Hello, World!
```

#### 2. Injecting Default Values:

You can provide a default value that will be used if the property is not found.

```java
@Value("${my.property:Default Value}")
private String myProperty;
```

#### 3. Injecting System Properties and Environment Variables:

You can also inject system properties or environment variables.

```java
@Value("${JAVA_HOME}")
private String javaHome;

@Value("${MY_ENV_VAR:DefaultEnvValue}")
private String myEnvVar;
```

#### 4. Type Conversion:

Spring automatically converts the value to the required type.

```java
@Value("${my.int.property}")
private int myIntProperty;

@Value("${my.boolean.property}")
private boolean myBooleanProperty;
```

### Example in a Spring Boot Application:

**application.properties**:

```properties
app.name=Spring Boot Application
app.version=1.0.0
app.feature.enabled=true
app.max.users=100
```

**Component Class**:

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.feature.enabled}")
    private boolean featureEnabled;

    @Value("${app.max.users}")
    private int maxUsers;

    public void printConfig() {
        System.out.println("App Name: " + appName);
        System.out.println("App Version: " + appVersion);
        System.out.println("Feature Enabled: " + featureEnabled);
        System.out.println("Max Users: " + maxUsers);
    }
}
```

**Controller Class**:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Autowired
    private AppConfig appConfig;

    @GetMapping("/config")
    public String getConfig() {
        appConfig.printConfig();
        return "Check the console for the printed configuration.";
    }
}
```

### Summary:

- **`@Value` Annotation**: Used to inject values into fields, methods, and constructors.
- **Sources**: Properties files, system properties, environment variables, etc.
- **Default Values**: Supports specifying default values.
- **Type Conversion**: Automatically converts the injected values to the required types.
- **Flexibility**: Enhances configuration management and makes the application settings easily changeable without modifying the code.

By using the `@Value` annotation, you can externalize your configuration and make your Spring Boot application more flexible and easier to manage.

## Autoconfiguration

Autoconfiguration is one of the core features of Spring Boot that makes it easy to create stand-alone, production-grade Spring applications. It aims to reduce the need for extensive configuration by automatically configuring Spring applications based on the dependencies present on the classpath and the beans you define.

### Key Concepts of Autoconfiguration

1. **Convention over Configuration**: Spring Boot follows the principle of "convention over configuration," meaning it provides sensible defaults and reduces the need for manual configuration.
2. **Classpath Analysis**: Spring Boot analyzes the classpath at runtime to determine which libraries and frameworks are present, and it automatically configures Spring beans based on this analysis.
3. **Conditional Configuration**: Autoconfiguration classes use conditions to determine whether they should apply certain configurations. This ensures that configurations are only applied when necessary.

### How Autoconfiguration Works

1. **SpringFactoriesLoader**: Spring Boot uses the `SpringFactoriesLoader` to find `META-INF/spring.factories` files on the classpath. These files list all the autoconfiguration classes.
2. **@EnableAutoConfiguration**: This annotation, often used via `@SpringBootApplication`, triggers the autoconfiguration process.
3. **Conditional Annotations**: Autoconfiguration classes use various conditional annotations, such as `@ConditionalOnClass`, `@ConditionalOnMissingBean`, and `@ConditionalOnProperty`, to apply configuration only when specific conditions are met.

### Example of Autoconfiguration

Consider a scenario where you want to use a `DataSource` bean if a JDBC driver is present on the classpath.

#### Autoconfiguration Class

```java
package org.springframework.boot.autoconfigure.jdbc;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(DataSource.class)
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceAutoConfiguration {

    @Autowired
    private DataSourceProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        return createDataSource(this.properties);
    }

    private DataSource createDataSource(DataSourceProperties properties) {
        // Create and configure the DataSource
    }
}
```

### Conditional Annotations

- **@ConditionalOnClass**: The configuration is only applied if the specified class is present on the classpath.
- **@ConditionalOnMissingBean**: The bean is only created if no other bean of the same type is already defined.
- **@EnableConfigurationProperties**: Binds externalized configuration properties (from `application.properties` or `application.yml`) to a Java object.

### Using Autoconfiguration in a Spring Boot Application

When you create a Spring Boot application with dependencies, the autoconfiguration mechanism will automatically configure components based on what it finds on the classpath.

#### Example: Creating a Spring Boot Application

1. **Add Dependencies**: Add dependencies for web and data access in your `pom.xml` or `build.gradle` file.

   ```xml
   <!-- pom.xml -->
   <dependencies>
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
       </dependency>
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-data-jpa</artifactId>
       </dependency>
   </dependencies>
   ```

2. **Main Application Class**: Create a main application class with `@SpringBootApplication`.

   ```java
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;

   @SpringBootApplication
   public class MySpringBootApplication {
       public static void main(String[] args) {
           SpringApplication.run(MySpringBootApplication.class, args);
       }
   }
   ```

3. **Autoconfiguration in Action**: When you run the application, Spring Boot automatically configures a `DispatcherServlet`, a `DataSource`, and other beans based on the dependencies found on the classpath.

### Customizing Autoconfiguration

- **Exclude Autoconfigurations**: You can exclude specific autoconfiguration classes if you want to manually configure certain aspects.

  ```java
  @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
  public class MySpringBootApplication {
      // Application code
  }
  ```

- **Custom Properties**: Use `application.properties` or `application.yml` to customize the default configurations provided by autoconfiguration.

  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/mydb
  spring.datasource.username=root
  spring.datasource.password=secret
  ```

### Benefits of Autoconfiguration

1. **Reduced Boilerplate**: Minimizes the amount of configuration needed to set up Spring applications.
2. **Productivity**: Speeds up development by providing sensible defaults and pre-configured settings.
3. **Flexibility**: Allows for customization and extension of the default configurations when necessary.

### Conclusion

Spring Boot's autoconfiguration is a powerful feature that simplifies the setup and development of Spring applications by automatically configuring beans based on the classpath and environment. It follows the principle of "convention over configuration" while providing flexibility for customization. This makes it easier to create production-ready applications with minimal effort.

## Spring Boot Actuator

Spring Boot Actuator is a powerful tool that provides production-ready features to help you monitor and manage your Spring Boot application. It offers a range of built-in endpoints and metrics that enable you to monitor various aspects of your application's health, performance, and behavior at runtime. Actuator endpoints expose valuable information about your application's internals, making it easier to diagnose issues, analyze performance, and gather operational insights.

### Key Features of Spring Boot Actuator

1. **Health Indicators**: The `/actuator/health` endpoint provides information about the application's health status. Health indicators can be customized to check various aspects such as database connectivity, disk space, and system load.

2. **Metrics Gathering**: Actuator collects metrics about your application's performance and behavior, including HTTP request counts, memory usage, garbage collection statistics, and more. Metrics are available via the `/actuator/metrics` endpoint and can be exported to various monitoring systems.

3. **Environment Information**: The `/actuator/env` endpoint exposes details about the application's environment, including configuration properties, system properties, and environment variables.

4. **Application Information**: Actuator provides endpoints (`/actuator/info` and `/actuator/beans`) to retrieve general information about the application context, such as the application name, version, and available beans.

5. **Endpoint Customization**: Actuator endpoints can be customized and secured to restrict access to sensitive information. You can enable or disable specific endpoints and configure security settings using Spring Boot properties.

6. **Integration with Monitoring Systems**: Actuator seamlessly integrates with various monitoring systems and tools, such as Prometheus, Grafana, and Micrometer. It provides compatibility with popular observability platforms, enabling comprehensive monitoring and alerting.

7. **Extensibility**: Actuator allows you to extend its functionality by creating custom endpoints, health indicators, and metrics collectors tailored to your application's specific requirements.

### Example Usage

1. **Health Endpoint**: Accessing the `/actuator/health` endpoint provides a summary of the application's health status, including details about database connectivity, disk space, and other components.

2. **Metrics Endpoint**: Use the `/actuator/metrics` endpoint to retrieve metrics about your application's performance, such as HTTP request rates, JVM memory usage, and database connection pool metrics.

3. **Environment Endpoint**: Accessing the `/actuator/env` endpoint provides information about the application's configuration properties, system properties, and environment variables.

4. **Custom Endpoints**: Define custom endpoints to expose application-specific information or perform administrative tasks. Custom endpoints can be implemented as Spring beans and exposed through Actuator.

To utilize Spring Boot Actuator in your Spring Boot application, you need to follow these steps:

### 1. Add Actuator Dependency

Ensure that the `spring-boot-starter-actuator` dependency is included in your project. If you're using Maven, add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

For Gradle, add the following to your `build.gradle` file:

```gradle
implementation 'org.springframework.boot:spring-boot-starter-actuator'
```

### 2. Configure Endpoints (Optional)

By default, Actuator endpoints are enabled, but you can customize their behavior in your `application.properties` or `application.yml` file:

```properties
# Customize Actuator endpoints
management.endpoints.web.exposure.include=health,info,metrics,env,beans
```

### 3. Access Actuator Endpoints

Once your application is running, you can access Actuator endpoints in your browser or using HTTP client tools like cURL or Postman:

- Health Endpoint: `http://localhost:8080/actuator/health`
- Info Endpoint: `http://localhost:8080/actuator/info`
- Metrics Endpoint: `http://localhost:8080/actuator/metrics`
- Environment Endpoint: `http://localhost:8080/actuator/env`
- Beans Endpoint: `http://localhost:8080/actuator/beans`
- Custom Endpoints: If you've defined custom endpoints, you can access them using their configured paths.

### 4. Secure Endpoints (Optional)

Actuator endpoints may expose sensitive information, so it's essential to secure them appropriately. You can configure security settings in your `application.properties` or `application.yml` file:

```properties
# Secure Actuator endpoints with basic authentication
management.endpoints.web.exposure.include=health,info,metrics,env,beans
management.endpoint.health.show-details=always
management.endpoint.info.enabled=true
management.endpoint.metrics.enabled=true
management.endpoint.env.enabled=true
management.endpoint.beans.enabled=true
management.endpoint.health.probe.enabled=true
management.endpoint.health.roles=admin
management.security.enabled=true
```

### 5. Customize Actuator

You can customize Actuator behavior by implementing custom health indicators, metrics collectors, or endpoints tailored to your application's requirements. Simply create Spring beans annotated with `@Component` or `@Configuration` and expose them accordingly.

### Integration with Spring Boot

Spring Boot Actuator is included as a dependency in Spring Boot starters, making it effortless to enable and use. To enable Actuator in your Spring Boot application, simply include the `spring-boot-starter-actuator` dependency in your project's `pom.xml` or `build.gradle` file. Spring Boot Actuator automatically exposes its endpoints, allowing you to monitor and manage your application with minimal configuration.

### Conclusion

Spring Boot Actuator is a valuable tool for monitoring and managing Spring Boot applications in production environments. By providing a comprehensive set of built-in endpoints and metrics, Actuator simplifies the process of monitoring application health, performance, and behavior. Leveraging Actuator enables you to gain insights into your application's runtime characteristics, diagnose issues efficiently, and ensure optimal performance and reliability.

## Embedded Server

In Spring Boot, an embedded server refers to the web server that comes bundled with the application, allowing it to run as a standalone executable JAR or WAR file without needing to deploy to an external server like Tomcat or Jetty. The embedded server simplifies the deployment process and makes it easier to build and distribute self-contained applications.

### Key Features and Components

1. **Supported Servers**: Spring Boot supports several embedded servers out-of-the-box, including Tomcat, Jetty, and Undertow. These servers are included as dependencies in the Spring Boot starters, allowing you to choose the one that best fits your requirements.

2. **Configuration**: Spring Boot provides sensible defaults for configuring the embedded server, making it easy to get started without any additional configuration. However, you can customize the server configuration using application properties or Java configuration classes to tweak settings such as port number, context path, SSL configuration, etc.

3. **Auto-Configuration**: Spring Boot's auto-configuration feature automatically configures the embedded server based on the classpath and the dependencies included in the project. It detects the presence of server-specific libraries and configures the embedded server accordingly, reducing the need for manual configuration.

4. **Embedded Container**: The embedded server is packaged directly within the Spring Boot application's executable JAR or WAR file. When you run the application, the embedded server starts automatically, serving HTTP requests and hosting the application's endpoints and resources.

5. **Lifecycle Management**: Spring Boot manages the lifecycle of the embedded server, starting it when the application starts up and shutting it down gracefully when the application is stopped. This ensures that resources are properly released and connections are closed when the application shuts down.

6. **Integration with Spring MVC**: The embedded server seamlessly integrates with Spring MVC, allowing you to develop web applications using Spring's powerful web framework. You can define controllers, endpoints, filters, and interceptors using familiar Spring MVC annotations and patterns.

### Example Usage

To use an embedded server in a Spring Boot application, you typically create a standard Spring Boot project and include the necessary dependencies. Here's a basic example of a Spring Boot application with an embedded Tomcat server:

1. **Add Dependencies**: Include the `spring-boot-starter-web` dependency in your project's `pom.xml` or `build.gradle` file. This starter includes everything you need to develop web applications with Spring Boot, including the embedded Tomcat server.

2. **Develop Application**: Write your application code using Spring MVC, defining controllers, services, and other components as needed.

3. **Run Application**: Run your Spring Boot application using the `java -jar` command, specifying the path to the executable JAR file. Spring Boot automatically starts the embedded Tomcat server, and your application is accessible at the configured port (usually 8080 by default).

### Conclusion

Embedded servers in Spring Boot provide a convenient and lightweight way to deploy web applications as standalone executables. By bundling the server directly within the application, Spring Boot simplifies the deployment process and eliminates the need for external server setups. Whether you're building RESTful APIs, web applications, or microservices, embedded servers offer flexibility, ease of use, and seamless integration with the Spring ecosystem.

## ApplicationContext

The `ApplicationContext` is a central interface in Spring for providing configuration information to the Spring container and managing the bean lifecycle. It represents the Spring IoC container and is responsible for instantiating, configuring, and managing beans defined in the Spring application context.

### Features and Responsibilities

1. **Bean Instantiation and Dependency Injection**: The `ApplicationContext` is responsible for creating and wiring beans defined in the Spring configuration files or Java classes. It handles dependency injection, resolving dependencies between beans, and managing their lifecycle.

2. **Bean Scoping**: It supports different bean scopes such as singleton, prototype, request, session, etc. You can define the scope of a bean using annotations like `@Scope` or XML configuration.

3. **Resource Loading**: The `ApplicationContext` can load application resources such as properties files, XML files, and classpath resources. It provides convenient methods for accessing these resources within the application.

4. **Environment Management**: It manages the application's environment properties, including system properties, environment variables, and configuration properties loaded from external sources like property files or databases.

5. **Event Handling**: The `ApplicationContext` supports the publishing and handling of application events. It allows beans to listen for and respond to events within the application context, facilitating loose coupling and communication between components.

6. **AOP Support**: Spring's AOP features, such as aspect weaving and advice execution, are integrated into the `ApplicationContext`. It automatically applies aspect-oriented programming techniques to beans managed within the context.

7. **Internationalization and Localization**: The `ApplicationContext` provides support for internationalization (i18n) and localization (l10n) by resolving message codes to localized messages using configured message sources.

### Types of ApplicationContext

1. **AnnotationConfigApplicationContext**: It's used to bootstrap the application context based on Java configuration classes annotated with `@Configuration`.

2. **ClassPathXmlApplicationContext**: It loads the application context from XML configuration files located on the classpath.

3. **FileSystemXmlApplicationContext**: Similar to `ClassPathXmlApplicationContext`, but it loads XML configuration files from the file system instead of the classpath.

4. **WebApplicationContext**: It's a specialized `ApplicationContext` for web applications and provides additional features such as access to ServletContext attributes and support for web-specific scopes.

### Example Usage

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyApp {
    public static void main(String[] args) {
        // Create and configure the application context using Java configuration classes
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Retrieve beans from the context and use them
        MyService myService = context.getBean(MyService.class);
        myService.doSomething();
    }
}
```

In this example:

- We create an `ApplicationContext` using `AnnotationConfigApplicationContext`, passing the configuration class `AppConfig`.
- The `AppConfig` class is annotated with `@Configuration` and contains bean definitions.
- We retrieve a bean (`MyService`) from the context and use it in our application.

### Conclusion

The `ApplicationContext` is a fundamental component of the Spring Framework, responsible for managing beans, handling dependencies, and providing various application services. It serves as the backbone of the Spring IoC container and plays a crucial role in the lifecycle of Spring-managed beans. Understanding the `ApplicationContext` and its features is essential for effective Spring application development.

## ORM in Spring Boot

Object-Relational Mapping (ORM) in Spring Boot is a mechanism that allows developers to interact with relational databases using object-oriented programming techniques. It abstracts away the complexities of SQL queries and database interactions, enabling developers to work with domain objects directly.

### Key Concepts and Components

1. **Entity Classes**: In Spring Boot, entity classes represent tables in the database. These classes typically have annotations such as `@Entity`, `@Table`, `@Column`, etc., to map them to the corresponding database tables and columns. Entity classes also define relationships between tables using annotations like `@ManyToOne`, `@OneToMany`, etc.

2. **Repository Interfaces**: Repository interfaces define methods for CRUD (Create, Read, Update, Delete) operations on entity objects. Spring Boot automatically generates implementations of these interfaces at runtime, providing seamless database access without the need to write SQL queries manually. Repositories are typically annotated with `@Repository` or `@RepositoryRestResource`.

3. **JPA (Java Persistence API)**: JPA is a specification for ORM frameworks in Java, and Spring Boot includes support for JPA through libraries like Hibernate. JPA provides a set of annotations and APIs for mapping Java objects to database tables and executing CRUD operations. Spring Boot's `@EnableJpaRepositories` annotation enables JPA repository support in the application.

4. **Data Source Configuration**: Spring Boot simplifies the configuration of data sources, allowing developers to specify database connection details in the `application.properties` or `application.yml` file. By default, Spring Boot auto-configures a data source based on the application's classpath and dependencies.

5. **Transactions**: Spring Boot supports declarative transaction management using annotations such as `@Transactional`. Transactions ensure data integrity by providing ACID (Atomicity, Consistency, Isolation, Durability) properties when performing multiple database operations as a single unit of work.

### Example Usage

Here's an example of using ORM in Spring Boot to create an entity class, repository interface, and perform CRUD operations on a database table:

1. **Dependencies** (in `pom.xml`):

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-jpa</artifactId>
   </dependency>
   <dependency>
       <groupId>com.h2database</groupId>
       <artifactId>h2</artifactId>
       <scope>runtime</scope>
   </dependency>
   ```

2. **Configuration** (in `application.properties`):

   ```properties
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Entity Class**:

   ```java
   @Entity
   public class User {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
       private String name;
       private String email;
       // Getters and Setters
   }
   ```

4. **Repository Interface**:

   ```java
   public interface UserRepository extends JpaRepository<User, Long> {
   }
   ```

5. **Service (Optional)**:

   ```java
   @Service
   public class UserService {
       @Autowired
       private UserRepository userRepository;
       public List<User> getAllUsers() { return userRepository.findAll(); }
       public User getUserById(Long id) { return userRepository.findById(id).orElse(null); }
       public User saveUser(User user) { return userRepository.save(user); }
       public void deleteUser(Long id) { userRepository.deleteById(id); }
   }
   ```

6. **Controller**:
   ```java
   @RestController
   @RequestMapping("/users")
   public class UserController {
       @Autowired
       private UserService userService;
       @GetMapping
       public List<User> getAllUsers() { return userService.getAllUsers(); }
       @GetMapping("/{id}")
       public User getUserById(@PathVariable Long id) { return userService.getUserById(id); }
       public User getUserById(@PathVariable Long id) { return userService.getUserById(id); RestController
       public User getUserById(@Mappings
       riable Long id) { return userService.getUserById(id); }
       @PostMapping
       public User createUser(@RequestBody User user) { return userService.saveUser(user); }
       @DeleteMapping("/{id}")
       public void deleteUser(@PathVariable Long id) { userService.deleteUser(id); }
   }
   ```

### Conclusion

ORM in Spring Boot simplifies database interactions by providing a higher-level abstraction over SQL queries and JDBC operations. By defining entity classes, repository interfaces, and configuring data sources, developers can easily perform CRUD operations on relational databases using familiar object-oriented techniques. ORM frameworks like Hibernate, coupled with Spring Boot's support for JPA, enable rapid development of database-driven applications with minimal boilerplate code.

## RESTful Services Development

## REST APIs

REST APIs (Representational State Transfer Application Programming Interfaces) in Spring Boot enable developers to build web services that follow the REST architectural style. RESTful APIs are based on the principles of using standard HTTP methods (GET, POST, PUT, DELETE) and uniform resource identifiers (URIs) to interact with resources in a stateless manner. Spring Boot provides comprehensive support for building RESTful APIs efficiently.

### Key Components and Concepts

1. **Controller Classes**: Controllers in Spring Boot are responsible for handling incoming HTTP requests and returning appropriate responses. Controller classes typically contain methods annotated with `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, etc., to map HTTP requests to specific endpoints.

2. **Request Mapping**: Request mapping annotations (`@RequestMapping`, `@GetMapping`, `@PostMapping`, etc.) define the URL patterns and HTTP methods that the controller methods can handle. They specify the mapping between incoming requests and the corresponding handler methods in the controller.

3. **Data Transfer Objects (DTOs)**: DTOs are Java classes that represent the data exchanged between the client and the server in RESTful APIs. They encapsulate request and response payloads, providing a structured format for data transmission. DTOs help decouple the API contract from the internal domain model of the application.

4. **Service Layer**: Service classes contain business logic and orchestrate interactions between controllers and repositories. They encapsulate complex operations and facilitate code reuse and maintainability. Service classes are often injected into controller classes using dependency injection.

5. **Repository Layer**: Repositories handle data access and database interactions in the application. They typically contain methods for CRUD operations on entities and are responsible for querying and persisting data in the underlying database.

6. **Response Entities**: ResponseEntity objects encapsulate HTTP response status codes, headers, and body payloads. They allow controllers to customize the HTTP response returned to clients, providing flexibility in handling different scenarios and error conditions.

7. **Exception Handling**: Spring Boot provides robust support for handling exceptions in RESTful APIs. Developers can define exception handler methods annotated with `@ExceptionHandler`, allowing them to gracefully handle exceptions and return appropriate error responses to clients.

8. **Content Negotiation**: Content negotiation allows clients to specify the desired representation format (e.g., JSON, XML) in the request headers. Spring Boot supports content negotiation out-of-the-box, enabling clients to consume APIs in their preferred format.

### Example Usage

Here's a basic example of building a RESTful API in Spring Boot:

1. **Define Controller**:

```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
```

2. **Run Application**:

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

3. **Access API**:

```
GET http://localhost:8080/api/hello
```

### Conclusion

RESTful APIs in Spring Boot enable developers to build scalable, robust, and interoperable web services using industry-standard practices. By leveraging Spring Boot's powerful features such as request mapping, DTOs, service and repository layers, response entities, and exception handling, developers can create RESTful APIs that are easy to develop, maintain, and consume. Spring Boot's convention-over-configuration approach and extensive ecosystem make it an ideal choice for building modern, cloud-native applications with RESTful APIs.

## `@Controller`

In Spring MVC, the `@Controller` annotation is used to mark a class as a controller component. Controllers handle incoming HTTP requests and determine the response to send back to the client. Here's a detailed description of the `@Controller` annotation:

### Key Features and Usage

1. **Component Annotation**: `@Controller` is a specialization of Spring's `@Component` annotation. It indicates that the annotated class is a controller component that handles HTTP requests. When Spring scans for components during application startup, classes annotated with `@Controller` are automatically identified as controllers.

2. **Request Mapping**: Controllers typically contain handler methods that are annotated with `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, etc. These annotations define the mapping between incoming HTTP requests and the methods that handle those requests. For example, `@GetMapping("/users")` maps a GET request to the `/users` URI to a specific controller method.

3. **Model and View Handling**: Controller methods return either a `ModelAndView` object or a logical view name. A `ModelAndView` combines model data with a view template, while a logical view name corresponds to a view template that Spring resolves and renders. Controllers can populate model data and choose the appropriate view to render based on the request.

4. **HTTP Method Handling**: By default, controller methods support all HTTP methods (GET, POST, PUT, DELETE, etc.). Developers can use method-specific annotations like `@GetMapping`, `@PostMapping`, etc., to explicitly map methods to specific HTTP methods. This helps organize the controller and improves readability.

5. **Request and Response Parameters**: Controller methods can accept request parameters, path variables, request headers, request bodies, etc., as method parameters. Spring automatically binds request data to method parameters based on parameter annotations like `@RequestParam`, `@PathVariable`, `@RequestBody`, etc. Similarly, controllers can return different types of responses, including JSON, XML, HTML, etc., based on client requirements.

### Example Usage

Here's a simple example of a controller class annotated with `@Controller`:

```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    @ResponseBody
    public String sayHello() {
        return "Hello, World!";
    }
}
```

In this example:

- The `HelloController` class is annotated with `@Controller`, marking it as a controller component.
- The `@RequestMapping("/hello")` annotation specifies that all endpoints in this controller are relative to the `/hello` URI.
- The `sayHello()` method is annotated with `@GetMapping` to handle GET requests at the `/hello` URI. The `@ResponseBody` annotation indicates that the return value of the method should be directly written to the HTTP response body.

### Conclusion

The `@Controller` annotation in Spring MVC identifies a class as a controller component responsible for handling HTTP requests. By combining `@Controller` with method-specific annotations and parameter annotations, developers can create flexible and powerful controllers to handle various types of requests and produce different types of responses.

## `@RestController`

The `@RestController` annotation in Spring Boot is a specialized version of the `@Controller` annotation that is used to define RESTful web services. It combines the functionality of `@Controller` and `@ResponseBody` annotations, making it convenient for building APIs that return data in JSON, XML, or any other format.

### Key Features and Usage

1. **Specialization of @Controller**: `@RestController` is a specialization of the `@Controller` annotation, indicating that the annotated class is a controller that handles incoming HTTP requests. It is typically used to define the endpoints of a RESTful API.

2. **Response Body Serialization**: Unlike traditional Spring MVC controllers, which return views or model objects, `@RestController` methods return the actual response body data. Spring Boot automatically serializes the return value of these methods to the appropriate format (e.g., JSON or XML) using message converters.

3. **Convenience Annotation**: By combining `@Controller` and `@ResponseBody`, `@RestController` eliminates the need to annotate each individual request-handling method with `@ResponseBody`. It simplifies the process of building RESTful APIs by providing a single annotation for defining both the controller and the response body behavior.

4. **Mapping to Request URIs**: `@RestController` classes and methods can be mapped to specific request URIs using `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, etc., annotations. These annotations define the URL patterns and HTTP methods that the controller methods can handle.

5. **Flexible Content Negotiation**: Spring Boot supports content negotiation out-of-the-box, allowing clients to specify the desired representation format (e.g., JSON, XML) in the request headers. `@RestController` methods automatically serialize response data to the requested format based on the content negotiation settings.

### Example Usage

Here's an example of using `@RestController` to define a simple RESTful API endpoint:

```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
```

In this example:

- The `HelloController` class is annotated with `@RestController`, indicating that it is a REST controller.
- The `@RequestMapping` annotation at the class level specifies the base URI for all endpoints defined in the controller.
- The `@GetMapping` annotation on the `sayHello` method defines a GET endpoint at the `/api/hello` URI. When accessed, this endpoint returns the string "Hello, World!" as the response body.

### Conclusion

`@RestController` in Spring Boot simplifies the development of RESTful APIs by combining the functionality of `@Controller` and `@ResponseBody` annotations into a single convenient annotation. It allows developers to define endpoints that return data directly without the need for additional annotations, making it easier to build APIs that follow REST principles and communicate with clients over HTTP.

## `@Controller` vs `@RestController`

The `@Controller` and `@RestController` annotations in Spring MVC are both used to define classes as controllers, but they have different behaviors and purposes. Here's the difference between them:

1. **Purpose**:

   - `@Controller`: The `@Controller` annotation is used to create a traditional MVC controller in Spring MVC. It is typically used to handle web requests and generate HTML views. Controllers annotated with `@Controller` return `ModelAndView` objects or logical view names, which are then resolved to view templates for rendering HTML responses.
   - `@RestController`: The `@RestController` annotation, on the other hand, is a specialized version of `@Controller` that is tailored for building RESTful web services. It combines the `@Controller` and `@ResponseBody` annotations, indicating that the annotated class is a REST controller that returns data directly in the response body. Controllers annotated with `@RestController` automatically serialize the return value of methods to JSON or XML (depending on the client's request) using message converters.

2. **Response Handling**:

   - `@Controller`: Controllers annotated with `@Controller` typically return view names or `ModelAndView` objects, which are resolved to view templates for rendering HTML responses. These controllers are primarily used to generate HTML responses for web applications.
   - `@RestController`: Controllers annotated with `@RestController` return data directly in the response body, bypassing the view resolution process. The return value of controller methods is serialized to JSON or XML (depending on the client's request) and sent as the response body. These controllers are ideal for building RESTful APIs that communicate with clients over HTTP.

3. **Response Serialization**:

   - `@Controller`: Controllers annotated with `@Controller` do not automatically serialize the return value of methods to JSON or XML. Instead, developers need to use view resolvers or model attributes to render the response body as HTML.
   - `@RestController`: Controllers annotated with `@RestController` automatically serialize the return value of methods to JSON or XML using message converters. This eliminates the need for manual serialization and simplifies the process of building RESTful APIs.

4. **Usage**:
   - `@Controller`: Use `@Controller` when building traditional web applications that generate HTML responses and render views using view templates.
   - `@RestController`: Use `@RestController` when building RESTful web services or APIs that return data directly in the response body, typically in JSON or XML format.

In summary, while both `@Controller` and `@RestController` are used to define classes as controllers in Spring MVC, `@RestController` is specifically designed for building RESTful web services that return data directly in the response body, while `@Controller` is used for generating HTML views in traditional web applications.

## `@RequestBody`

The `@RequestBody` annotation in Spring Boot is used to bind the HTTP request body to a method parameter in a controller handler method. It indicates that the method parameter should be populated with the body of the HTTP request.

### Key Features and Usage

1. **Binding Request Body**: When a controller method is annotated with `@RequestBody`, Spring Boot automatically converts the request body into the specified method parameter's type. It uses message converters to deserialize the request body, allowing for seamless conversion from JSON, XML, or other formats into Java objects.

2. **Support for Various Content Types**: `@RequestBody` supports various content types, including JSON, XML, form data, and others. Spring Boot automatically selects the appropriate message converter based on the `Content-Type` header of the incoming request.

3. **Automatic Deserialization**: Spring Boot automatically deserializes the request body into the specified method parameter's type using configured message converters. This simplifies the handling of complex request payloads, as developers do not need to manually parse the request body.

4. **Flexible Parameter Types**: The method parameter annotated with `@RequestBody` can be of any type, including custom Java objects, collections, arrays, or simple data types. Spring Boot attempts to deserialize the request body into the specified parameter type.

### Example Usage

Here's an example of using `@RequestBody` in a Spring Boot controller:

```java
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class MyController {

    @PostMapping("/data")
    public String processData(@RequestBody MyDataObject data) {
        // Process the received data object
        return "Received data: " + data.toString();
    }
}
```

In this example:

- The `processData` method is annotated with `@PostMapping("/data")` to handle POST requests to the `/api/data` endpoint.
- The `@RequestBody` annotation is applied to the `data` parameter, indicating that it should be populated with the request body.
- When a POST request is made to `/api/data` with a JSON or XML payload representing a `MyDataObject`, Spring Boot automatically converts the request body into a `MyDataObject` instance and passes it to the `processData` method.

### Conclusion

The `@RequestBody` annotation in Spring Boot simplifies the handling of HTTP request bodies in controller methods. By automatically binding the request body to method parameters, developers can easily extract data from incoming requests and process it in their applications. `@RequestBody` is essential for building RESTful APIs that communicate with clients over HTTP and handle complex request payloads efficiently.

## `@ResponseBody`

The `@ResponseBody` annotation in Spring Boot is used to indicate that the return value of a controller method should be written directly to the HTTP response body. It tells Spring Boot that the return value of the method should be serialized and sent as the response body, without further processing as a view or template.

### Key Features and Usage

1. **Direct Response Writing**: When a controller method is annotated with `@ResponseBody`, Spring Boot bypasses the view resolution process and writes the return value directly to the HTTP response body. This allows developers to return custom objects, strings, or any other data directly as the response body.

2. **Flexible Response Types**: The return value of a method annotated with `@ResponseBody` can be of any type, including custom Java objects, collections, arrays, or simple data types. Spring Boot automatically serializes the return value into JSON, XML, or other formats based on the configured message converters.

3. **Support for Various Content Types**: `@ResponseBody` supports various content types, including JSON, XML, plain text, and others. Spring Boot automatically selects the appropriate message converter based on the `Accept` header of the incoming request.

4. **Integration with Other Annotations**: `@ResponseBody` is often used in conjunction with other annotations like `@RequestMapping`, `@GetMapping`, `@PostMapping`, etc., to define RESTful API endpoints. By combining these annotations, developers can create controllers that return data directly in the desired format.

### Example Usage

Here's an example of using `@ResponseBody` in a Spring Boot controller:

```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello, World!";
    }
}
```

In this example:

- The `sayHello` method is annotated with `@GetMapping("/hello")` to handle GET requests to the `/api/hello` endpoint.
- The `@ResponseBody` annotation is applied to the method, indicating that the return value should be written directly to the HTTP response body.
- When a GET request is made to `/api/hello`, Spring Boot serializes the string "Hello, World!" and sends it as the response body.

### Conclusion

The `@ResponseBody` annotation in Spring Boot allows developers to return data directly as the HTTP response body from controller methods. By bypassing the view resolution process, `@ResponseBody` simplifies the creation of RESTful APIs and enables flexible handling of response data. It is an essential annotation for building web services and APIs that communicate with clients over HTTP.

### is @ResponseBody annotation is compulsory to annotate

No, the `@ResponseBody` annotation is not compulsory to annotate controller methods in Spring Boot. When a method returns a value from a controller method without being annotated with `@ResponseBody`, Spring Boot automatically assumes that the return value should be resolved as a view name or a `ModelAndView` object.

However, if you want the return value of the method to be written directly to the HTTP response body without view resolution, then you should use `@ResponseBody`.

In summary, whether you use `@ResponseBody` depends on whether you want to return data directly in the response body or if you need the view resolution mechanism provided by Spring MVC.

## `@RequestMapping`

The `@RequestMapping` annotation in Spring MVC is a versatile annotation used to map HTTP requests to handler methods in a controller. It provides a flexible way to define request mappings based on various criteria such as URL paths, request methods, request headers, request parameters, and more.

### Key Features and Usage

1. **Mapping URI Paths**: `@RequestMapping` allows you to specify one or more URI paths that will be mapped to the annotated handler method. These paths define the endpoints at which the method will respond to HTTP requests.

2. **Specifying Request Methods**: You can specify one or more HTTP request methods (GET, POST, PUT, DELETE, etc.) using the `method` attribute of `@RequestMapping`. This allows you to restrict the handler method to only handle requests of specific methods.

3. **Request Parameters and Headers**: `@RequestMapping` supports attributes like `params` and `headers` to further refine the mapping criteria. You can specify required request parameters or headers that must be present for the mapping to be used.

4. **Content Negotiation**: The `consumes` and `produces` attributes allow you to specify the content types that the handler method can consume or produce. This is useful for handling requests with specific content types and returning responses in different formats.

5. **Multiple Mappings**: You can apply `@RequestMapping` to both class-level and method-level. At the class level, it defines a base URI for all mappings within the controller, while at the method level, it defines specific mappings for individual handler methods.

### Example Usage

Here's an example of using `@RequestMapping` in a Spring MVC controller:

```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello, World!";
    }

    @RequestMapping(value = "/greet", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
    public String greet(@RequestBody String name) {
        return "Hello, " + name + "!";
    }
}
```

In this example:

- The `sayHello` method responds to GET requests at the `/api/hello` endpoint.
- The `greet` method responds to POST requests at the `/api/greet` endpoint, consuming JSON content and producing plain text responses.

### Conclusion

The `@RequestMapping` annotation in Spring MVC is a powerful tool for defining request mappings and handling HTTP requests in controller classes. By specifying various attributes, you can customize the mapping criteria to suit your application's needs and create flexible and robust APIs.

## `@GetMapping`

The `@GetMapping` annotation in Spring MVC is a specialized form of the more general `@RequestMapping` annotation, tailored specifically for mapping HTTP GET requests to handler methods in a controller. It provides a concise and readable way to define GET request mappings.

### Key Features and Usage

1. **Mapping URI Paths**: Similar to `@RequestMapping`, `@GetMapping` allows you to specify one or more URI paths that will be mapped to the annotated handler method. These paths define the endpoints at which the method will respond to HTTP GET requests.

2. **No Need to Specify Request Method**: Unlike `@RequestMapping`, `@GetMapping` specifically handles HTTP GET requests. Therefore, there's no need to explicitly specify the request method using attributes like `method`. This simplifies the code and makes it more readable.

3. **Content Negotiation**: Just like `@RequestMapping`, `@GetMapping` supports content negotiation through attributes like `consumes` and `produces`. You can specify the content types that the handler method can consume or produce.

4. **Integration with Path Variables**: You can easily integrate path variables into `@GetMapping` mappings using the `@PathVariable` annotation. Path variables allow you to extract dynamic values from the URI path and use them as method parameters.

5. **Multiple Mappings**: Similar to `@RequestMapping`, `@GetMapping` can be applied at both class-level and method-level. At the class level, it defines a base URI for all mappings within the controller, while at the method level, it defines specific mappings for individual handler methods.

### Example Usage

Here's an example of using `@GetMapping` in a Spring MVC controller:

```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return "Hello, " + name + "!";
    }
}
```

In this example:

- The `sayHello` method responds to GET requests at the `/api/hello` endpoint.
- The `greet` method responds to GET requests at the `/api/greet/{name}` endpoint, where `{name}` is a path variable representing the name of the person to greet.

### Conclusion

`@GetMapping` is a convenient and expressive annotation for mapping HTTP GET requests to handler methods in Spring MVC controllers. Its simplicity and clarity make it a preferred choice for defining GET request mappings in RESTful APIs and web applications.

## `@PostMapping`

The `@PostMapping` annotation in Spring MVC is a specialized form of the more general `@RequestMapping` annotation, specifically designed for mapping HTTP POST requests to handler methods in a controller. It provides a concise and expressive way to define POST request mappings.

### Key Features and Usage

1. **Mapping URI Paths**: Similar to `@RequestMapping`, `@PostMapping` allows you to specify one or more URI paths that will be mapped to the annotated handler method. These paths define the endpoints at which the method will respond to HTTP POST requests.

2. **Explicitly Handles POST Requests**: Unlike `@RequestMapping`, which can handle requests of any HTTP method, `@PostMapping` is specifically designed to handle HTTP POST requests. Therefore, there's no need to explicitly specify the request method using attributes like `method`.

3. **Content Negotiation**: `@PostMapping` supports content negotiation through attributes like `consumes` and `produces`. You can specify the content types that the handler method can consume or produce.

4. **Integration with Request Body**: It's common for POST requests to contain data in the request body. `@PostMapping` seamlessly integrates with the `@RequestBody` annotation to deserialize the request body into method parameters.

5. **Multiple Mappings**: Similar to other request mapping annotations, `@PostMapping` can be applied at both class-level and method-level. At the class level, it defines a base URI for all mappings within the controller, while at the method level, it defines specific mappings for individual handler methods.

### Example Usage

Here's an example of using `@PostMapping` in a Spring MVC controller:

```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyController {

    @PostMapping("/create")
    public ResponseEntity<String> createEntity(@RequestBody Entity entity) {
        // Logic to create the entity
        return ResponseEntity.ok("Entity created successfully");
    }
}
```

In this example:

- The `createEntity` method responds to POST requests at the `/api/create` endpoint.
- It expects an `Entity` object in the request body, which is automatically deserialized using the `@RequestBody` annotation.
- After processing the request, it returns a response entity with an "Entity created successfully" message.

### Conclusion

`@PostMapping` is a powerful annotation for mapping HTTP POST requests to handler methods in Spring MVC controllers. Its simplicity and specificity make it ideal for defining POST request mappings in RESTful APIs and web applications. By seamlessly integrating with request bodies and supporting content negotiation, `@PostMapping` simplifies the handling of POST requests and enhances developer productivity.

## `@PutMapping`

The `@PutMapping` annotation in Spring MVC is a specialized form of the more general `@RequestMapping` annotation, specifically tailored for mapping HTTP PUT requests to handler methods in a controller. It provides a concise and expressive way to define PUT request mappings.

### Key Features and Usage

1. **Mapping URI Paths**: Similar to `@RequestMapping`, `@PutMapping` allows you to specify one or more URI paths that will be mapped to the annotated handler method. These paths define the endpoints at which the method will respond to HTTP PUT requests.

2. **Explicitly Handles PUT Requests**: Unlike `@RequestMapping`, which can handle requests of any HTTP method, `@PutMapping` is specifically designed to handle HTTP PUT requests. Therefore, there's no need to explicitly specify the request method using attributes like `method`.

3. **Content Negotiation**: `@PutMapping` supports content negotiation through attributes like `consumes` and `produces`. You can specify the content types that the handler method can consume or produce.

4. **Integration with Request Body**: It's common for PUT requests to contain data in the request body. `@PutMapping` seamlessly integrates with the `@RequestBody` annotation to deserialize the request body into method parameters.

5. **Multiple Mappings**: Similar to other request mapping annotations, `@PutMapping` can be applied at both class-level and method-level. At the class level, it defines a base URI for all mappings within the controller, while at the method level, it defines specific mappings for individual handler methods.

### Example Usage

Here's an example of using `@PutMapping` in a Spring MVC controller:

```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyController {

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEntity(@PathVariable Long id, @RequestBody Entity entity) {
        // Logic to update the entity with the given id
        return ResponseEntity.ok("Entity updated successfully");
    }
}
```

In this example:

- The `updateEntity` method responds to PUT requests at the `/api/update/{id}` endpoint.
- It expects an `Entity` object in the request body, which is automatically deserialized using the `@RequestBody` annotation.
- The `id` path variable is extracted from the URI and used to identify the entity to be updated.
- After processing the request, it returns a response entity with an "Entity updated successfully" message.

### Conclusion

`@PutMapping` is a powerful annotation for mapping HTTP PUT requests to handler methods in Spring MVC controllers. Its specificity and simplicity make it ideal for defining PUT request mappings in RESTful APIs and web applications. By seamlessly integrating with request bodies and supporting content negotiation, `@PutMapping` simplifies the handling of PUT requests and enhances developer productivity.

## `@DeleteMapping`

The `@DeleteMapping` annotation in Spring MVC is a specialized form of the more general `@RequestMapping` annotation, specifically designed for mapping HTTP DELETE requests to handler methods in a controller. It provides a concise and expressive way to define DELETE request mappings.

### Key Features and Usage

1. **Mapping URI Paths**: Similar to `@RequestMapping`, `@DeleteMapping` allows you to specify one or more URI paths that will be mapped to the annotated handler method. These paths define the endpoints at which the method will respond to HTTP DELETE requests.

2. **Explicitly Handles DELETE Requests**: Unlike `@RequestMapping`, which can handle requests of any HTTP method, `@DeleteMapping` is specifically designed to handle HTTP DELETE requests. Therefore, there's no need to explicitly specify the request method using attributes like `method`.

3. **Content Negotiation**: `@DeleteMapping` supports content negotiation through attributes like `consumes` and `produces`. You can specify the content types that the handler method can consume or produce.

4. **Integration with Path Variables**: It's common for DELETE requests to include path variables in the URI to identify the resource to be deleted. `@DeleteMapping` seamlessly integrates with the `@PathVariable` annotation to extract these variables from the URI.

5. **Multiple Mappings**: Similar to other request mapping annotations, `@DeleteMapping` can be applied at both class-level and method-level. At the class level, it defines a base URI for all mappings within the controller, while at the method level, it defines specific mappings for individual handler methods.

### Example Usage

Here's an example of using `@DeleteMapping` in a Spring MVC controller:

```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyController {

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable Long id) {
        // Logic to delete the entity with the given id
        return ResponseEntity.ok("Entity deleted successfully");
    }
}
```

In this example:

- The `deleteEntity` method responds to DELETE requests at the `/api/delete/{id}` endpoint.
- It expects an `id` path variable in the URI, which is automatically extracted using the `@PathVariable` annotation.
- The extracted `id` is used to identify the entity to be deleted.
- After processing the request, it returns a response entity with an "Entity deleted successfully" message.

### Conclusion

`@DeleteMapping` is a powerful annotation for mapping HTTP DELETE requests to handler methods in Spring MVC controllers. Its specificity and simplicity make it ideal for defining DELETE request mappings in RESTful APIs and web applications. By seamlessly integrating with path variables and supporting content negotiation, `@DeleteMapping` simplifies the handling of DELETE requests and enhances developer productivity.

## `@PatchMapping`

In Spring MVC, there isn't a specific annotation like `@PatchMapping` dedicated solely to handling HTTP PATCH requests. However, you can still handle PATCH requests using the generic `@RequestMapping` annotation with the appropriate `method` attribute set to `RequestMethod.PATCH`.

Here's an example of how you can handle PATCH requests in a Spring MVC controller:

```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyController {

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateEntity(@PathVariable Long id, @RequestBody Entity entity) {
        // Logic to update the entity with the given id using PATCH request
        return ResponseEntity.ok("Entity updated successfully");
    }
}
```

In this example:

- The `@PatchMapping` annotation is used to specifically handle PATCH requests at the `/api/update/{id}` endpoint.
- The `@PathVariable` annotation is used to extract the `id` from the URI path.
- The `@RequestBody` annotation is used to deserialize the request body into an `Entity` object.
- The method logic performs the necessary updates to the entity identified by the `id`.
- Finally, a response entity with a success message is returned.

While there isn't a dedicated `@PatchMapping` annotation, you can achieve the same functionality using `@RequestMapping` with the appropriate `method` attribute set to `RequestMethod.PATCH`.

## `ResponseEntity`

`ResponseEntity` in Spring MVC is a class that represents an HTTP response entity, allowing you to customize the HTTP response returned by a controller method. It provides flexibility in constructing and sending responses with custom status codes, headers, and bodies.

### Key Features and Usage

1. **Customizing Response Status**: You can specify the HTTP status code to be returned in the response using `ResponseEntity.status(HttpStatus status)`. This allows you to indicate the success or failure of the request.

2. **Setting Response Headers**: You can add custom HTTP headers to the response using `ResponseEntity.header(String headerName, String headerValue)` or `ResponseEntity.headers(HttpHeaders headers)`. This is useful for including metadata or controlling client behavior.

3. **Setting Response Body**: You can set the body of the response using `ResponseEntity.body(T body)`. The body can be any object representing the data to be sent back to the client. It could be a string, a POJO, or any other data type.

4. **Combining Status, Headers, and Body**: `ResponseEntity` provides convenience methods like `ResponseEntity.ok()`, `ResponseEntity.created()`, `ResponseEntity.accepted()`, etc., to create a response entity with common status codes. You can then further customize the response by chaining methods to set headers and body.

5. **Handling Errors**: `ResponseEntity` is commonly used in error handling scenarios, where you can construct a response entity with an appropriate error status code, along with an error message or details.

### Example Usage

Here's an example of using `ResponseEntity` in a Spring MVC controller:

```java
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/data")
    public ResponseEntity<String> getData() {
        // Simulated data retrieval
        String data = "Hello, World!";

        // Construct ResponseEntity with custom status and body
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .body(data);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createData(@RequestBody String newData) {
        // Logic to create new data
        // Assuming successful creation
        return ResponseEntity.created(URI.create("/api/data/" + newData))
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Handling unexpected exceptions
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }
}
```

In this example:

- The `getData` method returns a response entity with a status of 200 OK, a content type of text/plain, and the body containing the string "Hello, World!".
- The `createData` method returns a response entity with a status of 201 Created, indicating successful creation of a new resource, along with the URI of the newly created resource.
- The `handleException` method handles unexpected exceptions by returning a response entity with a status of 500 Internal Server Error and an error message.

### Conclusion

`ResponseEntity` in Spring MVC provides a flexible and powerful mechanism for customizing HTTP responses returned by controller methods. It allows you to control the status code, headers, and body of the response, making it suitable for a wide range of use cases, including data retrieval, resource creation, error handling, and more.

## `@PathVariable`

The `@PathVariable` annotation in Spring Boot is used to extract values from the URI path and bind them to method parameters in your controller.

### Key Features

1. **Extracting Path Variables**: Captures dynamic parts of the URI and assigns them to method parameters.
2. **Flexible Mapping**: Supports mapping dynamic parts of the URL to method parameters.
3. **Data Binding**: Automatically converts path variables to the desired data type.

### Example

Here's an example of how to use `@PathVariable` in a Spring Boot application.

1. **Create a Controller**:

```java
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        return "Hello, " + name + "!";
    }
}
```

### Explanation

- **@GetMapping**: Maps HTTP GET requests to the `/hello/{name}` endpoint.
- **@PathVariable**: Binds the `{name}` path variable to the method parameter `name`.
- **Dynamic URI**: Allows clients to access `/hello/{name}` and pass a name dynamically in the URL.

### Usage

When a client accesses `/hello/John`, the `sayHello` method will receive "John" as the value for the `name` parameter and return "Hello, John!".

### Multiple Path Variables

You can use multiple `@PathVariable` annotations to capture multiple dynamic parts of the URI.

```java
@GetMapping("/hello/{firstName}/{lastName}")
public String sayHello(@PathVariable String firstName, @PathVariable String lastName) {
    return "Hello, " + firstName + " " + lastName + "!";
}
```

### Path Variable with Data Type Conversion

Spring automatically converts path variables to the desired data type.

```java
@GetMapping("/hello/{age}")
public String greetAge(@PathVariable int age) {
    return "Hello, you are " + age + " years old!";
}
```

### Summary

- **@PathVariable**: Extracts dynamic parts of the URI and binds them to method parameters.
- **Dynamic Mapping**: Allows flexible mapping of dynamic segments in the URL.
- **Data Binding**: Automatically converts path variables to the desired data type.

Using `@PathVariable` in Spring Boot simplifies the handling of dynamic parts of URLs in your RESTful web services, making it easy to create dynamic and flexible APIs.

## `@PathVariable` and `@RequestParam`

Handling path variables and request parameters in Spring MVC allows you to extract data from the URI and request parameters of an incoming HTTP request. This data can then be used by your controller methods to process the request and generate an appropriate response. Path variables are part of the URI path, while request parameters are passed as key-value pairs in the query string of the URL.

### Handling Path Variables

Path variables are placeholders in the URI path that capture dynamic values. They are specified within curly braces `{}` in the `@RequestMapping` annotation and are extracted using the `@PathVariable` annotation in the controller method parameters.

Example:

```java
@GetMapping("/users/{id}")
public ResponseEntity<User> getUserById(@PathVariable Long id) {
    // Logic to retrieve user with the given id
}
```

### Handling Request Parameters

Request parameters are key-value pairs passed in the query string of the URL. They are typically used for filtering, sorting, or providing additional data to the server.

Example:

```java
@GetMapping("/users")
public ResponseEntity<List<User>> getUsersByRole(@RequestParam("role") String role) {
    // Logic to retrieve users with the specified role
}
```

### Combined Usage

You can also use both path variables and request parameters in the same request mapping.

Example:

```java
@GetMapping("/users/{id}/posts")
public ResponseEntity<List<Post>> getUserPosts(
        @PathVariable Long id,
        @RequestParam(name = "category", required = false) String category) {
    // Logic to retrieve posts for the user with the specified id
    // Optionally filter by category if provided
}
```

### Conclusion

Handling path variables and request parameters in Spring MVC allows you to create flexible and dynamic endpoints that can process different types of requests. By using annotations like `@PathVariable` and `@RequestParam`, you can easily extract data from the URI and request parameters and use it within your controller methods to generate the appropriate response. This enables you to build robust and customizable RESTful APIs and web applications.

## JPA

**Java Persistence API (JPA)** is a specification for managing relational data in Java applications. It defines a set of concepts and interfaces for interacting with databases in a standardized way. In Spring Boot, JPA is often used with Hibernate, which is an implementation of the JPA specification.

### Key Features of JPA

1. **Entity Mapping**: JPA allows mapping Java objects (entities) to database tables.
2. **CRUD Operations**: Provides methods for Create, Read, Update, and Delete operations.
3. **Query Language**: Uses JPQL (Java Persistence Query Language) for database queries.
4. **Transaction Management**: Integrates with Spring’s transaction management.
5. **Cascading and Lazy Loading**: Handles related entity loading and operations efficiently.

### How JPA Works in Spring Boot

Spring Boot simplifies JPA integration with auto-configuration and the Spring Data JPA module, which reduces boilerplate code for database operations.

#### Step-by-Step Example

1. **Add Dependencies**: Include Spring Data JPA and a database driver in your `pom.xml` (for Maven) or `build.gradle` (for Gradle).

##### Maven

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

2. **Configure DataSource**: Define database connection properties in `application.properties` or `application.yml`.

##### application.properties

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```

3. **Define Entity Class**: Create a Java class annotated with `@Entity` to represent a database table.

```java
package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    // Getters and Setters
}
```

4. **Create Repository Interface**: Define a repository interface that extends `JpaRepository` to perform CRUD operations.

```java
package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
```

5. **Service Layer (Optional)**: Create a service layer to encapsulate business logic.

```java
package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
```

6. **Controller Layer**: Create a REST controller to handle HTTP requests.

```java
package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
```

### Summary

- **JPA (Java Persistence API)**: A specification for ORM in Java, enabling interaction with relational databases.
- **Entity**: Java class mapped to a database table, annotated with `@Entity`.
- **Repository**: Interface extending `JpaRepository` for CRUD operations.
- **Configuration**: Requires setting up dependencies and database connection properties.
- **Spring Boot Integration**: Simplifies the use of JPA with auto-configuration and the Spring Data JPA module, reducing boilerplate code.

Using JPA in Spring Boot allows for efficient and easy-to-maintain database interactions, leveraging the power of ORM to manage relational data in an object-oriented way.

## `@Entity`

An **Entity** in Spring Boot is a Java class that represents a table in a relational database. This class is mapped to the table using the Java Persistence API (JPA) annotations, and each instance of the entity class corresponds to a row in the table.

### Key Features

1. **Table Representation**: An entity class represents a table in the database.
2. **Field Mapping**: Fields in the entity class represent columns in the table.
3. **Primary Key**: Every entity must have a primary key, typically annotated with `@Id`.
4. **Annotations**: Uses JPA annotations to define the mappings.

### Common Annotations

- `@Entity`: Specifies that the class is an entity and is mapped to a database table.
- `@Table`: Specifies the name of the table (optional, defaults to the class name).
- `@Id`: Specifies the primary key of the entity.
- `@GeneratedValue`: Specifies how the primary key should be generated.
- `@Column`: Specifies the details of the column to which a field will be mapped.

### Example

Here's a step-by-step example of defining an entity in a Spring Boot application.

1. **Add Dependencies**: Ensure you have the necessary dependencies in your `pom.xml`.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

2. **Configuration**: Configure the datasource in `application.properties`.

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```

3. **Define Entity Class**:

```java
package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

or

```java
package com.example.demo.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
}

```

### Summary

- **Entity**: A Java class representing a database table.
- **Annotations**: Uses JPA annotations to map the class to a table and its fields to columns.
- **Primary Key**: Each entity must have a primary key annotated with `@Id`.
- **Configuration**: Includes dependencies and datasource configuration for JPA.

Entities in Spring Boot enable easy and straightforward mapping of Java objects to database tables, allowing for effective interaction with relational databases.

## JDBC

JDBC (Java Database Connectivity) is a standard API for connecting Java applications to relational databases. In Spring Boot, you can use JDBC for database access by leveraging Spring's JDBC support. Spring Boot simplifies JDBC usage by providing auto-configuration for data sources, JDBC templates, and transaction management.

### Key Components and Features

1. **DataSource Configuration**: Spring Boot automatically configures a DataSource bean based on the application's configuration properties. You can customize the DataSource configuration by providing properties in the `application.properties` or `application.yml` file.

2. **JdbcTemplate**: Spring Boot provides the `JdbcTemplate` class, which simplifies JDBC usage by encapsulating common database operations like query execution, batch updates, and result set processing. You can inject `JdbcTemplate` instances into your Spring beans and use them to interact with the database.

3. **Transaction Management**: Spring Boot integrates with Spring's transaction management capabilities, allowing you to manage transactions declaratively using annotations like `@Transactional`. Transactions ensure the ACID (Atomicity, Consistency, Isolation, Durability) properties of database operations.

4. **Exception Translation**: Spring Boot provides exception translation mechanisms that convert low-level JDBC exceptions into Spring's DataAccessException hierarchy. This makes it easier to handle database-related exceptions consistently within your application.

5. **Connection Pooling**: Spring Boot leverages connection pooling libraries like HikariCP by default to manage database connections efficiently. Connection pooling improves application performance by reusing database connections instead of creating new ones for each request.

### Example Usage

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("email")
                ));
    }

    public void save(User user) {
        String sql = "INSERT INTO users (username, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail());
    }
}
```

In this example:

- `UserRepository` is a Spring-managed bean responsible for data access operations.
- It uses `JdbcTemplate` to execute SQL queries and update statements.
- The `findById` method queries the database for a user with the specified ID.
- The `save` method inserts a new user into the database.

### Conclusion

JDBC remains a powerful and flexible option for database access in Spring Boot applications. While Spring Data JPA offers higher-level abstractions for working with relational databases, JDBC provides more control and flexibility for custom SQL queries and database-specific features. With Spring Boot's auto-configuration and JDBC support, you can easily integrate JDBC into your applications and interact with relational databases efficiently.

# MongoDB

## `@Document`

The `@Document` annotation is used in Spring Data MongoDB to mark a class as being a MongoDB document. This annotation is part of the Spring Data MongoDB project, which provides integration with MongoDB databases.

### Key Features

1. **Mapping**: Maps the Java class to a MongoDB collection.
2. **Collection Name**: Optionally specify the collection name. If not provided, the class name is used as the collection name.
3. **Flexible Schema**: MongoDB's schema-less nature allows flexible document structures.

### Example

Here’s how to define a MongoDB document using the `@Document` annotation.

1. **Add Dependencies**: Ensure you have the Spring Data MongoDB dependency in your `pom.xml`.

#### Maven

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.24</version>
    <scope>provided</scope>
</dependency>
```

2. **Configuration**: Configure MongoDB connection in `application.properties`.

```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=testdb
```

3. **Define Document Class Using Lombok**:

```java
package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;
    private String email;
}
```

### Explanation

- **@Data**: Lombok annotation that generates getters, setters, `toString()`, `equals()`, and `hashCode()` methods.
- **@Document**: Marks the class as a MongoDB document. The `collection` attribute specifies the collection name. If omitted, the class name is used.
- **@Id**: Marks the field as the primary key for the document.

### Repository Interface

Define a repository interface for the document.

```java
package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
```

### Using the Repository

```java
package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
```

### Summary

- **@Document**: Used to map a Java class to a MongoDB collection.
- **@Id**: Marks the field as the primary key for the document.
- **Integration**: Use Spring Data MongoDB for seamless integration with MongoDB.
- **Lombok**: Reduces boilerplate code by generating common methods.

Using the `@Document` annotation in conjunction with Lombok and Spring Data MongoDB simplifies the development of MongoDB-backed applications in Spring Boot.

## `@DBRef`

The `@DBRef` annotation is used in Spring Data MongoDB to indicate a reference to another document in a different collection. It creates a relationship between documents similar to foreign keys in relational databases.

### Key Features

1. **Document Reference**: Links documents in different collections.
2. **Lazy Loading**: Supports lazy loading of referenced documents.
3. **Consistency**: Ensures that the reference is maintained in the database, although it's important to manage the consistency manually.

### Example

Here's an example of how to use `@DBRef` to create a reference between two MongoDB documents.

1. **Add Dependencies**: Ensure you have the Spring Data MongoDB dependency in your `pom.xml`.

#### Maven

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.24</version>
    <scope>provided</scope>
</dependency>
```

#### Gradle

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'
    compileOnly 'org.projectlombok:lombok:1.18.24'
    annotationProcessor 'org.projectlombok:lombok:1.18.24'
}
```

2. **Configuration**: Configure MongoDB connection in `application.properties`.

```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=testdb
```

3. **Define Document Classes Using Lombok and @DBRef**:

#### Address Document

```java
package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "addresses")
public class Address {

    @Id
    private String id;
    private String street;
    private String city;
    private String zipCode;
}
```

#### User Document with @DBRef

```java
package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;
    private String email;

    @DBRef
    private Address address;
}
```

### Explanation

- **@Data**: Lombok annotation that generates getters, setters, `toString()`, `equals()`, and `hashCode()` methods.
- **@Document**: Marks the class as a MongoDB document.
- **@Id**: Marks the field as the primary key for the document.
- **@DBRef**: Creates a reference to another document. In this case, each `User` has a reference to an `Address`.

### Repository Interfaces

Define repository interfaces for both documents.

#### Address Repository

```java
package com.example.demo.repository;

import com.example.demo.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
```

#### User Repository

```java
package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
```

### Using the Repositories

```java
package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        Address address = addressRepository.save(user.getAddress());
        user.setAddress(address);
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
```

### Summary

- **@DBRef**: Creates a reference to another MongoDB document.
- **@Document**: Marks a class as a MongoDB document.
- **Lombok**: Reduces boilerplate code by generating common methods.
- **Lazy Loading**: Supports lazy loading of referenced documents.
- **Manual Consistency Management**: Ensures that the references are maintained properly.

Using `@DBRef` in Spring Data MongoDB allows for creating relationships between documents in different collections, making it easier to work with related data.

## `@Indexed`

The `@Indexed` annotation in Spring Data MongoDB is used to create indexes on fields to improve query performance.

### Key Features

- **Performance Improvement**: Speeds up query execution.
- **Unique Index**: Ensures field values are unique if specified.
- **Compound Indexes**: Supports creating indexes on multiple fields.

### Example

1. **Dependencies**: Ensure Spring Data MongoDB and Lombok are in your `pom.xml`.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.24</version>
    <scope>provided</scope>
</dependency>
```

2. **Configuration**: MongoDB settings in `application.properties`.

```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=testdb
```

3. **Document Class**:

```java
package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    @Indexed
    private String name;
}
```

### Summary

- **@Indexed**: Improves query performance by indexing fields.
- **@Document**: Marks a class as a MongoDB document.
- **Lombok**: Reduces boilerplate code.

Using `@Indexed` in Spring Data MongoDB enhances the efficiency and speed of database queries.

## `@MongoTransactionManager`

The `@MongoTransactionManager` annotation does not exist in Spring Boot. However, in a Spring Boot application with MongoDB, you can use the `MongoTransactionManager` class to manage transactions.

### Key Features:

1. **Transaction Management for MongoDB**: `MongoTransactionManager` is used to manage transactions in MongoDB.

2. **Supports ACID Transactions**: Enables support for ACID (Atomicity, Consistency, Isolation, Durability) transactions in MongoDB.

### Example Configuration:

```java
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
public class MongoConfig {

    private final MongoClient mongoClient;

    @Autowired
    public MongoConfig(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Bean
    public MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}
```

### Explanation:

- **MongoTransactionManager**: Configures a `MongoTransactionManager` bean, which manages transactions for MongoDB.

- **@Bean**: Declares a bean of type `MongoTransactionManager` in the Spring application context.

### Usage:

Once configured, you can use the `MongoTransactionManager` bean to manage transactions in your Spring Boot application with MongoDB. Annotate your service methods with `@Transactional` to apply transactional behavior.

### Summary:

- **MongoTransactionManager**: Manages transactions in a Spring Boot application with MongoDB.

- **Configuration**: Configured as a bean in the Spring application context to enable transaction management.

- **Supports ACID Transactions**: Enables support for ACID transactions in MongoDB, ensuring data integrity and consistency.

##

Serialization and Deserialization: Libraries like Jackson for JSON processing require a no-argument constructor for proper serialization and deserialization. If your class is used in JSON mapping, having a no-argument constructor becomes essential.

## `MongoTemplate`, `Criteria`, and `Query`

In Spring Boot, `MongoTemplate`, `Criteria`, and `Query` are part of the Spring Data MongoDB library, which provides a convenient way to interact with MongoDB. They allow you to perform complex queries and operations on MongoDB collections.

### MongoTemplate

`MongoTemplate` is the primary interface to interact with MongoDB. It provides a wide range of methods for performing CRUD operations, executing queries, and handling various database interactions.

#### Key Features:

- **CRUD Operations**: Create, Read, Update, and Delete operations.
- **Query Execution**: Execute complex queries using `Query` and `Criteria`.
- **Aggregation**: Perform aggregation operations.
- **Collection Management**: Create and drop collections.

#### Example Usage:

1. **Configuration**:
   First, you need to configure `MongoTemplate` in your Spring Boot application.

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(MongoClients.create("mongodb://localhost:27017"), "your_database_name");
    }
}
```

2. **Using MongoTemplate**:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    public User findUserById(String id) {
        return mongoTemplate.findById(id, User.class);
    }

    public void deleteUserById(String id) {
        User user = findUserById(id);
        if (user != null) {
            mongoTemplate.remove(user);
        }
    }
}
```

### Criteria

`Criteria` is used to define conditions in a query. It helps to create complex query conditions.

#### Example Usage:

```java
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> findUsersByAge(int age) {
        Criteria criteria = Criteria.where("age").is(age);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, User.class);
    }

    public List<User> findUsersByNameAndAge(String name, int age) {
        Criteria criteria = new Criteria().andOperator(
            Criteria.where("name").is(name),
            Criteria.where("age").is(age)
        );
        Query query = new Query(criteria);
        return mongoTemplate.find(query, User.class);
    }
}
```

### Query

`Query` is used to build queries to be executed by `MongoTemplate`. It takes a `Criteria` object to specify the conditions.

#### Example Usage:

1. **Simple Query**:

```java
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> findUsersByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query, User.class);
    }
}
```

2. **Complex Query**:

```java
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> findUsersByAgeRange(int minAge, int maxAge) {
        Criteria criteria = Criteria.where("age").gte(minAge).lte(maxAge);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, User.class);
    }
}
```

### Example in a Spring Boot Application:

#### Entity Class:

```java
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;
    private int age;

    // Getters and Setters
}
```

#### Service Class:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    public User findUserById(String id) {
        return mongoTemplate.findById(id, User.class);
    }

    public List<User> findUsersByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query, User.class);
    }

    public List<User> findUsersByAgeRange(int minAge, int maxAge) {
        Criteria criteria = Criteria.where("age").gte(minAge).lte(maxAge);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, User.class);
    }

    public void deleteUserById(String id) {
        User user = findUserById(id);
        if (user != null) {
            mongoTemplate.remove(user);
        }
    }
}
```

### Summary:

- **MongoTemplate**: Main interface for interacting with MongoDB, providing various methods for CRUD operations and query execution.
- **Criteria**: Used to define conditions in a query, enabling the creation of complex queries.
- **Query**: Builds the query to be executed by `MongoTemplate`, incorporating criteria to specify conditions.

Using `MongoTemplate`, `Criteria`, and `Query`, you can perform powerful and flexible operations on MongoDB collections within your Spring Boot application.

# Lombok

**Lombok** is a Java library that helps reduce boilerplate code by generating common methods (like getters, setters, toString, etc.) at compile time using annotations. This makes your code cleaner and more maintainable.

### Key Features of Lombok

1. **Automatic Generation**: Generates commonly used methods such as getters, setters, equals, hashCode, and toString.
2. **Annotations**: Uses annotations to indicate which methods to generate.
3. **Integration**: Easily integrates with Spring Boot and other Java frameworks.

### `@Data`

The `@Data` annotation is one of the most commonly used Lombok annotations. It is a shortcut for several other annotations combined:

- `@Getter`: Generates getters for all fields.
- `@Setter`: Generates setters for all fields.
- `@ToString`: Generates a `toString` method.
- `@EqualsAndHashCode`: Generates `equals` and `hashCode` methods.
- `@RequiredArgsConstructor`: Generates a constructor for all final fields and fields marked with `@NonNull`.

### Usage

#### Adding Lombok Dependency

To use Lombok in a Spring Boot project, add the following dependency to your `pom.xml` (for Maven) or `build.gradle` (for Gradle).

##### Maven

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.24</version>
    <scope>provided</scope>
</dependency>
```

#### Example Entity Class with Lombok

Without Lombok, a typical Java entity might look like this:

```java
package com.example.demo.entity;

import java.util.Objects;

public class User {
    private Long id;
    private String name;
    private String email;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
               Objects.equals(name, user.name) &&
               Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
```

With Lombok, the same class can be simplified significantly:

```java
package com.example.demo.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String email;
}
```

Lombok's `@Data` annotation automatically generates all the boilerplate code (getters, setters, toString, equals, and hashCode methods).

### Summary

- **Lombok**: A library to reduce boilerplate code by generating common methods.
- **@Data**: A Lombok annotation that combines `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`, and `@RequiredArgsConstructor`.
- **Integration**: Add Lombok as a dependency in your project and use annotations to simplify your code.

Using Lombok, particularly the `@Data` annotation, helps keep your Spring Boot applications clean and concise, focusing on business logic rather than repetitive boilerplate code.

## `@NonNull`

The `@NonNull` annotation in Spring Boot, provided by Lombok, is used to indicate that a field, parameter, or method return value cannot be null. This helps enforce null-safety in your code.

### Key Features

1. **Null-Safety**: Prevents null values for the annotated element.
2. **Validation**: Automatically checks for null values and throws a `NullPointerException` if a null value is assigned or passed.
3. **Readability**: Makes code more readable and self-documenting by clearly indicating non-null constraints.

### Example

Here's an example of using `@NonNull` in a Spring Boot application.

1. **Add Dependencies**: Ensure Lombok is included in your `pom.xml`.

#### Maven

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.24</version>
    <scope>provided</scope>
</dependency>
```

#### Gradle

```gradle
dependencies {
    compileOnly 'org.projectlombok:lombok:1.18.24'
    annotationProcessor 'org.projectlombok:lombok:1.18.24'
}
```

2. **Document Class Using @NonNull**:

```java
package com.example.demo.entity;

import lombok.Data;
import lombok.NonNull;

@Data
public class User {

    @NonNull
    private String name;

    @NonNull
    private String email;
}
```

### Explanation

- **@NonNull**: Applied to fields `name` and `email` to ensure they cannot be null.
- **@Data**: Lombok annotation that generates getters, setters, `toString()`, `equals()`, and `hashCode()` methods.

### Usage

When creating a new instance of the `User` class, Lombok will ensure the fields `name` and `email` are not null.

```java
package com.example.demo;

import com.example.demo.entity.User;

public class DemoApplication {

    public static void main(String[] args) {
        // This will throw a NullPointerException because the email is null
        User user = new User("John", null);
    }
}
```

### Summary

- **@NonNull**: Ensures fields, parameters, or return values are not null, providing null-safety.
- **Lombok Integration**: Works seamlessly with other Lombok annotations like `@Data` to reduce boilerplate code.
- **Automatic Validation**: Throws a `NullPointerException` if a null value is encountered.

Using `@NonNull` in your Spring Boot application helps to prevent null-related errors, making your code more robust and easier to maintain.

# Spring Security

Spring Security is a powerful and highly customizable security framework for Java applications, including those built using Spring Boot. It provides comprehensive security features such as authentication, authorization, session management, and protection against common security threats like cross-site request forgery (CSRF) and cross-site scripting (XSS).

### Key Features of Spring Security

1. **Authentication**: Spring Security allows you to authenticate users using various authentication mechanisms, including form-based authentication, HTTP Basic authentication, and OAuth.

2. **Authorization**: Once authenticated, you can enforce access control policies based on roles, permissions, or custom conditions using Spring Security's authorization mechanisms.

3. **Session Management**: Spring Security provides session management capabilities to manage user sessions securely, including invalidating sessions, preventing session fixation attacks, and tracking active sessions.

4. **Protection against Common Threats**: Spring Security includes features to protect your application against common security threats, such as CSRF protection, XSS protection, and HTTP response header security.

5. **Integration with Spring Boot**: Spring Security seamlessly integrates with Spring Boot, allowing you to configure security settings using simple annotations and properties.

### Basic Concepts

1. **Authentication**: The process of verifying the identity of a user, typically by validating credentials like username and password.

2. **Authorization**: The process of determining whether an authenticated user has the necessary permissions to access a resource or perform an action.

3. **Principal and Authorities**: In Spring Security, a principal represents an authenticated user, and authorities represent the roles or permissions granted to the user.

4. **Security Filters**: Spring Security uses a chain of security filters to intercept incoming requests, authenticate users, enforce access control policies, and perform other security-related tasks.

5. **Security Context**: The security context holds information about the current authenticated user (principal) and their granted authorities. It is accessible throughout the application via a thread-local storage mechanism.

### Example Usage

Here's a simple example of configuring Spring Security in a Spring Boot application:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("user").password("{noop}password").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/public/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin();
    }
}
```

In this example:

- The `SecurityConfig` class is annotated with `@EnableWebSecurity` to enable Spring Security.
- The `configure(AuthenticationManagerBuilder auth)` method configures in-memory authentication with a single user ("user" with password "password" and role "USER").
- The `configure(HttpSecurity http)` method configures URL-based security rules. Requests to "/public/\*\*" are permitted for all users, while any other request requires authentication via form-based login.

### Conclusion

Spring Security is a powerful and flexible security framework that provides robust authentication and authorization features for Java applications, including those built with Spring Boot. By configuring Spring Security, you can protect your application against unauthorized access, secure sensitive resources, and mitigate common security threats effectively.

## `@EnableWebSecurity`

The `@EnableWebSecurity` annotation in Spring Boot is used to enable web security in a Spring application. It is typically applied to a configuration class and allows you to customize the security configuration by extending `WebSecurityConfigurerAdapter` or implementing `WebSecurityConfigurer`.

### Key Features:

1. **Enables Web Security**: Marks a configuration class to enable Spring Security's web security features.

2. **Custom Security Configuration**: Allows you to customize security settings such as authentication, authorization, and CSRF protection.

3. **Method Security**: Integrates with Spring's method security annotations like `@Secured`, `@PreAuthorize`, and `@PostAuthorize`.

### Example:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Security configuration methods can be overridden here
}
```

### Explanation:

- **@Configuration**: Marks the class as a configuration class.
- **@EnableWebSecurity**: Enables Spring Security's web security features.

- **WebSecurityConfigurerAdapter**: Extends this class to customize security settings by overriding its methods.

### Usage:

You can customize the security configuration by overriding methods from `WebSecurityConfigurerAdapter`, such as `configure(HttpSecurity)` for configuring HTTP security, `configure(AuthenticationManagerBuilder)` for configuring authentication, and `configure(WebSecurity)` for configuring web-based security.

### Summary:

- **@EnableWebSecurity**: Enables Spring Security's web security features in a Spring Boot application.
- **Customizable Configuration**: Allows customization of security settings by extending `WebSecurityConfigurerAdapter` or implementing `WebSecurityConfigurer`.

- **Method Security**: Integrates with Spring's method security annotations for securing methods based on roles and permissions.

## `WebSecurityConfigurerAdapter`

`WebSecurityConfigurerAdapter` is a class provided by Spring Security that allows you to customize the web security configuration in a Spring application. It serves as a base class for creating a custom security configuration by extending it and overriding its methods.

### Key Features:

1. **Customizable Security Configuration**: Enables customization of web security settings such as authentication, authorization, and CSRF protection.

2. **Method Overrides**: Provides methods to override default security configurations according to specific requirements.

3. **Integration with Spring Boot**: Seamlessly integrates with Spring Boot applications to define security configurations.

### Example:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Security configuration methods can be overridden here
}
```

### Explanation:

- **@Configuration**: Marks the class as a configuration class.
- **@EnableWebSecurity**: Enables Spring Security's web security features.

- **WebSecurityConfigurerAdapter**: Extends this class to customize security settings by overriding its methods.

### Usage:

You can customize the security configuration by overriding methods from `WebSecurityConfigurerAdapter`, such as `configure(HttpSecurity)` for configuring HTTP security, `configure(AuthenticationManagerBuilder)` for configuring authentication, and `configure(WebSecurity)` for configuring web-based security.

### Summary:

- **WebSecurityConfigurerAdapter**: Class used to customize web security configuration in Spring Security.
- **Customizable Configuration**: Allows customization of security settings by extending `WebSecurityConfigurerAdapter` and overriding its methods.

- **Integration with Spring Boot**: Seamlessly integrates with Spring Boot applications to define security configurations.

## Authentication

Authentication in Spring Security Spring Boot involves verifying the identity of users attempting to access your application's resources. Here's how you can configure authentication in a Spring Boot application using Spring Security:

1. **Add Spring Security Dependency**: Ensure that you have the necessary Spring Security dependencies in your `pom.xml` (for Maven) or `build.gradle` (for Gradle) file.

2. **Configure Authentication Manager**: Extend `WebSecurityConfigurerAdapter` and override the `configure(AuthenticationManagerBuilder auth)` method to specify how users will be authenticated. You can configure authentication using in-memory, JDBC, LDAP, or custom authentication providers.

   ```java
   import org.springframework.context.annotation.Configuration;
   import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
   import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
   import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

   @Configuration
   @EnableWebSecurity
   public class SecurityConfig extends WebSecurityConfigurerAdapter {

       @Override
       protected void configure(AuthenticationManagerBuilder auth) throws Exception {
           auth
               .inMemoryAuthentication()
                   .withUser("user").password("{noop}password").roles("USER");
       }
   }
   ```

   In this example, we configure an in-memory authentication provider with a single user "user" having the password "password" and the role "USER".

3. **Secure Endpoints**: Use method chaining in the `configure(HttpSecurity http)` method to define security rules for different endpoints. You can specify which URLs require authentication, authorization, or are permitted for all users.

   ```java
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
           .authorizeRequests()
               .antMatchers("/public/**").permitAll()
               .anyRequest().authenticated()
           .and()
           .formLogin();
   }
   ```

   In this example, requests to "/public/\*\*" are permitted for all users, while any other request requires authentication via form-based login.

4. **Customize Authentication**: You can customize authentication further by configuring additional features such as password encoding, remember-me authentication, and session management.

   ```java
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
           .authorizeRequests()
               .antMatchers("/public/**").permitAll()
               .anyRequest().authenticated()
           .and()
           .formLogin()
               .loginPage("/login")
               .permitAll()
           .and()
           .rememberMe()
               .tokenValiditySeconds(3600)
               .key("my-remember-me-key")
           .and()
           .logout()
               .permitAll();
   }
   ```

   In this example, we customize the login page URL, remember-me functionality, and logout handling.

5. **Handle Logout**: Optionally, configure logout handling to allow users to log out securely.

   ```java
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
           .logout()
               .logoutUrl("/logout")
               .logoutSuccessUrl("/login?logout")
               .permitAll();
   }
   ```

   This configuration specifies the logout URL and the URL to redirect to after logout.

By following these steps, you can configure authentication in your Spring Boot application using Spring Security. This provides robust security features to protect your application's resources and authenticate users securely.

## Authorization

Authorization in Spring Security Spring Boot involves determining whether authenticated users have permission to access specific resources or perform certain actions within your application. Here's how you can configure authorization in a Spring Boot application using Spring Security:

1. **Add Spring Security Dependency**: Ensure that you have the necessary Spring Security dependencies in your `pom.xml` (for Maven) or `build.gradle` (for Gradle) file.

2. **Secure Endpoints**: Use method chaining in the `configure(HttpSecurity http)` method of your security configuration class to define security rules for different endpoints. You can specify which URLs require authentication, authorization, or are permitted for all users.

   ```java
   import org.springframework.context.annotation.Configuration;
   import org.springframework.security.config.annotation.web.builders.HttpSecurity;
   import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
   import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

   @Configuration
   @EnableWebSecurity
   public class SecurityConfig extends WebSecurityConfigurerAdapter {

       @Override
       protected void configure(HttpSecurity http) throws Exception {
           http
               .authorizeRequests()
                   .antMatchers("/admin/**").hasRole("ADMIN")
                   .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                   .anyRequest().authenticated()
               .and()
               .formLogin()
                   .permitAll()
               .and()
               .logout()
                   .permitAll();
       }
   }
   ```

   In this example:
   - Requests to "/admin/**" require users to have the "ADMIN" role.
   - Requests to "/user/**" require users to have either the "USER" or "ADMIN" role.
   - All other requests require authentication.

3. **Define Roles and Authorities**: Define roles and their corresponding authorities in your authentication provider configuration.

   ```java
   import org.springframework.context.annotation.Configuration;
   import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
   import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
   import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

   @Configuration
   @EnableWebSecurity
   public class SecurityConfig extends WebSecurityConfigurerAdapter {

       @Override
       protected void configure(AuthenticationManagerBuilder auth) throws Exception {
           auth
               .inMemoryAuthentication()
                   .withUser("user").password("{noop}password").roles("USER")
                   .and()
                   .withUser("admin").password("{noop}password").roles("ADMIN");
       }
   }
   ```

   In this example, we define two users: "user" with the role "USER" and "admin" with the role "ADMIN".

4. **Use Annotations for Method-Level Security**: Use `@PreAuthorize`, `@PostAuthorize`, `@Secured`, or `@RolesAllowed` annotations on methods to define fine-grained access control rules.

   ```java
   import org.springframework.security.access.prepost.PreAuthorize;
   import org.springframework.stereotype.Controller;
   import org.springframework.web.bind.annotation.GetMapping;

   @Controller
   public class MyController {

       @GetMapping("/admin/dashboard")
       @PreAuthorize("hasRole('ADMIN')")
       public String adminDashboard() {
           return "admin-dashboard";
       }
   }
   ```

   In this example, the `adminDashboard()` method can only be accessed by users with the "ADMIN" role.

By following these steps, you can configure authorization in your Spring Boot application using Spring Security. This allows you to control access to resources and actions within your application based on user roles and permissions.

## `@Scheduled`

Scheduling tasks in Spring Boot using cron jobs can be efficiently done with the `@Scheduled` annotation. This annotation is part of the Spring Framework’s scheduling support and allows you to execute tasks periodically based on cron expressions or fixed delays/intervals.

### Step-by-Step Guide:

#### 1. Add Spring Boot Starter Dependency

First, ensure you have the `spring-boot-starter` dependency included in your `pom.xml`.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
```

#### 2. Enable Scheduling

You need to enable scheduling in your Spring Boot application by adding the `@EnableScheduling` annotation to a configuration class.

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SchedulingConfig {
}
```

#### 3. Create a Scheduled Task

Create a component class where you define your scheduled tasks using the `@Scheduled` annotation.

```java
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    // A simple example with a fixed rate
    @Scheduled(fixedRate = 5000)
    public void performTaskWithFixedRate() {
        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }

    // A task with a fixed delay
    @Scheduled(fixedDelay = 5000)
    public void performTaskWithFixedDelay() {
        System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
    }

    // A task with an initial delay
    @Scheduled(fixedDelay = 5000, initialDelay = 10000)
    public void performTaskWithInitialDelay() {
        System.out.println("Fixed delay task with initial delay - " + System.currentTimeMillis() / 1000);
    }

    // A task scheduled with a cron expression
    @Scheduled(cron = "0 0/1 * * * ?")
    public void performTaskWithCronExpression() {
        System.out.println("Cron expression task - " + System.currentTimeMillis() / 1000);
    }
}
```

### Cron Expression

A cron expression is a string comprised of six or seven fields separated by white space that represents a schedule:

```
* * * * * *
│ │ │ │ │ │
│ │ │ │ │ └─── Day of the week (0 - 7) (Sunday = 0 or 7)
│ │ │ │ └────── Month (1 - 12)
│ │ │ └──────── Day of the month (1 - 31)
│ │ └────────── Hour (0 - 23)
│ └──────────── Minute (0 - 59)
└────────────── Second (0 - 59) [Optional]
```

### Examples of Cron Expressions:

- `@Scheduled(cron = "0 0 * * * ?")` - Every hour at the top of the hour.
- `@Scheduled(cron = "0 0 12 * * ?")` - Every day at noon.
- `@Scheduled(cron = "0 0 12 * * MON-FRI")` - Every weekday at noon.
- `@Scheduled(cron = "0 0/5 * * * ?")` - Every 5 minutes.

### Using Property Placeholders:

You can also use property placeholders in your cron expressions to make them configurable from application properties.

**application.properties**:

```properties
scheduling.cron.expression=0 0/1 * * * ?
```

**Scheduled Task**:

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Value("${scheduling.cron.expression}")
    private String cronExpression;

    @Scheduled(cron = "${scheduling.cron.expression}")
    public void performTaskWithCronExpression() {
        System.out.println("Cron expression task - " + System.currentTimeMillis() / 1000);
    }
}
```

### Summary

1. **Add Dependency**: Include `spring-boot-starter` dependency.
2. **Enable Scheduling**: Use `@EnableScheduling` in a configuration class.
3. **Define Scheduled Tasks**: Use `@Scheduled` to define methods that will run according to your schedule.
4. **Cron Expressions**: Utilize cron expressions for flexible scheduling.
5. **Property Placeholders**: Use properties files to externalize cron expressions for easy configuration changes.

By following these steps, you can efficiently schedule tasks in a Spring Boot application using cron jobs, providing a robust solution for executing periodic tasks.

## DevTools

DevTools in Spring Boot is a set of development-time features aimed at improving productivity during the development of Spring Boot applications. It provides several convenient features to help streamline the development process. Some of the key features of Spring Boot DevTools include:

1. **Automatic Restart**: DevTools monitors the classpath for changes and automatically restarts the application whenever changes are detected. This eliminates the need to manually stop and restart the application during development.

2. **Live Reload**: DevTools can trigger a browser refresh whenever changes are made to static resources (HTML, CSS, JavaScript) without needing a full-page reload. This feature enhances the development experience by providing instant feedback on changes.

3. **Remote Development**: DevTools allows you to connect to a remote application and provides similar functionalities like automatic restart and live reload. This is particularly useful in scenarios where the development environment is different from the local environment.

4. **Hot Swapping**: DevTools enables hot swapping of classes during development. When changes are made to Java classes, DevTools attempts to reload the classes without restarting the entire application, preserving the application's state.

5. **Property Defaults**: DevTools provides default properties for development, which can be overridden in different environments (e.g., production). This makes it easy to configure the application for development without modifying the production configuration files.

To use Spring Boot DevTools in your application, you typically include the `spring-boot-devtools` dependency in your project's build configuration. For Maven, you can add the following dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

For Gradle:

```groovy
dependencies {
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
}
```

Once you have added the dependency, DevTools will be automatically enabled when you run your Spring Boot application in a development environment. You can customize DevTools behavior by configuring properties in your `application.properties` or `application.yml` file.

Overall, Spring Boot DevTools significantly improves the developer experience by reducing development cycle times and providing tools for rapid iteration and testing.

## Sending emails in a Spring Boot

Sending emails in a Spring Boot application using Gmail SMTP and JavaMailSender is a straightforward process. Below is a step-by-step guide on how to set this up.

### Step-by-Step Guide:

#### 1. Add Dependencies

First, add the necessary dependencies to your `pom.xml` file.

```xml
<dependencies>
    <!-- Spring Boot Starter Mail -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
</dependencies>
```

#### 2. Configure Mail Properties

Next, configure the mail properties in your `application.properties` or `application.yml` file.

**application.properties**:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-email-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.smtp.ssl.trust=smtp.gmail.com
spring.mail.properties.mail.smtp.connectiontimeout=5000
spring.mail.properties.mail.smtp.timeout=3000
spring.mail.properties.mail.smtp.writetimeout=5000
```

**Important**:

- Replace `your-email@gmail.com` with your Gmail address.
- Replace `your-email-password` with your Gmail password. For better security, consider using an App Password instead of your actual Gmail password, especially if you have 2-Step Verification enabled on your Google account.

#### 3. Create a Service to Send Emails

Create a service class to handle email sending logic.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your-email@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
```

#### 4. Create a Controller to Trigger Email Sending

Create a REST controller to trigger the email sending through an endpoint.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendEmail")
    public String sendEmail(@RequestParam String toEmail,
                            @RequestParam String subject,
                            @RequestParam String body) {
        emailService.sendSimpleEmail(toEmail, subject, body);
        return "Email sent successfully!";
    }
}
```

#### 5. Run the Application

Start your Spring Boot application. You can now send an email by making a request to the `/sendEmail` endpoint with the required parameters (`toEmail`, `subject`, `body`).

Example request:

```
http://localhost:8080/sendEmail?toEmail=recipient@example.com&subject=Test%20Subject&body=This%20is%20the%20email%20body.
```

### Summary

1. **Dependencies**: Add `spring-boot-starter-mail` dependency.
2. **Configuration**: Set up mail properties in `application.properties` or `application.yml`.
3. **Service**: Create an `EmailService` to handle email sending.
4. **Controller**: Create an `EmailController` to expose an endpoint for sending emails.
5. **Run & Test**: Start the application and test the email sending functionality by accessing the endpoint.

This setup will enable your Spring Boot application to send emails using Gmail SMTP and JavaMailSender.

## Redis with Spring Boot

Integrating Redis with a Spring Boot application can significantly enhance performance, especially when dealing with caching, session management, and other scenarios that benefit from fast in-memory data storage. Here’s a step-by-step guide to setting up Redis in a Spring Boot application:

### Step-by-Step Guide:

#### 1. Add Dependencies

Add the necessary dependencies to your `pom.xml` file. You need `spring-boot-starter-data-redis`.

```xml
<dependencies>
    <!-- Spring Boot Starter Data Redis -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <!-- Lettuce is the default Redis client library -->
    <dependency>
        <groupId>io.lettuce.core</groupId>
        <artifactId>lettuce-core</artifactId>
    </dependency>
    <!-- Jackson for JSON serialization/deserialization -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
    </dependency>
</dependencies>
```

#### 2. Configure Redis Properties

Configure your Redis properties in `application.properties` or `application.yml`.

**application.properties**:

```properties
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.password=yourpassword (if Redis is password protected)
```

#### 3. Create a Redis Configuration Class

Create a configuration class to set up RedisTemplate and other necessary beans.

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Use StringRedisSerializer for keys
        template.setKeySerializer(new StringRedisSerializer());
        // Use GenericJackson2JsonRedisSerializer for values
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }
}
```

#### 4. Use RedisTemplate in Your Service

Now, you can use `RedisTemplate` in your service class to interact with Redis.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void save(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
```

#### 5. Example Controller

Create a controller to demonstrate the usage of `RedisService`.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping("/save")
    public void save(@RequestParam String key, @RequestParam String value) {
        redisService.save(key, value);
    }

    @GetMapping("/get")
    public Object get(@RequestParam String key) {
        return redisService.get(key);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String key) {
        redisService.delete(key);
    }
}
```

### Additional Use Cases

#### 1. Caching with Redis

Spring Boot also supports caching with Redis through annotations. Here’s how you can configure caching with Redis:

**Enable Caching**:

```java
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
}
```

**Service Class with Caching**:

```java
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Cacheable(value = "dataCache", key = "#key")
    public String getData(String key) {
        // Simulate a slow service call
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Data for " + key;
    }
}
```

**application.properties**:

```properties
spring.cache.type=redis
```

### Summary

1. **Dependencies**: Add `spring-boot-starter-data-redis` and optionally `lettuce-core` and `jackson-databind` for JSON serialization.
2. **Configuration**: Set Redis connection properties and configure `RedisTemplate`.
3. **Service Implementation**: Use `RedisTemplate` to interact with Redis.
4. **Controller**: Create a controller to demonstrate the usage of Redis.
5. **Caching**: Enable caching and use `@Cacheable` for methods that need caching.

By following these steps, you can effectively integrate Redis into your Spring Boot application, enabling powerful caching, session management, and fast data storage capabilities.

## Kafka with Spring Boot

Kafka is an open-source distributed event streaming platform used for building real-time data pipelines and streaming applications. It is designed to handle high volumes of data and provides fault tolerance, scalability, and low-latency processing.

### Key Concepts of Kafka:

1. **Topics**: Kafka organizes data into topics, which are similar to message queues. Producers publish messages to topics, and consumers subscribe to topics to receive messages.

2. **Brokers**: Kafka clusters consist of one or more servers called brokers. Brokers store and manage the topics, partitions, and consumer offsets.

3. **Partitions**: Each topic is divided into one or more partitions, which are distributed across brokers in the Kafka cluster. Partitions allow for parallel processing and scalability.

4. **Producers**: Producers are applications that publish messages to Kafka topics.

5. **Consumers**: Consumers are applications that subscribe to Kafka topics and process messages.

### Using Kafka in Spring Boot Application:

To use Kafka in a Spring Boot application, you can follow these steps:

#### 1. Add Kafka Dependencies

Add the necessary dependencies to your `pom.xml` file:

```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

#### 2. Configure Kafka Properties

Configure Kafka properties in `application.properties` or `application.yml`:

**application.properties**:

```properties
spring.kafka.bootstrap-servers=your-kafka-broker-host:9092
```

Replace `your-kafka-broker-host` with the hostname of your Kafka broker.

#### 3. Define Kafka Producer

Create a Kafka producer class to publish messages to Kafka topics:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
```

#### 4. Define Kafka Consumer

Create a Kafka consumer class to subscribe to Kafka topics and process messages:

```java
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "your-topic-name")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
```

Replace `your-topic-name` with the name of the Kafka topic you want to subscribe to.

#### 5. Publish and Consume Messages

Use the Kafka producer to publish messages to Kafka topics and let the Kafka consumer receive and process messages.

#### Example Usage:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaExampleApplication implements CommandLineRunner {

    @Autowired
    private KafkaProducer kafkaProducer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Publish a message to Kafka topic
        kafkaProducer.sendMessage("your-topic-name", "Hello, Kafka!");
    }
}
```

### Summary:

Kafka is a distributed event streaming platform used for building real-time data pipelines and streaming applications. In Spring Boot, you can use the `spring-kafka` library to integrate Kafka into your application. This involves configuring Kafka properties, defining Kafka producers and consumers, and publishing/consuming messages from Kafka topics. With Kafka, you can build scalable, fault-tolerant, and real-time streaming applications.

# Testing 

## `@SpringBootTest`

`@SpringBootTest` is an annotation in Spring Boot that is used to write integration tests. It provides a way to create an application context for the tests, enabling you to test the entire application or specific parts of it in a real-world scenario.

### Key Features:

1. **Full Application Context**: Loads the full application context for end-to-end integration tests.
2. **Spring TestContext Framework**: Integrates with the Spring TestContext framework, providing out-of-the-box testing support.
3. **Embedded Server**: Can start an embedded server to test web endpoints.
4. **Profiles and Configurations**: Allows specifying active profiles and additional configurations for the test context.

### Example Usage:

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class MyIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHomeEndpoint() {
        ResponseEntity<String> response = restTemplate.getForEntity("/", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).contains("Welcome to my application");
    }
}
```

### Explanation:

- **@SpringBootTest**: Annotates the class to indicate that it is a Spring Boot test that will load the full application context.
  - `webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT`: Starts the web server on a random port.
- **@ActiveProfiles("test")**: Specifies that the `test` profile should be active for this test.

- **TestRestTemplate**: A convenient class provided by Spring Boot for testing RESTful endpoints.

### Web Environment Options:

- **MOCK**: Loads an `ApplicationContext` with a mock servlet environment. This is the default.
- **RANDOM_PORT**: Starts an embedded web server listening on a random port.
- **DEFINED_PORT**: Starts an embedded web server listening on the port defined in the application properties.
- **NONE**: Loads an `ApplicationContext` without an embedded web server.

### Summary:

- **@SpringBootTest**: Used for integration testing in Spring Boot applications, loading the full application context.
- **Full Context Loading**: Allows for comprehensive tests that cover multiple layers of the application.
- **Embedded Server Testing**: Supports testing of web endpoints with an embedded server.
- **Profiles**: Supports the use of profiles to tailor the test environment.

## `@Test`

`@Test` is an annotation provided by the JUnit framework, which is used to define a test method. When a method is annotated with `@Test`, it indicates that the method is a test method that should be executed by the JUnit testing framework.

### Key Features:

1. **Test Method**: Marks a method as a test method.
2. **No Return Value**: Test methods should not return any value.
3. **Exception Handling**: Can specify expected exceptions.
4. **Timeouts**: Can specify timeouts for tests.

### Example Usage:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void testAddition() {
        Calculator calculator = new Calculator();
        int result = calculator.add(2, 3);
        assertEquals(5, result, "2 + 3 should equal 5");
    }

    @Test
    public void testSubtraction() {
        Calculator calculator = new Calculator();
        int result = calculator.subtract(5, 3);
        assertEquals(2, result, "5 - 3 should equal 2");
    }
}
```

### Explanation:

- **@Test**: Marks the `testAddition` and `testSubtraction` methods as test methods.
- **assertEquals**: Asserts that the expected value matches the actual value returned by the method being tested. If the assertion fails, the test fails.

### Advanced Usage:

1. **Expected Exceptions**: You can specify that a test method is expected to throw a particular exception.

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionTest {

    @Test
    public void testForException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Invalid argument");
        });

        assertEquals("Invalid argument", exception.getMessage());
    }
}
```

2. **Timeouts**: You can specify that a test method must complete within a certain period of time.

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import java.time.Duration;

public class TimeoutTest {

    @Test
    public void testWithTimeout() {
        assertTimeout(Duration.ofSeconds(1), () -> {
            // Simulate long-running task
            Thread.sleep(500);
        });
    }
}
```

### Summary:

- **@Test**: Marks a method as a test method in JUnit.
- **Assertions**: Commonly used with assertions to validate the expected outcomes.
- **Exception Handling**: Can handle expected exceptions using `assertThrows`.
- **Timeouts**: Ensures that tests complete within a specified time using `assertTimeout`.

Certainly! Here is an explanation of each of these annotations used in testing frameworks such as JUnit and TestNG, particularly in the context of Spring Boot applications.

## JUnit 5 Annotations:

#### @BeforeEach

- **Purpose**: Runs before each test method in the test class.
- **Use Case**: Set up test data or configure the environment needed for each test.
- **Example**:

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExampleTest {

    @BeforeEach
    public void setup() {
        // Code to set up test environment
    }

    @Test
    public void testExample() {
        // Test code
    }
}
```

#### @AfterEach

- **Purpose**: Runs after each test method in the test class.
- **Use Case**: Clean up resources or reset states modified during the test.
- **Example**:

```java
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class ExampleTest {

    @AfterEach
    public void cleanup() {
        // Code to clean up after each test
    }

    @Test
    public void testExample() {
        // Test code
    }
}
```

#### @BeforeAll

- **Purpose**: Runs once before all the test methods in the test class.
- **Use Case**: Set up expensive resources that are shared among all tests (e.g., a database connection).
- **Note**: The method annotated with `@BeforeAll` must be static.
- **Example**:

```java
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ExampleTest {

    @BeforeAll
    public static void setupAll() {
        // Code to set up resources before all tests
    }

    @Test
    public void testExample() {
        // Test code
    }
}
```

#### @AfterAll

- **Purpose**: Runs once after all the test methods in the test class.
- **Use Case**: Clean up expensive resources that are shared among all tests.
- **Note**: The method annotated with `@AfterAll` must be static.
- **Example**:

```java
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class ExampleTest {

    @AfterAll
    public static void cleanupAll() {
        // Code to clean up resources after all tests
    }

    @Test
    public void testExample() {
        // Test code
    }
}
```

### TestNG Annotations:

TestNG provides additional annotations that offer more granular control over the test lifecycle, often used in conjunction with Spring Boot for comprehensive test setups.

#### @BeforeTestClass

- **Purpose**: Runs once before any method in the current test class is invoked.
- **Use Case**: Initialize resources that are needed across all test methods in the test class.
- **Example**:

```java
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExampleTest {

    @BeforeClass
    public void setupClass() {
        // Code to set up resources before all tests in this class
    }

    @Test
    public void testExample() {
        // Test code
    }
}
```

#### @AfterTestClass

- **Purpose**: Runs once after all the methods in the current test class have been run.
- **Use Case**: Clean up resources that are shared across all test methods in the test class.
- **Example**:

```java
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ExampleTest {

    @AfterClass
    public void cleanupClass() {
        // Code to clean up resources after all tests in this class
    }

    @Test
    public void testExample() {
        // Test code
    }
}
```

#### @BeforeTestMethod

- **Purpose**: Runs before each test method in the test class.
- **Use Case**: Set up preconditions before each test method (same as `@BeforeEach` in JUnit 5).
- **Example**:

```java
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExampleTest {

    @BeforeMethod
    public void setupMethod() {
        // Code to set up preconditions before each test method
    }

    @Test
    public void testExample() {
        // Test code
    }
}
```

#### @AfterTestMethod

- **Purpose**: Runs after each test method in the test class.
- **Use Case**: Clean up after each test method (same as `@AfterEach` in JUnit 5).
- **Example**:

```java
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ExampleTest {

    @AfterMethod
    public void cleanupMethod() {
        // Code to clean up after each test method
    }

    @Test
    public void testExample() {
        // Test code
    }
}
```

#### @BeforeSuite

- **Purpose**: Runs once before any of the tests in the suite are run.
- **Use Case**: Set up global configurations or resources needed for the entire test suite.
- **Example**:

```java
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ExampleTest {

    @BeforeSuite
    public void setupSuite() {
        // Code to set up resources before any tests in the suite
    }

    @Test
    public void testExample() {
        // Test code
    }
}
```

#### @AfterSuite

- **Purpose**: Runs once after all the tests in the suite have been run.
- **Use Case**: Clean up global configurations or resources used throughout the test suite.
- **Example**:

```java
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class ExampleTest {

    @AfterSuite
    public void cleanupSuite() {
        // Code to clean up resources after all tests in the suite
    }

    @Test
    public void testExample() {
        // Test code
    }
}
```

### Summary

- **JUnit 5 Annotations**:
  - `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`
- **TestNG Annotations**:
  - `@BeforeTestClass`, `@AfterTestClass`, `@BeforeTestMethod`, `@AfterTestMethod`, `@BeforeSuite`, `@AfterSuite`

These annotations help manage the setup and teardown process for tests, ensuring that tests are isolated and have a consistent environment.

## `@ParameterizedTest`

`@ParameterizedTest` is an annotation provided by JUnit 5 (JUnit Jupiter) that allows you to run the same test with different inputs. It is used for parameterized tests, where the same test logic is executed multiple times with different sets of parameters. This helps in reducing code duplication and improving test coverage by testing different scenarios with various inputs.

### Key Features:

- **Parameterized Tests**: Runs the same test multiple times with different parameters.
- **Parameter Sources**: Supports various parameter sources such as value sources, method sources, CSV files, and more.

### Example Usage:

1. **@ValueSource**: Provides a simple way to pass a list of values to the test method.

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterizedTestExample {

    @ParameterizedTest
    @ValueSource(strings = {"racecar", "radar", "level"})
    public void testIsPalindrome(String word) {
        assertTrue(isPalindrome(word));
    }

    private boolean isPalindrome(String word) {
        return new StringBuilder(word).reverse().toString().equals(word);
    }
}
```

2. **@CsvSource**: Provides multiple sets of arguments, specified in CSV format.

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvSourceTest {

    @ParameterizedTest
    @CsvSource({
        "apple, 5",
        "banana, 6",
        "cherry, 6"
    })
    public void testWordLength(String word, int expectedLength) {
        assertEquals(expectedLength, word.length());
    }
}
```

3. **@MethodSource**: Uses a method to provide the arguments for the parameterized test.

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MethodSourceTest {

    @ParameterizedTest
    @MethodSource("stringProvider")
    public void testIsPalindrome(String word) {
        assertTrue(isPalindrome(word));
    }

    private boolean isPalindrome(String word) {
        return new StringBuilder(word).reverse().toString().equals(word);
    }

    static Stream<String> stringProvider() {
        return Stream.of("racecar", "radar", "level");
    }
}
```

4. **@EnumSource**: Provides enum constants as arguments to the parameterized test.

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnumSourceTest {

    enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

    @ParameterizedTest
    @EnumSource(Day.class)
    public void testIsWeekday(Day day) {
        assertTrue(day.ordinal() <= Day.FRIDAY.ordinal());
    }
}
```

### Summary:

- **@ParameterizedTest**: An annotation for running the same test with different parameters.
- **@ValueSource**: Provides simple values such as strings, ints, etc.
- **@CsvSource**: Provides multiple sets of arguments in CSV format.
- **@MethodSource**: Uses a method to supply arguments.
- **@EnumSource**: Provides enum constants as arguments.

These annotations and sources help create flexible and comprehensive tests by allowing the same test logic to be executed with various inputs.

## `@Disabled`

`@Disabled` is an annotation in JUnit 5 (JUnit Jupiter) that is used to temporarily disable a test method or a test class. When a test or class is annotated with `@Disabled`, JUnit will skip the execution of the annotated test or all the tests in the annotated class.

### Key Features:

- **Disable Tests**: Skips the execution of specific test methods or entire test classes.
- **Documentation**: You can optionally provide a reason for disabling the test, which can be helpful for documentation and future reference.

### Usage:

#### Disabling a Single Test Method:

You can use `@Disabled` to skip the execution of an individual test method. Optionally, you can provide a reason for disabling the test.

```java
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DisabledTestExample {

    @Test
    public void testEnabled() {
        // This test will run
        System.out.println("This test is enabled and will run.");
    }

    @Disabled("Temporarily disabled until bug #123 is fixed")
    @Test
    public void testDisabled() {
        // This test will be skipped
        System.out.println("This test is disabled and will not run.");
    }
}
```

#### Disabling an Entire Test Class:

You can use `@Disabled` at the class level to skip all the tests in the class.

```java
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("All tests in this class are disabled")
public class DisabledClassExample {

    @Test
    public void test1() {
        // This test will be skipped
        System.out.println("This test is disabled and will not run.");
    }

    @Test
    public void test2() {
        // This test will be skipped
        System.out.println("This test is disabled and will not run.");
    }
}
```

### Summary:

- **Purpose**: Temporarily disables the execution of a test method or an entire test class.
- **Optional Reason**: Allows for an optional string to specify the reason for disabling, aiding in documentation and future reference.
- **Use Cases**:
  - When a test is flaky and needs to be fixed.
  - When the functionality being tested is temporarily broken or not available.
  - When you want to exclude certain tests from a test suite temporarily during development or debugging.

By using `@Disabled`, you can manage which tests should be skipped during the test run, providing flexibility and control over the test execution process.

## `@Mock` and `@InjectMocks`

`@Mock` and `@InjectMocks` are annotations provided by the Mockito framework, which is commonly used in unit testing Java applications, including Spring Boot applications. These annotations help to create mock objects and inject them into the class under test, facilitating isolated testing of components.

### @Mock

- **Purpose**: To create a mock instance of a class or an interface.
- **Use Case**: When you want to create a mock object to simulate the behavior of a real object in a controlled way.
- **Example**:

```java
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleServiceTest {

    @Mock
    private ExampleRepository exampleRepository;

    public ExampleServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetExample() {
        when(exampleRepository.findById(1)).thenReturn(new ExampleEntity(1, "Test"));

        ExampleEntity example = exampleRepository.findById(1);
        assertEquals("Test", example.getName());
    }
}
```

### @InjectMocks

- **Purpose**: To create an instance of the class under test and inject the mock objects into it.
- **Use Case**: When you want to automatically inject mock dependencies into the class under test.
- **Example**:

```java
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleServiceTest {

    @Mock
    private ExampleRepository exampleRepository;

    @InjectMocks
    private ExampleService exampleService;

    public ExampleServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetExample() {
        when(exampleRepository.findById(1)).thenReturn(new ExampleEntity(1, "Test"));

        ExampleEntity example = exampleService.getExample(1);
        assertEquals("Test", example.getName());
    }
}
```

### How They Work Together:

1. **@Mock**: Creates a mock instance of the specified class or interface. In the example above, `exampleRepository` is a mock object.
2. **@InjectMocks**: Creates an instance of the class under test and injects the mock objects into it. In the example above, `exampleService` is the class under test, and `exampleRepository` is injected into it.

### Summary:

- **@Mock**:
  - **Purpose**: Create mock objects.
  - **Use Case**: Simulate the behavior of dependencies.
- **@InjectMocks**:
  - **Purpose**: Inject mock objects into the class under test.
  - **Use Case**: Automatically set up dependencies for the class under test.

These annotations simplify the setup of unit tests by automatically creating and injecting mock dependencies, allowing you to focus on testing the behavior of the class under test in isolation.
