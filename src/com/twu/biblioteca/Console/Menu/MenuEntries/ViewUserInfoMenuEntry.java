package com.twu.biblioteca.Console.Menu.MenuEntries;

import com.twu.biblioteca.Users.User;
import com.twu.biblioteca.Users.UserController;

import java.io.PrintStream;
import java.util.Scanner;

public class ViewUserInfoMenuEntry extends MenuEntry {
    private final UserController controllerUsers;

    public ViewUserInfoMenuEntry(UserController controllerUsers) {

        this.controllerUsers = controllerUsers;
    }

    @Override
    public void execute(PrintStream printStream, Scanner scan) {
        printStream.println(getUserInfo());
    }

    public String getUserInfo() {
        User u = controllerUsers.getCurrentSignedInUser();
        return u == null ? null : String.format("Name: %s , Email %s , Phone %s",
                u.getName(), u.getEmail(), u.getPhoneNumber());
    }

}
