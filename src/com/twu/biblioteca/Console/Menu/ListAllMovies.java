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

    public ArrayList<ArrayList<PrintableEntry>> getMoviePrintableEntries() {
        ArrayList<ArrayList<PrintableEntry>> result = new ArrayList<>();
        ArrayList<Movie> movies = (ArrayList) checkablesMovies.values();

        int longestNameLength = computePropertyMaxLength(movies, ((ToIntFunction<? super Movie>)
                (i -> i.getName().length())));

        int longestDirectorLength = computePropertyMaxLength(movies, ((ToIntFunction<? super Movie>)
                (i -> i.getDirector().length())));

        for (Movie movie : movies) {
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
