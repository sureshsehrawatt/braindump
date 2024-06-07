# Maven
Sure! Here's a comprehensive tutorial on Apache Maven, a powerful build automation tool used primarily for Java projects.

### What is Maven?

Maven is a build automation tool used for Java projects, though it can be used for other languages as well. It uses a project object model (POM) file to manage a project's build, dependencies, and documentation.

### Key Concepts

1. **POM (Project Object Model):** The fundamental unit of work in Maven is the POM file (`pom.xml`). It contains information about the project and configuration details used by Maven to build the project.
2. **Dependencies:** Libraries that the project depends on. Maven handles downloading and managing these dependencies.
3. **Repositories:** Locations where Maven stores project artifacts and dependencies. There are local and remote repositories.
4. **Plugins:** Extensions that add extra functionality to Maven, used for tasks such as compiling code, packaging binaries, running tests, etc.

### POM File Structure

The `pom.xml` file is the heart of a Maven project. Here’s a basic example:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0-SNAPSHOT</version>
    <!-- SNAPSHOT - created when software is under development or unstable -->
    <!-- Release - created when software is developed and ready for release and for stable -->

    <dependencies>
        <!-- Add your project dependencies here -->
    </dependencies>

    <build>
        <plugins>
            <!-- Add your build plugins here -->
        </plugins>
    </build>
</project>
```

### Building and Running the Project

1. **Compile the Project:**
   ```sh
   mvn compile
   ```

2. **Package the Project:**
   ```sh
   mvn package
   ```
   - This creates a `jar` file in the `target` directory.

3. **Run the Project:**
   - You can run the packaged `jar` file using:
    ```sh
    java -jar target/my-app-1.0-SNAPSHOT.jar
    ```

### Common Maven Commands

1. **Clean the project:**
   ```sh
   mvn clean
   ```
   - Deletes the `target` directory.

2. **Compile the project:**
   ```sh
   mvn compile
   ```

3. **Run tests:**
   ```sh
   mvn test
   ```

4. **Package the project:**
   ```sh
   mvn package
   ```

5. **Install the project into the local repository:**
   ```sh
   mvn install
   ```

6. **Deploy the project to a remote repository:**
   ```sh
   mvn deploy
   ```

### Maven Repositories

- **Local Repository:** Located on your machine in the `.m2` directory.
- **Central Repository:** Default remote repository provided by Maven.
- **Remote Repository:** Custom repositories you can configure in the `pom.xml` or `settings.xml`.

### Types of POM
1. Simple POM
2. Super POM
3. Effective POM

## Maven Phases
Although hardly a comprehensive list, these are the most common default lifecycle phases executed.

- **validate**: validate the project is correct and all necessary information is available
- **compile**: compile the source code of the project
- **test**: test the compiled source code using a suitable unit testing - framework. These tests should not require the code be packaged or deployed
- **package**: take the compiled code and package it in its distributable format, such as a JAR.
- **integration-test**: process and deploy the package if necessary into an environment where integration tests can be run
verify: run any checks to verify the package is valid and meets quality criteria
- **install**: install the package into the local repository, for use as a dependency in other projects locally
- **deploy**: done in an integration or release environment, copies the final package to the remote repository for sharing with other developers and projects.

There are two other Maven lifecycles of note beyond the default list above. They are

- **clean**: cleans up artifacts created by prior builds
- **site**: generates site documentation for this project