package com.twu.biblioteca.Users;

public class User {
    private String userId;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;

    public User(String userId, String password, String name, String email, String phoneNumber) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
