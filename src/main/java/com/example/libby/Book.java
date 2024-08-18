package com.example.libby;
public class Book extends CheckOut {
    private String title;
    private String author;
    private String isbn;
    private boolean checkedOut;
    private String format;
    private boolean completed;
    private String review;
    private String genre;
    private int pageLength;

    public Book() {
        // Initialize variables or leave them null if appropriate
        this.title = "";
        this.author = "";
        this.isbn = "";
        this.format = "";
        this.checkedOut = false;
        this.completed = false;
        this.review = "";
        this.genre = "";
        this.pageLength = 0;
    }

    public Book(String title, String author, String isbn, String format) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.format = format;
        this.checkedOut = false;
        this.completed = false;
        this.review = "";
    }

    public Book(String genre, int pageLength) {
        this.genre = genre;
        this.pageLength = pageLength;
    }



    public Book(String book, String author, int pageNum) {
        super();
    }


    public String getBookTitle()
    {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPageLength() {
        return pageLength;
    }

    public void setPageLength(int pageLength) {
        this.pageLength = pageLength;
    }
}







