package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main application class to initialize the Spring IoC Container and run tests.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("     Spring Dependency Injection Verification      ");
        System.out.println("==================================================");

        // 1. Load the Spring application context from classpath XML configuration
        System.out.println("[Container] Initializing Spring ApplicationContext...");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("[Container] ApplicationContext loaded successfully.\n");

        // 2. Retrieve and test Setter-Injected BookService bean
        System.out.println("[Container] Fetching 'bookServiceSetter' bean...");
        BookService serviceSetter = context.getBean("bookServiceSetter", BookService.class);
        serviceSetter.addBook("Design Patterns in Java");
        System.out.println();

        // 3. Retrieve and test Constructor-Injected BookService bean
        System.out.println("[Container] Fetching 'bookServiceConstructor' bean...");
        BookService serviceConstructor = context.getBean("bookServiceConstructor", BookService.class);
        serviceConstructor.addBook("Algorithms Unlocked");
        System.out.println();

        System.out.println("==================================================");
        System.out.println("   All Dependency Injection tests passed!         ");
        System.out.println("==================================================");
    }
}
