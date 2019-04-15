package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookInteractiveConsole {

    public static void handleInteractiveMode(PrintStream out, Scanner scan, String[] menuOptions, ArrayList<Book> books) {
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
                out.println("- Die Hard    | John McTiernan | 1998");
                out.println("- Gladiator   | Ridley Scott   | 2000");
                out.println("- The Shining | Mick Garris    | 1997");
                return;
            } else if (nextLine == 5) {
                Messages.quitMessage(out);
                return;
            }
        }

    }

    public static void handleBookBooking(PrintStream out, Scanner scan, ArrayList<Book> books) {
        Book book;
        do {
            out.println("Please type in the name of the book you want to borrow");
            book = getBookCheckedOut(out, scan, books);
        } while (book == null);
        book.setCheckedOut(true);
    }


    public static Book getBookCheckedOut(PrintStream out, Scanner scan, ArrayList<Book> books) {
        return getBookAndHandlePrintingOfMessages(out, scan, books, false,
                "Thank you! Enjoy the book", "Sorry that book is not available");
    }

    public static Integer validInputWithErrorMessage(PrintStream out, Scanner scan, String[] menuOptions) {

        Integer nextLine = Utilities.tryParse(scan.nextLine());
        if (nextLine == null || nextLine > menuOptions.length || nextLine <= 0) {
            out.println("Please select a valid option!");
            return null;
        }
        return nextLine;
    }

    public static void handleBookReturning(PrintStream out, Scanner scan, ArrayList<Book> books) {
        Book book;
        do {
            out.println("Please type in the name of the book you want to return");
            book = getBookReturned(out, scan, books);
        } while (book == null);
        book.setCheckedOut(false);
    }

    public static Book getBookReturned(PrintStream out, Scanner scan, ArrayList<Book> books) {
        return getBookAndHandlePrintingOfMessages(out, scan, books, true,
                "Thank you for returning the book", "This is not a valid book for return.");
    }

    public static Book getBookAndHandlePrintingOfMessages(PrintStream out, Scanner scan, ArrayList<Book> books, boolean bookShouldBeCheckedOut,
                                                          String successMessage, String errorMessage) {
        while (scan.hasNext()) {
            String nextLine = scan.nextLine();

            ArrayList<Book> ff = books.stream().filter(i -> i.getName().equals(nextLine) && i.isCheckedOut() == bookShouldBeCheckedOut).collect(Collectors.toCollection(ArrayList::new));
            if (ff.size() == 1) {
                out.println(successMessage);
                Book b = ff.get(0);
                return b;
            }
            out.println(errorMessage);
        }
        return null;
    }
}
