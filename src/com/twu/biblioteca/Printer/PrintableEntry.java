package com.twu.biblioteca.Printer;

public class PrintableEntry {
    private String name;
    private int maxLength;

    public PrintableEntry(String name, int maxLength) {
        this.name = name;
        this.maxLength = maxLength;
    }

    public PrintableEntry(Integer number, int maxLength) {
        this.name = number+"";
        this.maxLength = maxLength;
    }




}
