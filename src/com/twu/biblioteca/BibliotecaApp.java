package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.ToIntFunction;

public class BibliotecaApp {

    private static ArrayList<Book> books = BookFactory.createHardCodedBooks();

    public static void main(String... args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore");
        BooksPrinter.printAllBooks(books);
    }

}
