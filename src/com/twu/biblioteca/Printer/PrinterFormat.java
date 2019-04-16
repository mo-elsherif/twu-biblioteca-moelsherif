package com.twu.biblioteca.Printer;

import com.twu.biblioteca.Console.Menu.MenuItem;

public class PrinterFormat {

    public String menuItem(int index, MenuItem menuItem)
    {
        return String.format("(%d) %s",index,menuItem.getMenuItemName());
    }
}
