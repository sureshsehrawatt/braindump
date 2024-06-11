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
