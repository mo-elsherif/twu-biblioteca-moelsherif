package com.twu.biblioteca.Console.Menu;

import com.twu.biblioteca.CheckableItem;
import com.twu.biblioteca.Printer.PrintableEntry;
import com.twu.biblioteca.Printer.PrinterFormat;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.ToIntFunction;

public abstract class ListAllItems extends MenuEntry {

    HashMap<String, CheckableItem> checkablesMovies;
    PrinterFormat printerFormat = new PrinterFormat();

    public ListAllItems(HashMap<String, CheckableItem> checkableMap) {
        this.checkablesMovies = checkableMap;
    }

    public abstract ArrayList<ArrayList<PrintableEntry>> getAllMapEntries();

    protected static Integer computePropertyMaxLength(ArrayList list, ToIntFunction mapper) {
        return list.stream().mapToInt(mapper).max().orElse(0);
    }

    @Override
    public void execute(PrintStream printStream, Scanner scan) {
        ArrayList<ArrayList<PrintableEntry>> items = getAllMapEntries();
        for(int i=0;i<items.size();i++){
            printStream.println(printerFormat.checkableItem(items.get(i)));
        }
    }
}
