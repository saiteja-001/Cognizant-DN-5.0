package com.library.service;

import com.library.repository.BookRepository;

/**
 * Service class that handles business operations for books.
 * Depends on BookRepository.
 */
public class BookService {
    private BookRepository bookRepository;

    /**
     * Setter method for dependency injection.
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
        System.out.println("[Service] Registering a new book: '" + bookName + "'");
        bookRepository.saveBook(bookName);
    }
}
