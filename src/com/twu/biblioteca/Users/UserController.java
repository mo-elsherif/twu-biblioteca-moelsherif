package com.twu.biblioteca.Users;

import java.util.HashMap;

public class UserController {
    private User currentSignedInUser;
    private HashMap<String, User> users;


    public UserController(HashMap<String, User> users) {
        this.users = users;
    }

    public boolean logIn(String username,String password)
    {
        if(users.containsKey(username) && users.get(username).getPassword().equals(password)){
            currentSignedInUser=  users.get(username);
            return true;
        }
        return false;
    }

    public void logOut()
    {
        currentSignedInUser=null;
    }

    public User getCurrentSignedInUser() {
        return currentSignedInUser;
    }
}
