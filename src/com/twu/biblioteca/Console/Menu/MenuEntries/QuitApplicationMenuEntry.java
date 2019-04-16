package com.twu.biblioteca.Console.Menu.MenuEntries;

import com.twu.biblioteca.Console.Menu.MenuEntries.MenuEntry;
import com.twu.biblioteca.Exception.QuitApplicationException;

import java.io.PrintStream;
import java.util.Scanner;

public class QuitApplicationMenuEntry extends MenuEntry {

    @Override
    public void execute(PrintStream printStream, Scanner scan) throws QuitApplicationException {
        throw new QuitApplicationException();
    }
}
