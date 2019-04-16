package com.twu.biblioteca.Printer;

import com.twu.biblioteca.Console.Menu.MenuItem;

import java.util.ArrayList;
import java.util.Collections;

public class PrinterFormat {

    public String menuItem(int index, MenuItem menuItem)
    {
        return String.format("(%d) %s",index,menuItem.getMenuItemName());
    }

    public String checkableItem(ArrayList<PrintableEntry> printableEntries) {
        String result="";
        for(PrintableEntry entry : printableEntries){
            int spacesNecessaryAfterName = entry.getMaxLength() - entry.getName().length();
            spacesNecessaryAfterName=Math.max(spacesNecessaryAfterName,0);
            result +=" | " +entry.getName() +spaces(spacesNecessaryAfterName);
        }
        return result;
    }

    private static String spaces(int n) {
        return String.join("", Collections.nCopies(n, " "));
    }
}
