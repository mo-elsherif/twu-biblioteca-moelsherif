package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static com.twu.biblioteca.CheckableItemInteractiveConsole.handleBookReturning;
import static com.twu.biblioteca.CheckableItemInteractiveConsole.handleCheckableItemBooking;

public class MainMenu {

    public static void handleInteractiveMode(PrintStream out, Scanner scan, String[] menuOptions, ArrayList<Book> checkablesBooks,
                                             ArrayList<? extends CheckableItem> checkablesMovies) {
        while (true) {
            Integer nextLine;
            do {
                Printer.printAllMenus(out, menuOptions);
                nextLine = validInputWithErrorMessage(out, scan, menuOptions);
            } while (nextLine == null);
            if (nextLine == 1) {
                Printer.printAllBooks(out, checkablesBooks);
            } else if (nextLine == 2) {
                handleCheckableItemBooking(out,scan,  checkablesBooks);
            } else if (nextLine == 3) {
                handleBookReturning(out, scan, checkablesBooks);
            } else if (nextLine == 4) {
                Printer.printAllMovies(out, (ArrayList<Movie>) checkablesMovies);
            } else if (nextLine == 5) {
                handleCheckableItemBooking(out,scan,  checkablesMovies);
            } else if (nextLine == 6) {
                Messages.quitMessage(out);
                return;
            }
        }
    }

    public static Integer validInputWithErrorMessage(PrintStream out, Scanner scan, String[] menuOptions) {

        Integer nextLine = Utilities.tryParse(scan.nextLine());
        if (nextLine == null || nextLine > menuOptions.length || nextLine <= 0) {
            out.println("Please select a valid option!");
            return null;
        }
        return nextLine;
    }
}
