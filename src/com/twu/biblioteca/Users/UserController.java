package com.twu.biblioteca.Users;

import com.twu.biblioteca.Console.Menu.MainMenu;

import java.util.HashMap;

public class UserController {
    private User currentSignedInUser;
    private HashMap<String, User> users;
    private MainMenu mainMenu;

    public UserController(HashMap<String, User> users, MainMenu mainMenu) {
        this.users = users;
        this.mainMenu = mainMenu;
    }

    public boolean logIn(String username,String password)
    {
        if(users.containsKey(username) && users.get(username).getPassword().equals(password)){
            currentSignedInUser=  users.get(username);
            mainMenu.activateUserInfo();
            return true;
        }
        return false;
    }

    public void logOut()
    {
        currentSignedInUser=null;
        mainMenu.deactivateUserInfo();
    }

    public User getCurrentSignedInUser() {
        return currentSignedInUser;
    }
}
