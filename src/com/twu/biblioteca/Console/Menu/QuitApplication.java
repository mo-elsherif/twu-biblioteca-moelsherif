package com.twu.biblioteca.Console.Menu;

import com.twu.biblioteca.Exception.QuitApplicationException;

import java.io.PrintStream;
import java.util.Scanner;

public class QuitApplication extends MenuEntry {

    @Override
    public void execute(PrintStream printStream, Scanner scan) throws QuitApplicationException {

        throw new QuitApplicationException();

    }
}
