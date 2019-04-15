package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    public static ArrayList<Book> books = BookFactory.createHardCodedBooks();

    public static String[] menuOptions = {"List all books", "Checkout a Book", "Return a Book", "Quit"};

    public static void main(String... args) {
        printConsoleMessages(System.out, System.in);
    }

    public static void printConsoleMessages(PrintStream out, InputStream in) {
        Messages.welcomeMessage(out);
        InteractiveConsole.handleInteractiveMode(out, new Scanner(in), menuOptions, books);
    }
}
