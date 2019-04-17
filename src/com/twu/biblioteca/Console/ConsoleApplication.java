package com.twu.biblioteca.Console;

import com.twu.biblioteca.Console.Menu.MainMenu;
import com.twu.biblioteca.Exception.QuitApplicationException;
import com.twu.biblioteca.Messages;
import com.twu.biblioteca.Printer.PrinterFormat;
import com.twu.biblioteca.Utilities.Utilities;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleApplication {

    PrinterFormat printerFormat = new PrinterFormat();
    MainMenu mainMenu;
    PrintStream printStream;
    Scanner scanner;

    public ConsoleApplication(MainMenu mainMenu, PrintStream printStream, Scanner scanner) {
        this.mainMenu = mainMenu;
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
                mainMenu.getActiveMenuItems().get(nextLine).getMenuEntry().execute(printStream,scanner);
            } catch (QuitApplicationException e) {
                printStream.println(Messages.quitMessage());
                return;
            }
        }
    }

    public Integer getIntOrPrintErrorMessage() {
        Integer nextLine = Utilities.tryParse(scanner.nextLine());
        if (nextLine == null || nextLine > mainMenu.getActiveMenuItems().size() || nextLine < 0) {
            printStream.println("Please select a valid option!");
            return null;
        }
        return nextLine;
    }


    public void printMenuItems() {
        for (int i = 0; i < mainMenu.getActiveMenuItems().size(); i++) {
            printStream.println(printerFormat.menuItem(i, mainMenu.getActiveMenuItems().get(i)));
        }
    }
}
