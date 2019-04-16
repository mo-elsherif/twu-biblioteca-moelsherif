package com.twu.biblioteca.Book;

import com.twu.biblioteca.Checkable.CheckableItem;

import java.util.ArrayList;
import java.util.HashMap;

public class BookFactory {

    public static HashMap<String, CheckableItem> createHardCodedBooksMap() {
        return new HashMap<String, CheckableItem>() {
            {
                put("Harry Potter", new Book("Harry Potter", "J. K. Rowling", 1990));
                put("Lord of the Rings", new Book("Lord of the Rings", "J. R. R. Tolkien", 1995));
                put("Tarzan", new Book("Tarzan", "Edgar Rice Burroughs", 1970));
            }
        };
    }
}
