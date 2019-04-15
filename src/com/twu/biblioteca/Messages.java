package com.twu.biblioteca;

import java.io.PrintStream;

public class Messages {

    public static void welcomeMessage(PrintStream out) {
        out.println( "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore");
    }

    public static void quitMessage(PrintStream out) {
        out.println("Thank you for using our Application.");
    }
}
