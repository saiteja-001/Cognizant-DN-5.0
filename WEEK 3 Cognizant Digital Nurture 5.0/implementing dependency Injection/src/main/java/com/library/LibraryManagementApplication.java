package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main application class to initialize the Spring IoC Container and verify Dependency Injection.
 */
public class LibraryManagementApplication {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   Exercise 2: Dependency Injection Verification  ");
        System.out.println("==================================================");

        // 1. Initialize Spring context
        System.out.println("[Container] Initializing Spring ApplicationContext...");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("[Container] ApplicationContext loaded successfully.\n");

        // 2. Retrieve BookService bean
        System.out.println("[Container] Fetching 'bookService' bean from context...");
        BookService bookService = context.getBean("bookService", BookService.class);

        // 3. Test business operation (dependency repository should be called via injected bean)
        System.out.println("[Container] Invoking service operation...");
        bookService.addBook("Pro Spring 5: An In-Depth Guide");
        bookService.addBook("Design Patterns: Elements of Reusable Object-Oriented Software");

        System.out.println("==================================================");
        System.out.println("   Dependency Injection test completed!           ");
        System.out.println("==================================================");
    }
}
