package com.twu.biblioteca.Console.Menu.MenuEntries;

import com.twu.biblioteca.Checkable.CheckableItem;
import com.twu.biblioteca.Printer.PrintableEntry;
import com.twu.biblioteca.Printer.PrinterFormat;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.ToIntFunction;

public abstract class ListAllItemsMenuEntry extends MenuEntry {

    HashMap<String, CheckableItem> checkables;
    PrinterFormat printerFormat = new PrinterFormat();

    public ListAllItemsMenuEntry(HashMap<String, CheckableItem> checkableMap) {
        this.checkables = checkableMap;
    }

    public abstract ArrayList<PrintableEntry> getBookPrintableEntries(CheckableItem item);

    protected Integer computePropertyMaxLength(ToIntFunction mapper) {
        return new ArrayList<>(checkables.values()).stream().mapToInt(mapper).max().orElse(0);
    }

    public ArrayList<ArrayList<PrintableEntry>> getAllMapEntries() {
        ArrayList<ArrayList<PrintableEntry>> result = new ArrayList<>();
        for (CheckableItem itm : checkables.values()) {
            if (itm.isCheckedOut()) {
                continue;
            }
            ArrayList<PrintableEntry> item = getBookPrintableEntries(itm);
            result.add(item);
        }
        return result;
    }

    @Override
    public void execute(PrintStream printStream, Scanner scan) {
        ArrayList<ArrayList<PrintableEntry>> items = getAllMapEntries();
        for(int i=0;i<items.size();i++){
            printStream.println(printerFormat.checkableItem(items.get(i)));
        }
    }
}
