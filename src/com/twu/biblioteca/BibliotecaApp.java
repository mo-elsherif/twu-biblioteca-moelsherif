package com.twu.biblioteca;

import com.twu.biblioteca.Book.Book;
import com.twu.biblioteca.Book.BookFactory;
import com.twu.biblioteca.Console.ConsoleApplication;
import com.twu.biblioteca.Console.Menu.MainMenu;
import com.twu.biblioteca.Movie.Movie;
import com.twu.biblioteca.Movie.MovieFactory;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BibliotecaApp {

    public static ArrayList<Book> books = BookFactory.createHardCodedBooks();

    public static ArrayList<Movie> movies = MovieFactory.createHardCodedMovies();

    public static HashMap<String,CheckableItem> moviesMap = MovieFactory.createHardCodedMoviesMap();

    public static HashMap<String,CheckableItem> booksMap = BookFactory.createHardCodedBooksMap();

    public static String[] menuOptions = {"List all books", "Checkout a Book", "Return a Book", "List all movies", "Checkout a Movie", "Quit"};

    public static void main(String... args) {
        ConsoleApplication x = new ConsoleApplication(new MainMenu(),System.out,new Scanner(System.in));
        x.interactiveLoop();
        printConsoleMessages(System.out, System.in);
    }

    public static void printConsoleMessages(PrintStream out, InputStream in) {
        Messages.welcomeMessage(out);
        MainMenu.handleInteractiveMode(out, new Scanner(in), menuOptions, books,movies);
    }
}
