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

