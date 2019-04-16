package com.twu.biblioteca;

import com.twu.biblioteca.Book.BookFactory;
import com.twu.biblioteca.Checkable.CheckableItem;
import com.twu.biblioteca.Console.ConsoleApplication;
import com.twu.biblioteca.Console.Menu.MainMenu;
import com.twu.biblioteca.Movie.MovieFactory;
import com.twu.biblioteca.Users.User;
import com.twu.biblioteca.Users.UserFactory;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class BibliotecaApp {

    public static HashMap<String, CheckableItem> movies = MovieFactory.createHardCodedMoviesMap();
    public static HashMap<String, CheckableItem> books = BookFactory.createHardCodedBooksMap();
    public static HashMap<String, User> users = UserFactory.createHardCodedUsers();

    public static void main(String... args) {
        startApp(new MainMenu(), System.out, new Scanner(System.in));
    }

    public static void startApp(MainMenu mainMenu, PrintStream printStream, Scanner scanner)
    {
        ConsoleApplication x = new ConsoleApplication(mainMenu, printStream, scanner);
        x.interactiveLoop();
    }
}
