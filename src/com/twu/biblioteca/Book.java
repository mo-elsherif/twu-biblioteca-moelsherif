package com.twu.biblioteca;


public class Book extends CheckableItem {

    private String author;

    public Book(String name, String author, int year) {
        super(name, year);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
}
