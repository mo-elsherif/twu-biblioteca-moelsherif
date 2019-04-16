package com.twu.biblioteca.Console;

import com.twu.biblioteca.Console.Menu.MainMenu;
import com.twu.biblioteca.Console.Menu.MenuItem;
import com.twu.biblioteca.Printer.PrinterFormat;
import com.twu.biblioteca.PrinterClass;
import com.twu.biblioteca.Utilities;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleApplication {

    PrinterFormat printerFormat = new PrinterFormat();
    MenuItem[] menuItems;
    PrintStream printStream;
    Scanner scanner;

    public ConsoleApplication(MainMenu mainMenu, PrintStream out, Scanner scanner) {
        this.menuItems = mainMenu.getMenuItems();
        this.printStream = printStream;
        this.scanner = scanner;
    }

    public void interactiveLoop() {
        while (true) {
            Integer nextLine;
            do {
                printMenuItems();
                nextLine = getIntOrPrintErrorMessage();
            } while (nextLine == null);
            menuItems[nextLine].getMenuEntry().execute(printStream,scanner);
        }
    }

    public Integer getIntOrPrintErrorMessage() {
        Integer nextLine = Utilities.tryParse(scanner.nextLine());
        if (nextLine == null || nextLine > menuItems.length || nextLine <= 0) {
            printStream.println("Please select a valid option!");
            return null;
        }
        return nextLine;
    }


    public void printMenuItems() {
        for (int i = 0; i < menuItems.length; i++) {
            printStream.println(printerFormat.menuItem(i, menuItems[i]));
        }
    }

}
