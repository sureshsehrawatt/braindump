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

