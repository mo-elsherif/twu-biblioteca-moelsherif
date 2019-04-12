package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    private static ArrayList<Book> books = BookFactory.createHardCodedBooks();

    public static String[] menuOptions = {"List all books","Quit"};

    public static void main(String... args) {
        printConsoleMessages(System.in,System.out);
    }

    public static void printConsoleMessages(InputStream in, PrintStream out) {
        System.setOut(out);
        Messages.welcomeMessage();
        Scanner scan = new Scanner(in);

        Integer nextLine;
        do {
            Printer.printAllMenus(menuOptions);
            nextLine=validInputWithErrorMessage(scan);
        } while (nextLine==null);

        if(nextLine==1){
            Printer.printAllBooks(books);
        }
        else if (nextLine == 2)
        {
            Messages.quitMessage();
        }
    }

    public static Integer validInputWithErrorMessage(Scanner scan){
        Integer nextLine=Utilities.tryParse(scan.nextLine());
        if(nextLine==null || nextLine>menuOptions.length || nextLine<=0)
        {
            System.out.println("Please select a valid option!");
            return null;
        }
        return nextLine;
    }
}
