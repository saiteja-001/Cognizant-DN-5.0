package com.library.repository;

/**
 * Repository class that handles data operations for books.
 */
public class BookRepository {

    /**
     * Simulates saving a book record to the database.
     * 
     * @param bookName The name of the book.
     */
    public void saveBook(String bookName) {
        System.out.println("[Database] Successfully saved book: '" + bookName + "' to the persistent repository.");
    }
}
