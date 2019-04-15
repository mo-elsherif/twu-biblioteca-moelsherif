package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InteractiveConsole {

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
                Messages.quitMessage(out);
                return;
            }
        }
    }

    public static void handleBookBooking(PrintStream out, Scanner scan, ArrayList<Book> books) {
        Book book;
        do {
            out.println("Please type in the name of the book you want to borrow");
            book = getBookWithErrorMessage(out, scan, books);
        } while (book == null);
        book.setCheckedOut(true);
    }


    public static Book getBookWithErrorMessage(PrintStream out, Scanner scan, ArrayList<Book> books) {
       // Scanner scan= new Scanner(in);
        while (scan.hasNext()) {
            String  nextLine = scan.nextLine();
            ArrayList<Book> ff = books.stream().filter(i -> i.getName().equals(nextLine) && !i.isCheckedOut()).collect(Collectors.toCollection(ArrayList::new));
            if (ff.size() == 1) {
                out.println("Thank you! Enjoy the book");
                Book b = ff.get(0);
                return b;
            }
            out.println("Sorry that book is not available");
        }
        return null;
    }

    public static Integer validInputWithErrorMessage(PrintStream out, Scanner scan, String[] menuOptions) {
        //Scanner scan = new Scanner(in);
        Integer nextLine = Utilities.tryParse(scan.nextLine());
        if (nextLine == null || nextLine > menuOptions.length || nextLine <= 0) {
            out.println("Please select a valid option!");
            return null;
        }
        return nextLine;
    }
}
