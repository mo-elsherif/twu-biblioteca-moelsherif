package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MovieInteractiveConsole {
    public static void handleMovieBooking(PrintStream printStream, Scanner scanner, ArrayList<Movie> movies) {
        Movie movie;
        do {
            printStream.println("Please type in the name of the movie you want to borrow");
            movie = getCheckedOutMovie(printStream, scanner, movies);
        } while (movie == null);
        movie.setCheckedOut(true);
    }

    private static Movie getCheckedOutMovie(PrintStream printStream, Scanner scanner, ArrayList<Movie> movies) {
        return getBookAndHandlePrintingOfMessages(printStream, scanner, movies, false,
                "Thank you! Enjoy the movie", "Sorry that book is not available");
    }

    public static Movie getBookAndHandlePrintingOfMessages(PrintStream out, Scanner scan, ArrayList<Movie> movies, boolean bookShouldBeCheckedOut,
                                                           String successMessage, String errorMessage) {
        while (scan.hasNext()) {
            String nextLine = scan.nextLine();

            ArrayList<Movie> ff = movies.stream().filter(i -> i.getName().equals(nextLine) && i.isCheckedOut() == bookShouldBeCheckedOut).collect(Collectors.toCollection(ArrayList::new));
            if (ff.size() == 1) {
                out.println(successMessage);
                Movie b = ff.get(0);
                return b;
            }
            out.println(errorMessage);
        }
        return null;
    }
}
