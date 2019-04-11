package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    private static ArrayList<Book> books = new ArrayList<Book>() {
        {
            add(new Book("Harry Potter"));
            add(new Book("Lord of the Rings"));
            add(new Book("Tarzan"));
        }
    };


    public static void main(String... args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore");
        printAllBooks();
    }

    private static void printAllBooks() {
        for (Book book : books) {
            System.out.println("- " + book.getName());
        }
    }
}
