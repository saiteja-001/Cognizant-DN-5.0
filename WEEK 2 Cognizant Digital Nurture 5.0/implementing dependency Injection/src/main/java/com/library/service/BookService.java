package com.library.service;

import com.library.repository.BookRepository;

/**
 * Service class that handles business operations for books.
 * Demonstrates both Constructor and Setter Injection.
 */
public class BookService {
    private BookRepository bookRepository;
    private String injectionType;

    /**
     * Default constructor (needed for setter injection).
     */
    public BookService() {
        this.injectionType = "Setter Injection";
    }

    /**
     * Constructor for Constructor Injection.
     * 
     * @param bookRepository The repository to inject.
     */
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.injectionType = "Constructor Injection";
    }

    /**
     * Setter method for Setter Injection.
     * 
     * @param bookRepository The repository to inject.
     */
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Business operation to register a book in the library.
     * 
     * @param bookName The name of the book.
     */
    public void addBook(String bookName) {
        System.out.println("[Service - via " + injectionType + "] Registering a new book: '" + bookName + "'");
        bookRepository.saveBook(bookName);
    }
}
