package com.twu.biblioteca.Console.Menu;

import java.io.PrintStream;
import java.util.Scanner;

public abstract class MenuEntry {

    public abstract void execute(PrintStream printStream, Scanner scan);
}
