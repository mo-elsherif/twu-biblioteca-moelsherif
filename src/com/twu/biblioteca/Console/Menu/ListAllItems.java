package com.twu.biblioteca.Console.Menu;

import com.twu.biblioteca.CheckableItem;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.ToIntFunction;

public class ListAllItems extends MenuEntry {

    HashMap<String, CheckableItem> checkablesMovies;

    public ListAllItems(HashMap<String, CheckableItem> checkableMap) {
        this.checkablesMovies = checkableMap;
    }



    protected static Integer computePropertyMaxLength(ArrayList list, ToIntFunction mapper) {
        return list.stream().mapToInt(mapper).max().orElse(0);
    }

    @Override
    public void execute(PrintStream printStream, Scanner scan) {

    }
}
