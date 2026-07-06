# SME Walkthrough: spring-learn

This project is a Spring Web application created using Maven. Below is an architectural and structural walkthrough of the project components.

---

## 1. Project Directory Structure

- **`src/main/java`**: The root directory for the application's source code. 
  - Contains packages such as `com.cognizant.springlearn`.
  - All Java files, components, controllers, services, repositories, and the entry-point execution class reside here.
- **`src/main/resources`**: Holds configuration and static files.
  - Contains configuration properties like `application.properties` (defining server settings, logger configuration, etc.).
  - Can host static assets (`static/` folder) and UI templates (`templates/` folder) for web presentation.
- **`src/test/java`**: Reserved strictly for unit and integration testing files.
  - Holds code structured to mirror the main application's package hierarchy.
  - Test suites run separately and are not packaged into production binaries.

---

## 2. SpringLearnApplication Entrypoint

The main entry point of our Spring Boot application is `SpringLearnApplication.java`:

```java
package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLearnApplication {
    private static final Logger logger = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        logger.info("==================================================");
        logger.info("   Starting SpringLearnApplication main() method  ");
        logger.info("==================================================");

        SpringApplication.run(SpringLearnApplication.class, args);

        logger.info("==================================================");
        logger.info("   SpringLearnApplication is running successfully ");
        logger.info("==================================================");
    }
}
```

### The `main()` Method Walkthrough
- Under the hood, `SpringApplication.run(SpringLearnApplication.class, args)` starts the application.
- It bootstraps the Spring context, initializes the embedded Tomcat Web Server (listening on port `8082`), and runs auto-configurations.

### The Purpose of `@SpringBootApplication` Annotation
This annotation is a convenience annotation that bundles three core Spring Boot annotations:
1. **`@SpringBootConfiguration`**: Denotes that this class provides Spring Boot configuration (marking it as a configuration source).
2. **`@EnableAutoConfiguration`**: Instructs Spring Boot to automatically configure beans based on dependencies present on the classpath (e.g. configuring Tomcat and Spring MVC setup because `spring-boot-starter-web` is on the classpath).
3. **`@ComponentScan`**: Enables scanning for Spring components, configurations, controllers, and services (beans) starting from this package (`com.cognizant.springlearn`) and its subpackages.

---

## 3. Maven Configuration (`pom.xml`)

The `pom.xml` handles the project's build settings and dependency tree:

- **Parent POM (`spring-boot-starter-parent`)**: Declares standard dependency versions, default plugin settings, and configuration properties.
- **Dependencies**:
  1. `spring-boot-starter-web`: Pulls in core Spring Boot starter modules, Jackson parser, embedded Tomcat, and Spring WebMVC components.
  2. `spring-boot-devtools`: Enjoys automatic restarts, live reload capabilities, and configuration caching for improved developer efficiency.
  3. `spring-boot-starter-test`: Introduces testing frameworks including JUnit 5 (Jupiter), AssertJ, Hamcrest, and Mockito.
- **Spring Boot Maven Plugin**: Bundles the application into an executable archive JAR containing all dependencies (fat jar).
