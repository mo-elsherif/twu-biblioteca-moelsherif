package com.twu.biblioteca.Console.Menu;

import com.twu.biblioteca.Exception.QuitApplicationException;

import java.io.PrintStream;
import java.util.Scanner;

public abstract class MenuEntry {

    public abstract void execute(PrintStream printStream, Scanner scan) throws QuitApplicationException;
}
