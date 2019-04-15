package com.twu.biblioteca;

public abstract class CheckableItem {

    String name;
    int year;
    boolean checkedOut;

    public CheckableItem(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public abstract String getItemName();
}
