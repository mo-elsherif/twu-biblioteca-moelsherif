package com.twu.biblioteca;

import java.util.ArrayList;

public class BookFactory {

    public static ArrayList<Book> createHardCodedBooks() {
        return new ArrayList<Book>() {
            {
                add(new Book("Harry Potter", "J. K. Rowling", 1990));
                add(new Book("Lord of the Rings", "J. R. R. Tolkien", 1995));
                add(new Book("Tarzan", "Edgar Rice Burroughs", 1970));
            }
        };
    }
}
