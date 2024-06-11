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
