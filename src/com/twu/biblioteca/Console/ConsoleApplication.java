package com.twu.biblioteca.Console;

import com.twu.biblioteca.Console.Menu.MainMenu;
import com.twu.biblioteca.Console.Menu.MenuItem;
import com.twu.biblioteca.Exception.QuitApplicationException;
import com.twu.biblioteca.Messages;
import com.twu.biblioteca.Printer.PrinterFormat;
import com.twu.biblioteca.Utilities.Utilities;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleApplication {

    PrinterFormat printerFormat = new PrinterFormat();
    MenuItem[] menuItems;
    PrintStream printStream;
    Scanner scanner;

    public ConsoleApplication(MainMenu mainMenu, PrintStream printStream, Scanner scanner) {
        this.menuItems = mainMenu.getMenuItems();
        this.printStream = printStream;
        this.scanner = scanner;
    }

    public void interactiveLoop() {
        printStream.println(Messages.welcomeMessage());
        while (true) {
            Integer nextLine;
            do {
                printMenuItems();
                nextLine = getIntOrPrintErrorMessage();
            } while (nextLine == null);
            try {
                menuItems[nextLine].getMenuEntry().execute(printStream,scanner);
            } catch (QuitApplicationException e) {
                printStream.println(Messages.quitMessage());
                return;
            }
        }
    }

    public Integer getIntOrPrintErrorMessage() {
        Integer nextLine = Utilities.tryParse(scanner.nextLine());
        if (nextLine == null || nextLine > menuItems.length || nextLine < 0) {
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
