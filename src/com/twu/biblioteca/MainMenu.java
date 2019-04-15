package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static com.twu.biblioteca.BookInteractiveConsole.*;

public class MainMenu {

    public static void handleInteractiveMode(PrintStream out, Scanner scan, String[] menuOptions, ArrayList<Book> books, ArrayList<Movie> movies) {
        while (true) {
            Integer nextLine;
            do {
                Printer.printAllMenus(out, menuOptions);
                nextLine = validInputWithErrorMessage(out, scan, menuOptions);
            } while (nextLine == null);
            if (nextLine == 1) {
                Printer.printAllBooks(out, books);
            } else if (nextLine == 2) {
                handleBookBooking(out, scan, books);
            } else if (nextLine == 3) {
                handleBookReturning(out, scan, books);
            } else if (nextLine == 4) {
                Printer.printAllMovies(out,movies);
            } else if (nextLine == 5) {
                Messages.quitMessage(out);
                return;
            }
        }
    }
}
