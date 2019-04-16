package com.twu.biblioteca.Checkable;

import com.twu.biblioteca.Users.User;

public abstract class CheckableItem {

    String name;
    int year;
    private User userThatCheckedTheItemOut;

    public CheckableItem(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public User getUser(){
        return userThatCheckedTheItemOut;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public boolean isCheckedOut() {
        return userThatCheckedTheItemOut!=null;
    }

    public void setCheckedOutUser(User userThatCheckedTheItemOut) {
        this.userThatCheckedTheItemOut = userThatCheckedTheItemOut;
    }

    public abstract String getItemName();
}
