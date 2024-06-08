# Spring Boot
Spring Boot is a framework for building production-ready applications in Java with minimal effort. It simplifies the setup of new Spring applications by offering defaults for configuration and a suite of production-ready features like metrics, health checks, and externalized configuration.

## Bean
### Java Beans
Java Beans are classes that encapsulate multiple objects into a single object (the bean). They are designed to create reusable software components for Java.

### Spring Bean
A Spring Bean is an object created, managed, and destroyed by the Spring Container. Objects are injected into the Spring Container using metadata (XML or annotations), a process known as inversion of control (IoC).
- Beans are instances of a class managed by Spring Container.
- Beans are created once and used by all the project.


### Analogy
- **Farmer**: Spring Framework
- **Farmland**: Spring Container
- **Seeds/Beans**: Spring Beans
- **Cultivating**: Spring Processes

### Spring Bean Lifecycle
1. **Instantiate**: The Spring container finds the bean's definition and instantiates the bean.
2. **Populate Properties**: Spring populates all properties using dependency injection as specified in the bean definition.
3. **Set Bean Name**: If the bean implements `BeanNameAware`, Spring passes the bean's ID to the `setBeanName()` method.
4. **Set Bean Factory**: If the bean implements `BeanFactoryAware`, Spring passes the `BeanFactory` to the `setBeanFactory()` method.
5. **Pre-Initialization**: If there are any `BeanPostProcessors`, Spring calls `postProcessBeforeInitialization()`.
6. **Initialize Beans**: If the bean implements `InitializingBean`, its `afterPropertiesSet()` method is called. If an `init-method` is specified, that method is called.
7. **Post-Initialization**: If there are any `BeanPostProcessors`, Spring calls `postProcessAfterInitialization()`.
8. **Ready to Use**: The bean is now ready for use by the application.
9. **Destroy**: If the bean implements `DisposableBean`, Spring calls the `destroy()` method.

- We can put the bean in to Spring by Wiring and Auto Wiring.
- Wiring mean we manually configure it into the XML file.
- Auto Wiring mean we put the annotations in the Java file then Spring automatically scan the root-context where java configuration file, make it and put into the bag of Spring.

### ApplicationContext
ApplicationContext is a central interface in Spring for configuration and management of beans.

The ApplicationContext is the thing that understands the user's wishes in terms of where and what should be injected based on the configuration a user provides, either through an xml file or annotations.

In a project, especially when using build tools like Maven or Gradle, certain terms are used to uniquely identify and structure the project. These terms include group, artifact, name, and package.

## Project init details

### Group
- **Definition**: The group ID uniquely identifies your project across all projects. It follows a reverse domain name notation (e.g., `com.example`).
- **Purpose**: Helps organize and differentiate projects, particularly when multiple projects are managed within an organization.
- **Example**: `com.example.myapp`

### Artifact
- **Definition**: The artifact ID is the name of the project or module. It uniquely identifies an artifact (a build output like a JAR file) within the group.
- **Purpose**: Specifies the actual output name of the project, such as the name of the JAR file that will be generated.
- **Example**: `myapp`

### Name
- **Definition**: The name is a human-readable name for the project. While it might be similar to the artifact ID, it is intended for display purposes in project listings or documentation.
- **Purpose**: Provides a descriptive, often more readable, name for the project.
- **Example**: `My Application`

### Package
- **Definition**: The package refers to the namespace in which the classes are placed. It usually follows the group ID structure to avoid class name conflicts.
- **Purpose**: Organizes the classes within the project into a structured namespace to avoid name conflicts and to group related classes together.
- **Example**: `com.example.myapp`

### Example in a Maven Project (pom.xml)
```xml
<groupId>com.example</groupId>
<artifactId>myapp</artifactId>
<version>1.0.0</version>
<name>My Application</name>
```

### Archetype
- **Archetype**: A project template in Maven.
- **Purpose**: Standardizes and simplifies the creation of new projects.
- **Usage**: Via mvn `archetype:generate` with various options to specify the template and project details.
- **Customization**: Custom archetypes can be created to meet specific needs.

### JAR (Java ARchive)

**Definition**: A JAR file is a package file format used to aggregate many Java class files, associated metadata, and resources (text, images, etc.) into one file for distribution.

**Purpose**:
- **Deployment**: Eases the distribution and deployment of Java applications.
- **Library**: Bundles multiple Java classes and resources into a single file for use as a library.
- **Execution**: Can be executable (with a main class defined in the manifest file) to run as a standalone application.

**Structure**:
- **Manifest File** (`META-INF/MANIFEST.MF`): Contains metadata about the JAR file and configuration details like the main class.
- **Classes**: Compiled Java classes (`.class` files).
- **Resources**: Additional resources like configuration files, images, etc.

**Example Usage**:
To create a JAR file using Maven:
```bash
mvn package
```
This will generate a JAR file in the `target` directory.

### WAR (Web Application ARchive)

**Definition**: A WAR file is a package file format used to distribute a collection of JavaServer Pages (JSP), servlets, Java classes, XML files, tag libraries, and static web pages (HTML and images) for web applications.

**Purpose**:
- **Web Deployment**: Designed specifically for deploying web applications on a web server or servlet container (like Tomcat, Jetty).

**Structure**:
- **WEB-INF**: Contains configuration and class files.
  - `web.xml`: Deployment descriptor for the web application.
  - `classes/`: Directory for compiled Java classes.
  - `lib/`: Directory for JAR files that the application depends on.
- **Static Content**: HTML, CSS, JavaScript files, and images accessible directly by the client.

**Example Usage**:
To create a WAR file using Maven:
```bash
mvn package
```
This will generate a WAR file in the `target` directory if your Maven project is configured as a web application.

### Comparison

| Feature        | JAR                                          | WAR                                         |
|----------------|----------------------------------------------|---------------------------------------------|
| **Usage**      | General-purpose Java applications and libraries | Web applications with servlets, JSP, etc.   |
| **Deployment** | Can be run on any JVM or used as a library   | Deployed on a web server or servlet container |
| **Structure**  | Manifest, classes, and resources             | `WEB-INF`, static content, configuration    |
| **Main Class** | Can have an entry point for execution        | Typically does not have a main class, relies on `web.xml` or annotations |

### Summary

- **JAR (Java ARchive)**: A package format for Java applications and libraries, useful for general-purpose deployment and distribution.
- **WAR (Web Application ARchive)**: A package format for web applications, including servlets and JSP, designed for deployment on web servers.

`ps aux | grep 19277`

## Inversion of Control (IoC)
In simple terms..

**IOC(Inversion of Control)** is a concept that means: Instead of creating objects with the new operator,let the container do it for you.

**DI(Dependency injection)** is way to inject the dependency of a framework component by the following ways of spring:
1. Contructor injection
2. Setter/Getter injection
3. field injection

## `@Component`
The `@Component` annotation is a core part of the Spring Framework and is used to indicate that a class is a Spring-managed bean. In Spring Boot, `@Component` plays a crucial role in enabling the component-scanning mechanism, which automatically detects and registers beans within the application context.

### Key Features of `@Component`
  1. **Bean Definition**: Marks a Java class as a bean, meaning it will be managed by the Spring IoC container.
  2. **Component Scanning**: Enables automatic detection of classes annotated with `@Component` (or other stereotype annotations) and registers them as beans.
  3. **Stereotype Annotation**: Acts as a generic stereotype for any Spring-managed component.

### Summary

- **@Component**: A core Spring annotation that marks a class as a Spring-managed bean.
- **Component Scanning**: Automatically detects and registers beans annotated with `@Component` or other stereotype annotations.
- **Stereotype Annotations**: Include `@Service`, `@Repository`, and `@Controller`, which are specialized forms of `@Component`.
- **Usage**: Simply annotate a class with `@Component`, and it will be managed by the Spring IoC container. Use `@Autowired` to inject it where needed.
- **Customization**: Use `@ComponentScan` to customize the scanning behavior if necessary.

## `@SpringBootApplication`
The `@SpringBootApplication` annotation is a key feature in Spring Boot that simplifies the configuration and setup of a Spring Boot application. It is a composite annotation that combines several other annotations to provide a comprehensive setup for a Spring application.

### Composition of `@SpringBootApplication`

The `@SpringBootApplication` annotation is a combination of the following three annotations:

1. **@SpringBootConfiguration**: 
    - Acts as a specialization of `@Configuration`, indicating that the class can be used by the Spring IoC container as a source of bean definitions.
2. **@EnableAutoConfiguration**:
    - Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
3. **@ComponentScan**:
    - Enables component scanning so that the web controller classes and other components you create will be automatically discovered and registered as Spring beans.

### Benefits of `@SpringBootApplication`

1. **Convenience**: Combines multiple annotations into one, reducing boilerplate code.
2. **Auto-Configuration**: Enables automatic configuration of your Spring application based on the dependencies you have added.
3. **Component Scanning**: Automatically scans the package of the annotated class and all sub-packages for Spring components.

### Customizing Behavior

While `@SpringBootApplication` provides default settings, you can customize its behavior as needed:

#### Disabling Specific Auto-Configuration Classes

You can disable specific auto-configuration classes using the `exclude` attribute:

```java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DemoApplication {
    // ...
}
```

#### Customizing Component Scanning

You can customize the base packages to scan using the `@ComponentScan` annotation:

```java
@SpringBootApplication
@ComponentScan(basePackages = "com.example.custom")
public class DemoApplication {
    // ...
}
```

### Summary

- **@SpringBootApplication**: A composite annotation that combines `@SpringBootConfiguration`, `@EnableAutoConfiguration`, and `@ComponentScan`.
- **Purpose**: Simplifies the setup and configuration of a Spring Boot application.
- **Usage**: Placed on the main application class, serving as the entry point for the application.
- **Benefits**: Reduces boilerplate code, enables auto-configuration, and automatically scans for components.
- **Customization**: Allows disabling specific auto-configurations and customizing component scanning.

## ORM in Spring Boot

**Object-Relational Mapping (ORM)** is a technique used to map Java objects to database tables. In Spring Boot, this is typically achieved using Spring Data JPA.

### Key Points

1. **Entity**: A Java class representing a table, annotated with `@Entity`.
2. **Repository**: Interface extending `JpaRepository` for CRUD operations.
3. **Configuration**: 
   - Add dependencies (`spring-boot-starter-data-jpa` and a database driver like `h2`).
   - Configure datasource properties in `application.properties`.

### Example

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

### Summary

ORM in Spring Boot, primarily via Spring Data JPA, simplifies database interactions by mapping Java objects to database tables, providing repository abstractions, and reducing boilerplate code.

## JPA in Spring Boot

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
 
## ResponseEntity 
**ResponseEntity** is a class in Spring Boot that represents the entire HTTP response, including the status code, headers, and body. It is a powerful and flexible way to handle HTTP responses in Spring applications.

### Key Features

1. **Status Code**: Allows you to set the HTTP status code of the response.
2. **Headers**: Enables you to include HTTP headers in the response.
3. **Body**: Allows you to include a body (typically JSON or XML) in the response.

### Common Usage

#### Basic ResponseEntity

A simple usage of `ResponseEntity` might look like this:

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello, World!", HttpStatus.OK);
    }
}
```

In this example, the `hello` method returns a `ResponseEntity` with a body containing "Hello, World!" and an HTTP status of 200 OK.

#### ResponseEntity with Headers

You can also add headers to the response:

```java
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/greet")
    public ResponseEntity<String> greet() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomValue");
        
        return new ResponseEntity<>("Greetings!", headers, HttpStatus.OK);
    }
}
```

This example adds a custom header to the response.

### Practical Example

#### Service Layer

```java
package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
```

#### Controller Layer

```java
package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
```

### Summary

- **ResponseEntity**: Represents the entire HTTP response.
- **Status Code**: Set using `HttpStatus`.
- **Headers**: Include custom headers.
- **Body**: Include the response body.
- **Usage**: Useful for full control over HTTP responses, commonly used in RESTful APIs.

Using `ResponseEntity` in Spring Boot provides fine-grained control over the HTTP response, allowing developers to customize status codes, headers, and the response body effectively.

## Lombok and @Data

**Lombok** is a Java library that helps reduce boilerplate code by generating common methods (like getters, setters, toString, etc.) at compile time using annotations. This makes your code cleaner and more maintainable.

### Key Features of Lombok

1. **Automatic Generation**: Generates commonly used methods such as getters, setters, equals, hashCode, and toString.
2. **Annotations**: Uses annotations to indicate which methods to generate.
3. **Integration**: Easily integrates with Spring Boot and other Java frameworks.

### @Data Annotation

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

##### Gradle
```gradle
dependencies {
    compileOnly 'org.projectlombok:lombok:1.18.24'
    annotationProcessor 'org.projectlombok:lombok:1.18.24'
}
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


## `@RequestBody`

The `@RequestBody` annotation in Spring Boot is used to map the HTTP request body to a Java object. It is commonly used in RESTful web services to accept JSON or XML data from clients.

### Key Features

1. **Data Binding**: Automatically binds the HTTP request body to a Java object.
2. **JSON/XML Support**: Works with various data formats like JSON and XML.
3. **Validation**: Can be combined with validation annotations to enforce constraints on the incoming data.

### Example

Here’s an example of how to use `@RequestBody` in a Spring Boot application to handle a POST request.

1. **Add Dependencies**: Ensure you have Spring Web dependency in your `pom.xml`.

#### Maven
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
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
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok:1.18.24'
    annotationProcessor 'org.projectlombok:lombok:1.18.24'
}
```

2. **Create a Data Transfer Object (DTO)**:

```java
package com.example.demo.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private String email;
}
```

3. **Create a REST Controller**:

```java
package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody UserDTO user) {
        // Handle the incoming user data
        return ResponseEntity.ok("User created: " + user.getName());
    }
}
```

### Explanation

- **@RequestBody**: Maps the JSON or XML from the HTTP request body to the `UserDTO` object.
- **@PostMapping**: Maps HTTP POST requests to the `/users` endpoint.
- **ResponseEntity**: Used to construct the response, including status and body.

### Usage

When a client sends a POST request to `/users` with a JSON body, the data is automatically bound to the `UserDTO` object.

#### Example POST Request
```json
{
    "name": "John Doe",
    "email": "john.doe@example.com"
}
```

### Summary

- **@RequestBody**: Maps HTTP request body to a Java object.
- **Automatic Binding**: Handles JSON and XML data seamlessly.
- **Validation**: Can be combined with validation annotations for data integrity.

Using `@RequestBody` in Spring Boot simplifies handling input data in RESTful web services, providing an easy and efficient way to bind request data to Java objects.

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

#### Gradle
```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
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

### `@PathVariable`
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

## `@RestController`
The `@RestController` annotation in Spring Boot combines the `@Controller` and `@ResponseBody` annotations. It is used to create RESTful web services by mapping HTTP requests to handler methods and returning data directly as the response body.

### Key Features

1. **RESTful Endpoints**: Maps HTTP requests to controller methods and returns data as the response body.
2. **JSON/XML Support**: Supports returning data in JSON or XML format.
3. **Simplified Configuration**: Combines the functionality of `@Controller` and `@ResponseBody`.

### Example

Here's an example of how to use `@RestController` in a Spring Boot application.

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
```

### Explanation

- **@RestController**: Marks the class `HelloController` as a REST controller, indicating that it will handle HTTP requests and return data directly as the response body.
- **@GetMapping**: Maps HTTP GET requests to the `/hello` endpoint.
- **Response Body**: The return value of the `sayHello()` method is returned directly as the response body.

### Usage

When a client accesses `/hello`, the `sayHello()` method will be invoked, and "Hello, World!" will be returned as the response body.

### Summary

- **@RestController**: Combines `@Controller` and `@ResponseBody` annotations to simplify the creation of RESTful web services.
- **RESTful Endpoints**: Maps HTTP requests to controller methods and returns data directly as the response body.
- **JSON/XML Support**: Supports returning data in JSON or XML format, based on the client's request.

Using `@RestController` in Spring Boot simplifies the development of RESTful web services by eliminating the need for manual conversion of data to JSON or XML format, allowing for more concise and readable code.

## `@Mappings`
It seems you're referring to the various `@RequestMapping` annotations in Spring MVC. These annotations are used to map web requests to specific handler methods in your controllers. Here are the commonly used `@RequestMapping` annotations:

1. **@GetMapping**: Handles HTTP GET requests. It is a composed annotation that acts as a shortcut for `@RequestMapping(method = RequestMethod.GET)`.
   
2. **@PostMapping**: Handles HTTP POST requests. Similar to `@GetMapping`, it is a shortcut for `@RequestMapping(method = RequestMethod.POST)`.

3. **@PutMapping**: Handles HTTP PUT requests. It's a shortcut for `@RequestMapping(method = RequestMethod.PUT)`.

4. **@DeleteMapping**: Handles HTTP DELETE requests. It's a shortcut for `@RequestMapping(method = RequestMethod.DELETE)`.

5. **@PatchMapping**: Handles HTTP PATCH requests. It's a shortcut for `@RequestMapping(method = RequestMethod.PATCH)`.

### Example:

```java
import org.springframework.web.bind.annotation.*;

@RestController
public class ExampleController {

    @GetMapping("/get")
    public String handleGetRequest() {
        return "Handled GET request";
    }

    @PostMapping("/post")
    public String handlePostRequest() {
        return "Handled POST request";
    }

    @PutMapping("/put")
    public String handlePutRequest() {
        return "Handled PUT request";
    }

    @DeleteMapping("/delete")
    public String handleDeleteRequest() {
        return "Handled DELETE request";
    }

    @PatchMapping("/patch")
    public String handlePatchRequest() {
        return "Handled PATCH request";
    }
}
```

In this example, each method in the `ExampleController` class maps to a specific HTTP method using one of the `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, or `@PatchMapping` annotations.


## `@Transactional`
`@Transactional` is an annotation used in Spring Boot to mark a method, class, or interface as transactional. It is typically used in service layer methods to ensure that the operations within the method are executed within a transactional context. 

### Key Features:

1. **Transactional Context**: Ensures that all operations within the annotated method are executed within a single transaction.
  
2. **Automatic Rollback**: If an exception occurs during the transaction, Spring automatically rolls back the changes made within that transaction to maintain data consistency.

3. **Scope Flexibility**: Can be applied at the method level, class level, or even at the interface level.

### Example:

```java
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExampleService {

    @Transactional
    public void performTransactionalOperation() {
        // Perform database operations within a transactional context
    }
}
```

### Explanation:

- **@Transactional**: Marks the `performTransactionalOperation()` method as transactional. Any database operations within this method will be executed within a single transaction.

### Usage:

When `performTransactionalOperation()` is called, all database operations within the method will be grouped into a single transaction. If any operation fails (e.g., due to an exception), the entire transaction will be rolled back, ensuring data consistency.

### Summary:

- **@Transactional**: Marks methods, classes, or interfaces as transactional, ensuring that operations within them are executed within a transactional context.
  
- **Transactional Context**: Provides a mechanism for grouping database operations into a single transaction, ensuring data consistency and integrity.

- **Automatic Rollback**: If an exception occurs during the transaction, Spring automatically rolls back the changes made within that transaction to maintain data consistency.

## `@EnableTransactionManagement`
`@EnableTransactionManagement` is an annotation in Spring Boot used to enable Spring's annotation-driven transaction management capability. It's typically used at the configuration level to enable transactional behavior for Spring beans.

### Key Features:

1. **Annotation-driven Transaction Management**: Enables Spring's support for declarative transaction management using annotations such as `@Transactional`.

2. **Flexible Configuration**: Allows you to customize transaction management behavior through properties and configuration options.

3. **Integration with AOP**: Internally, it integrates with Spring's Aspect-Oriented Programming (AOP) infrastructure to intercept method calls and apply transactional behavior.

### Example:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class AppConfig {
    // Configuration beans and other application configuration
}
```

### Explanation:

- **@EnableTransactionManagement**: Marks the configuration class (`AppConfig` in this example) to enable Spring's annotation-driven transaction management capability.

- **Configuration Class**: Typically, this annotation is used in a configuration class to enable transaction management for the entire application.

### Usage:

By adding `@EnableTransactionManagement` to your configuration class, you enable Spring's support for declarative transaction management using annotations such as `@Transactional`. Any bean annotated with `@Transactional` will have transactional behavior applied to its methods.

### Summary:

- **@EnableTransactionManagement**: Enables Spring's support for annotation-driven transaction management.

- **Flexible Configuration**: Allows customization of transaction management behavior through properties and configuration options.

- **Integration with AOP**: Internally integrates with Spring's Aspect-Oriented Programming (AOP) infrastructure to apply transactional behavior to methods annotated with `@Transactional`.

## `PlatformTransactionManager`
`PlatformTransactionManager` is an interface in the Spring Framework that defines the contract for transaction management. It's typically used to manage transactions in Spring applications.

### Key Features:

1. **Transaction Management Interface**: Defines the methods for transaction management operations such as beginning, committing, and rolling back transactions.

2. **Multiple Implementations**: Spring provides various implementations of the `PlatformTransactionManager` interface, such as `DataSourceTransactionManager`, `JpaTransactionManager`, and `HibernateTransactionManager`, to support different transaction management scenarios.

### Example:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
```

### Explanation:

- **DataSourceTransactionManager**: In this example, we configure a `DataSourceTransactionManager` bean by providing a `DataSource`. This transaction manager implementation is suitable for JDBC-based transactions.

- **@Bean**: This annotation is used to declare a bean of type `PlatformTransactionManager` in the Spring application context.

### Usage:

The configured `PlatformTransactionManager` bean will be used by Spring to manage transactions within the application. It can be injected into service classes or repository classes where transactional behavior is required.

### Summary:

- **PlatformTransactionManager**: An interface in the Spring Framework that defines transaction management operations.
  
- **Multiple Implementations**: Spring provides various implementations of `PlatformTransactionManager` to support different transaction management scenarios, such as JDBC, JPA, and Hibernate transactions.

- **Configuration**: It's typically configured as a bean in the Spring application context to enable transaction management.

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


## `@Configuration`
The `@Configuration` annotation in Spring Boot is used to indicate that a class declares one or more `@Bean` methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.

### Key Features:

1. **Bean Definition**: Marks a class as a source of bean definitions for the Spring application context.
  
2. **Declares Bean Methods**: Allows the declaration of `@Bean` methods within the class to define Spring beans.

3. **Configuration Classes**: Often used to centralize bean definitions and related configurations in one place.

### Example:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ExampleService exampleService() {
        return new ExampleService();
    }

    // Other @Bean methods can be declared here
}
```

### Explanation:

- **@Configuration**: Marks the `AppConfig` class as a configuration class, indicating that it contains bean definitions for the Spring application context.

- **@Bean**: Declares a method (`exampleService()` in this example) as a bean definition method. The return value of the method becomes the bean instance in the Spring application context.

### Usage:

Configuration classes with `@Configuration` annotation are used to centralize the configuration of beans and other components in a Spring Boot application. Beans declared in configuration classes can be injected into other components using dependency injection.

### Summary:

- **@Configuration**: Indicates that a class contains bean definitions and other configuration for the Spring application context.

- **Bean Definition Methods**: Declares methods annotated with `@Bean` to define beans within the Spring application context.

- **Centralized Configuration**: Configuration classes help centralize bean definitions and related configurations in one place for easier management and organization.

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

## Spring Boot profiles
Spring Boot profiles allow you to define different configurations for different environments or scenarios, such as development, testing, production, etc. These profiles help in managing environment-specific configurations without the need for manual changes to the configuration files.

### How to Define Profiles:

1. **application.properties/application.yml**: Define properties or configurations specific to each profile in separate files, such as `application-dev.properties` for development, `application-prod.properties` for production, etc.

2. **application-{profile}.properties/application-{profile}.yml**: Use naming conventions to create profile-specific configuration files, where `{profile}` is the name of the profile (e.g., `dev`, `prod`, `test`).

### How to Activate Profiles:

1. **Using `spring.profiles.active` Property**: Set the `spring.profiles.active` property in the `application.properties` file to specify the active profiles.

   ```properties
   spring.profiles.active=dev
   ```

2. **Command Line Arguments**: Specify active profiles as command-line arguments when running the application.

   ```bash
   java -jar -Dspring.profiles.active=dev my-application.jar
   ```

3. **Environment Variable**: Set the `SPRING_PROFILES_ACTIVE` environment variable to specify the active profiles.

   ```bash
   export SPRING_PROFILES_ACTIVE=dev
   ```

### Example:

Suppose you have the following `application-dev.properties` and `application-prod.properties` files:

**application-dev.properties**:
```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/dev_db
```

**application-prod.properties**:
```properties
server.port=9090
spring.datasource.url=jdbc:mysql://production-server:3306/prod_db
```

In this example, when you set the `spring.profiles.active` property to `dev`, the application will use the configuration from `application-dev.properties`. Similarly, when set to `prod`, it will use the configuration from `application-prod.properties`.

### How to Use Profiles in Configuration Classes:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {
    // Dev-specific configuration
}

@Configuration
@Profile("prod")
public class ProdConfig {
    // Prod-specific configuration
}
```

In this example, the `DevConfig` class will be active only when the `dev` profile is active, and the `ProdConfig` class will be active only when the `prod` profile is active.

### Summary:

- **Profiles**: Allow you to define different configurations for different environments or scenarios.
- **Activation**: Profiles can be activated using properties, command-line arguments, or environment variables.
- **Usage**: Profiles can be used to configure properties, beans, and other components in Spring Boot applications.









# TODO
- [ ] Automacity
- [x] Sharding in MongoDB
- [ ] Spring Security
- [ ] Authentication and Authorization
- [ ] Headers in HTTP
- [ ] Method Chaining
- [ ] Cookie
- [ ] what is scope in POM dependencies
- [ ] @SpringBootTest, @Test
- [ ] @BeforeEach, @BeforeAll, @AfterEach, @AfterAll
- [ ] @ParameterizedTest
- [ ] @CsvSource, @ValueSource
- [ ] @Disabled
- [ ] @Mock @InjectMocks
- [ ] build() and builder()
- [ ] Test code coverage
- [ ] SonarQube, SonarLint, and SonarCloud
- [ ] RestTemplate
- [ ] Serialization and Deserialization
- [ ] Eleven labs
- [ ] July Rating Sheet


/akdshakufhnkasjefnlkswajhfu





