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

| Feature        | JAR                                             | WAR                                                                      |
| -------------- | ----------------------------------------------- | ------------------------------------------------------------------------ |
| **Usage**      | General-purpose Java applications and libraries | Web applications with servlets, JSP, etc.                                |
| **Deployment** | Can be run on any JVM or used as a library      | Deployed on a web server or servlet container                            |
| **Structure**  | Manifest, classes, and resources                | `WEB-INF`, static content, configuration                                 |
| **Main Class** | Can have an entry point for execution           | Typically does not have a main class, relies on `web.xml` or annotations |

### Summary

- **JAR (Java ARchive)**: A package format for Java applications and libraries, useful for general-purpose deployment and distribution.
- **WAR (Web Application ARchive)**: A package format for web applications, including servlets and JSP, designed for deployment on web servers.

`ps aux | grep 19277`

## Lombok and `@Data`

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

## `RestTemplate`

`RestTemplate` is a synchronous client to perform HTTP requests in Spring Framework. It simplifies communication with HTTP servers and enforces RESTful principles. It provides methods to interact with HTTP endpoints using various HTTP methods, such as GET, POST, PUT, DELETE, etc.

### Key Features:

- **Simplified HTTP Communication**: Makes it easy to perform HTTP operations.
- **RESTful Principles**: Follows RESTful conventions.
- **Customizable**: Supports customizing request and response handling.

### Basic Usage:

#### 1. Creating a `RestTemplate` Instance:

You can create a `RestTemplate` instance using its default constructor.

```java
RestTemplate restTemplate = new RestTemplate();
```

Alternatively, you can configure it with a custom `ClientHttpRequestFactory`.

```java
ClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
RestTemplate restTemplate = new RestTemplate(factory);
```

#### 2. Performing HTTP Operations:

`RestTemplate` provides methods for all HTTP operations. Here are some common examples:

- **GET Request**:

```java
String url = "https://api.example.com/resource";
ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
String body = response.getBody();
System.out.println(body);
```

- **POST Request**:

```java
String url = "https://api.example.com/resource";
MyRequest request = new MyRequest("example");
ResponseEntity<MyResponse> response = restTemplate.postForEntity(url, request, MyResponse.class);
MyResponse responseBody = response.getBody();
System.out.println(responseBody);
```

- **PUT Request**:

```java
String url = "https://api.example.com/resource/1";
MyRequest updateRequest = new MyRequest("updatedExample");
restTemplate.put(url, updateRequest);
```

- **DELETE Request**:

```java
String url = "https://api.example.com/resource/1";
restTemplate.delete(url);
```

#### 3. Handling URI Variables:

You can use URI variables in your request URLs.

```java
String url = "https://api.example.com/resource/{id}";
Map<String, String> uriVariables = new HashMap<>();
uriVariables.put("id", "1");
ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, uriVariables);
String body = response.getBody();
System.out.println(body);
```

#### 4. Customizing Request Headers:

You can customize the request headers using `HttpEntity`.

```java
String url = "https://api.example.com/resource";
HttpHeaders headers = new HttpHeaders();
headers.set("Authorization", "Bearer token");
HttpEntity<MyRequest> requestEntity = new HttpEntity<>(new MyRequest("example"), headers);
ResponseEntity<MyResponse> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, MyResponse.class);
MyResponse responseBody = response.getBody();
System.out.println(responseBody);
```

### Configuration in Spring Boot:

In a Spring Boot application, you can configure a `RestTemplate` bean to be managed by the Spring container.

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

Now, you can autowire `RestTemplate` wherever you need it.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consume")
    public String consume() {
        String url = "https://api.example.com/resource";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}
```

### Summary:

- **RestTemplate**: Simplifies HTTP communication in Spring applications.
- **Key Methods**: `getForEntity`, `postForEntity`, `put`, `delete`, `exchange`, etc.
- **Configuration**: Can be configured as a Spring bean and autowired into Spring components.
- **Customizable**: Supports customizing request and response handling, including headers, URI variables, and more.

`RestTemplate` is a powerful tool for interacting with RESTful services in a Spring application, providing a straightforward API for performing HTTP operations.

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
