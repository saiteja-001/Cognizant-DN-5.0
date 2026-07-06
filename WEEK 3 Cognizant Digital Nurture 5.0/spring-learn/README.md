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

---

## 4. Spring Core Country XML Configuration

In this hands-on, we added support for loading Country data from an XML configuration file (`country.xml`).

### XML Configuration (`src/main/resources/country.xml`)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="country" class="com.cognizant.springlearn.Country">
        <property name="code" value="IN" />
        <property name="name" value="India" />
    </bean>

</beans>
```

### Country Class (`Country.java`)
Includes a zero-parameter constructor, getters, setters, and `toString()` with `DEBUG` logger statements to inspect lifecycle invocations.

---

## SME Walkthrough Insights

### 1. Spring XML Bean Configuration Tags
* **`<bean>`**: The primary configuration tag in Spring XML metadata. It tells the Spring IoC Container to instantiate, configure, and manage an object instance.
  * **`id` attribute**: A unique identifier for the bean within the application context. Used to retrieve the bean instance programmatically or inject it as a dependency.
  * **`class` attribute**: The fully qualified name of the Java class (e.g. `com.cognizant.springlearn.Country`) to instantiate.
* **`<property>`**: Used for **setter-based dependency injection**.
  * **`name` attribute**: Matches the setter property name in the target class (following JavaBean conventions, e.g. `code` matches `setCode()`).
  * **`value` attribute**: Assigns a literal string value to the property, which Spring converts to the target type automatically.

### 2. ApplicationContext vs. ClassPathXmlApplicationContext
* **`ApplicationContext`**: The core Spring IoC Container interface. It extends `BeanFactory` and provides advanced features like event publication, internationalization support, and integration with resource loading.
* **`ClassPathXmlApplicationContext`**: A concrete implementation of `ApplicationContext` that loads configuration XML files from the application's classpath (such as `src/main/resources`).

### 3. What Happens During `context.getBean()` Invocation?
1. **Bean Retrieval**: Spring searches its internal singleton cache map for the bean matching the requested ID (`country`).
2. **Instantiation & Configuration (if lazy or prototype)**: If the bean is not yet instantiated (or is prototype-scoped), Spring instantiates the bean using the default constructor, injects properties via setter methods, and processes lifecycle callbacks.
3. **Type Checking & Return**: Spring casts the bean to the requested class (`Country.class`) and returns the fully configured instance.

---

## 5. Hello World RESTful Web Service

We created a simple RESTful web service returning `"Hello World!!"`.

### Endpoint Configuration
* **Controller Class**: `com.cognizant.springlearn.controller.HelloController`
* **Method**: `GET`
* **URL**: `http://localhost:8083/hello`

### Implementation (`HelloController.java`)
```java
package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String sayHello() {
        logger.info("[REST] HelloController.sayHello() execution started.");
        String response = "Hello World!!";
        logger.info("[REST] HelloController.sayHello() execution finished. Returning: {}", response);
        return response;
    }
}
```

---

## SME Walkthrough: Understanding HTTP Headers

An HTTP exchange consists of request headers sent by the client and response headers returned by the server.

### 1. Viewing Headers in Chrome Developer Tools
1. Open Chrome and press **F12** (or right-click and choose **Inspect**) to open Developer Tools.
2. Select the **Network** tab.
3. Navigate to `http://localhost:8083/hello`.
4. Click on the `hello` request in the Name list.
5. In the right panel, select the **Headers** tab. Here you will see:
   * **General**: Request URL, Request Method (`GET`), Status Code (`200 OK`), Remote Address.
   * **Response Headers**:
     * `Content-Type`: `text/plain;charset=UTF-8` (specifies the return mime-type and character encoding).
     * `Content-Length`: `13` (exact size of `"Hello World!!"` in bytes).
     * `Keep-Alive`: `timeout=60` (tells the client that the connection remains open for subsequent requests).
     * `Connection`: `keep-alive`.
     * `Date`: Server timestamp when the response was generated.
   * **Request Headers**: User-Agent, Accept language, cookies, cache policies, etc.

### 2. Viewing Headers in Postman
1. Open Postman and create a new request tab.
2. Select HTTP Method as `GET` and type the URL `http://localhost:8083/hello`.
3. Click the **Send** button.
4. Below the request settings, look at the Response panel.
5. Click on the **Headers** tab to view key-value pairs representing the response headers (such as `Content-Type`, `Content-Length`, `Date`, and `Keep-Alive`).

---

## 6. REST Country Web Service

We created a REST service mapping `/country` to return details of country India.

### Endpoint Configuration
* **Controller Class**: `com.cognizant.springlearn.controller.CountryController`
* **Method**: `GET` (via `@RequestMapping`)
* **URL**: `http://localhost:8083/country`

### Implementation (`CountryController.java`)
```java
package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {
    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public Country getCountryIndia() {
        logger.info("[REST] CountryController.getCountryIndia() execution started.");
        
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("country", Country.class);
        
        logger.info("[REST] CountryController.getCountryIndia() execution finished. Returning: {}", country);
        return country;
    }
}
```

---

## SME Walkthrough: REST Response Serialization & Headers

### 1. What Happens in the Controller Method?
1. The request hits `GET /country`.
2. The controller instantiates `ClassPathXmlApplicationContext("country.xml")`.
3. This XML parse triggers the instantiation of `Country` (invoking the default constructor) and sets the `code` and `name` properties (invoking setters).
4. The method finishes and returns the populated `Country` instance object to the Spring MVC framework.

### 2. How the Bean is Converted to a JSON Response
* Spring MVC uses **Jackson JSON Processor** (`MappingJackson2HttpMessageConverter`) because `spring-boot-starter-web` places Jackson on the classpath.
* Since the controller is marked with `@RestController` (inheriting `@ResponseBody`), the framework intercepts the returned Java object.
* It invokes the object's getter methods (`getCode()` and `getName()`) to read values, dynamically generates the JSON structure `{"code":"IN","name":"India"}`, and writes it directly to the HTTP response stream.

### 3. Header Analysis (Chrome Developer Tools & Postman)
When querying `http://localhost:8083/country`, the following key headers are observed:
* **`Content-Type`**: `application/json` (automatically set by Spring's Jackson converter to tell the client the payload is formatted as JSON).
* **`Transfer-Encoding`**: `chunked` (signifies the body is streamed in chunks rather than calculating a static Content-Length).
* **`Keep-Alive`**: `timeout=60` & **`Connection`**: `keep-alive` (standard HTTP keep-alive policy).



