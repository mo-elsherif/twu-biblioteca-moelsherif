package com.twu.biblioteca.Console.Menu;

import com.twu.biblioteca.CheckableItem;
import com.twu.biblioteca.Movie.Movie;
import com.twu.biblioteca.Printer.PrintableEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.ToIntFunction;

public class ListAllMovies extends ListAllItems {
    public ListAllMovies(HashMap<String, CheckableItem> checkableMap) {
        super(checkableMap);
    }

    public ArrayList<ArrayList<PrintableEntry>> getAllMapEntries() {
        ArrayList<ArrayList<PrintableEntry>> result = new ArrayList<>();
        HashMap<String, CheckableItem> movies= checkablesMovies;

        int longestNameLength = computePropertyMaxLength(new ArrayList<> (movies.values()), ((ToIntFunction<? super Movie>)
                (i -> i.getName().length())));

        int longestDirectorLength = computePropertyMaxLength(new ArrayList<> (movies.values()), ((ToIntFunction<? super Movie>)
                (i -> i.getDirector().length())));

        for (CheckableItem itm : movies.values()) {
            Movie movie=(Movie) itm;
            if (movie.isCheckedOut()) {
                continue;
            }
            ArrayList<PrintableEntry> item = new ArrayList<PrintableEntry>() {
                {
                    add(new PrintableEntry(movie.getName(), longestNameLength));
                    add(new PrintableEntry(movie.getDirector(), longestDirectorLength));
                    add(new PrintableEntry(movie.getYear(), 0));
                    add(new PrintableEntry(movie.getRating(), 0));
                }
            };
            result.add(item);
        }
        return result;
    }
}
