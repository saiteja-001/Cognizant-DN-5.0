package com.library;

import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   Exercise 1: Basic Spring Application Context   ");
        System.out.println("==================================================");

        // Load the context
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("[Container] Spring Application Context loaded successfully.\n");

        // Retrieve and test BookRepository bean
        BookRepository bookRepository = context.getBean("bookRepository", BookRepository.class);
        bookRepository.saveBook("Sample Book for Exercise 1");

        // Retrieve and test BookService bean
        BookService bookService = context.getBean("bookService", BookService.class);
        bookService.runService();

        System.out.println("==================================================");
        System.out.println("   Exercise 1 verification completed!             ");
        System.out.println("==================================================");
    }
}
