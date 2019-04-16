package com.twu.biblioteca.Console.Menu.MenuEntries;

import com.twu.biblioteca.Checkable.CheckableItem;

import com.twu.biblioteca.Movie.Movie;
import com.twu.biblioteca.Printer.PrintableEntry;
import com.twu.biblioteca.Utilities.IntTuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.ToIntFunction;

public class ListAllMoviesMenuEntry extends ListAllItemsMenuEntry {
    public ListAllMoviesMenuEntry(HashMap<String, CheckableItem> checkableMap) {
        super(checkableMap);
    }

    @Override
    public ArrayList<PrintableEntry> getBookPrintableEntries(CheckableItem item) {
        Movie movie = (Movie) item;
        IntTuple nameAndDirectorMaxLength = getMoviesNameAndDirectMaxLength();
        return new ArrayList<PrintableEntry>() {
            {
                add(new PrintableEntry(movie.getName(), nameAndDirectorMaxLength.x));
                add(new PrintableEntry(movie.getDirector(), nameAndDirectorMaxLength.y));
                add(new PrintableEntry(movie.getYear(), 0));
                add(new PrintableEntry(movie.getRating(), 0));
            }
        };
    }

    public IntTuple getMoviesNameAndDirectMaxLength() {
        int longestNameLength = computePropertyMaxLength(((ToIntFunction<? super Movie>)
                (i -> i.getName().length())));

        int longestDirectorLength = computePropertyMaxLength(((ToIntFunction<? super Movie>)
                (i -> i.getDirector().length())));
        return new IntTuple(longestNameLength, longestDirectorLength);
    }
}
