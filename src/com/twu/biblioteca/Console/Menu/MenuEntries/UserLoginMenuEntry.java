package com.twu.biblioteca.Console.Menu.MenuEntries;

import com.twu.biblioteca.Users.User;
import com.twu.biblioteca.Users.UserController;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class UserLoginMenuEntry extends MenuEntry {

    HashMap<String, User> users;
    UserController controllerUsers;

    public UserLoginMenuEntry(HashMap<String, User> users, UserController controllerUsers) {
        this.users = users;
        this.controllerUsers = controllerUsers;
    }

    public boolean execute(PrintStream printStream, String username, String password) {
        boolean loginSucceeded = controllerUsers.logIn(username, password);
        if(loginSucceeded)
        {
            printStream.println("You have successfully signed in");
            return true;
        }
        else
        {
            printStream.println("This user data is not valid. Please type your info again.");
            return false;
        }
    }

    @Override
    public void execute(PrintStream printStream, Scanner scan) {
        printStream.println("Please type in the username and then the password");
        boolean returnVal = false;
        String username, password;
        while (!returnVal) {
            username = scan.nextLine();
            password = scan.nextLine();
            returnVal = execute(printStream, username, password);
        }
    }
}
