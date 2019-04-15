package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.ToIntFunction;

public class Printer {

    public static void printAllBooks(PrintStream out, ArrayList<Book> books) {
        int longestNameLength = computePropertyMaxLength(books, (ToIntFunction<? super Book>)
                (i -> i.getName().length()));
        int longestAuthorLength = computePropertyMaxLength(books, (ToIntFunction<? super Book>)
                (i -> i.getAuthor().length()));
        for (Book book : books) {
            if (book.isCheckedOut()) {
                continue;
            }
            int spacesNecessaryAfterName = longestNameLength - book.getName().length();
            int spacesNecessaryAfterAuthor = longestAuthorLength - book.getAuthor().length();
            out.println(String.format("- %s%s | %s%s | %d", book.getName(),
                    spaces(spacesNecessaryAfterName), book.getAuthor(), spaces(spacesNecessaryAfterAuthor)
                    , book.getPublicationYear()));
        }
    }

    public static void printAllMovies(PrintStream out, ArrayList<Movie> movies) {
        int longestNameLength = computePropertyMaxLength(movies, ((ToIntFunction<? super Movie>)
                (i -> i.getName().length())));
        int longestDirectorLength = computePropertyMaxLength(movies, ((ToIntFunction<? super Movie>)
                (i -> i.getDirector().length())));
        for (Movie movie : movies) {
           if (movie.isCheckout()) {
                continue;
            }
            int spacesNecessaryAfterName = longestNameLength - movie.getName().length();
            int spacesNecessaryAfterDirector = longestDirectorLength - movie.getDirector().length();
            out.println(String.format("- %s%s | %s%s | %d | %d", movie.getName(),
                    spaces(spacesNecessaryAfterName), movie.getDirector(), spaces(spacesNecessaryAfterDirector)
                    , movie.getYear(), movie.getRating()));
        }
    }

    private static Integer computePropertyMaxLength(ArrayList list, ToIntFunction mapper) {
        return list.stream().mapToInt(mapper).max().orElse(0);
    }

    private static String spaces(int n) {
        return String.join("", Collections.nCopies(n, " "));
    }

    public static void printAllMenus(PrintStream out, String[] menuOptions) {
        for (int i = 0; i < menuOptions.length; i++) {
            out.println(String.format("(%d) %s ", i + 1, menuOptions[i]));
        }
    }
}
