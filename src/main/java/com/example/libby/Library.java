package com.example.libby;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import android.content.Context;
import android.widget.Toast;
import java.lang.String;

public class Library {
    private Stack<Book> bookHistory;
    private Queue<Book> checkedOutBooks;

    private static Library instance;

    private Library() {
        bookHistory = new Stack<>();
        checkedOutBooks = new LinkedList<>();
    }

    public static synchronized Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addBook(Book book) {
        bookHistory.push(book);
    }

    public void checkOutBook(Book book) {
        if (!book.isCheckedOut()) {
            book.setCheckedOut(true);
            checkedOutBooks.add(book);
        } else {
            System.out.println("Book is already checked out!");
        }
    }

    public void checkInBook(Book book) {
        if (book.isCheckedOut()) {
            book.setCheckedOut(false);
            checkedOutBooks.remove(book);
        }
    }

    public void addToBookHistory(Book book) {
        // Check if the book already exists in the book history
        boolean bookExists = false;
        for (Book b : bookHistory) {
            if (b.getIsbn().equals(book.getIsbn())) {
                // Update the existing book with the new review
                b.setReview(book.getReview());
                bookExists = true;
                break;
            }
        }
        // If the book does not exist, add it to the book history
        if (!bookExists) {
            bookHistory.push(book);
        }
    }

    public Stack<Book> getBookHistory() {
        return bookHistory;
    }

    public Queue<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }
}