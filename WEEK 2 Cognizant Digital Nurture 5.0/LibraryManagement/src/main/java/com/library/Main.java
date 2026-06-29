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
        System.out.println("   Spring Library Management Configuration Test   ");
        System.out.println("==================================================");

        // 1. Load the Spring application context from classpath XML configuration
        System.out.println("[Container] Initializing Spring ApplicationContext...");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("[Container] ApplicationContext loaded successfully.\n");

        // 2. Retrieve the BookService bean from the context
        System.out.println("[Container] Fetching 'bookService' bean from IoC container...");
        BookService bookService = context.getBean("bookService", BookService.class);

        // 3. Verify dependency injection by calling service operations
        System.out.println("[Container] Executing business operations...");
        bookService.addBook("Introduction to Spring Framework 5");
        bookService.addBook("Clean Code in Java");

        System.out.println("==================================================");
        System.out.println("   All Spring configuration checks passed!        ");
        System.out.println("==================================================");
    }
}
