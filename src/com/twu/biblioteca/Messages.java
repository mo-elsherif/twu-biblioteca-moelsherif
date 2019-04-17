package com.twu.biblioteca;

import java.io.PrintStream;

public class Messages {

    public static String welcomeMessage() {
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore";
    }

    public static String quitMessage() {
        return "Thank you for using our Application.";
    }

    public static String userShouldSignInMessage() {
        return "User should sign in before checking out a book";
    }

    public static String notValidForReturnMessage(String itemName){
        return "This is not a valid " + itemName + " for return.";
    }
}
