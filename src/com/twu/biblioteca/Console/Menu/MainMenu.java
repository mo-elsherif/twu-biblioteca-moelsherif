package com.twu.biblioteca.Console.Menu;

import com.twu.biblioteca.*;
import com.twu.biblioteca.Book.Book;
import com.twu.biblioteca.Movie.Movie;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static com.twu.biblioteca.CheckableItemInteractiveConsole.handleBookReturning;
import static com.twu.biblioteca.CheckableItemInteractiveConsole.handleCheckableItemBooking;

public class MainMenu {

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    private MenuItem[] menuItems;

    public MainMenu() {
        CheckableController controllerMovies = new CheckableController(BibliotecaApp.moviesMap);
        CheckableController controllerBooks = new CheckableController(BibliotecaApp.booksMap);
        MenuItem listAllBooks = new MenuItem("List all books", new ListAllBooks(BibliotecaApp.booksMap));

        MenuItem checkoutABook = new MenuItem("Checkout a Book", new CheckoutACheckableItem(BibliotecaApp.booksMap, controllerBooks));

        MenuItem returnABook = new MenuItem("Return a Book", new ReturnACheckableItem(BibliotecaApp.booksMap, controllerBooks));

        MenuItem listAllMovies = new MenuItem("List all movies", new ListAllMovies(BibliotecaApp.moviesMap));

        MenuItem checkoutAMovie = new MenuItem("Checkout a Movie", new CheckoutACheckableItem(BibliotecaApp.moviesMap, controllerMovies));

        MenuItem quit = new MenuItem("Quit Application", new QuitApplication());

        this.menuItems = new MenuItem[]{listAllBooks, checkoutABook, returnABook, listAllMovies, checkoutAMovie, quit};
    }


    public static void handleInteractiveMode(PrintStream out, Scanner scan, String[] menuOptions, ArrayList<Book> checkablesBooks,
                                             ArrayList<? extends CheckableItem> checkablesMovies) {


        while (true) {
            Integer nextLine;
            do {
                PrinterClass.printAllMenus(out, menuOptions);
                nextLine = validInputWithErrorMessage(out, scan, menuOptions);
            } while (nextLine == null);
            if (nextLine == 1) {
                PrinterClass.printAllBooks(out, checkablesBooks);
            } else if (nextLine == 2) {
                handleCheckableItemBooking(out, scan, checkablesBooks);
            } else if (nextLine == 3) {
                handleBookReturning(out, scan, checkablesBooks);
            } else if (nextLine == 4) {
                PrinterClass.printAllMovies(out, (ArrayList<Movie>) checkablesMovies);
            } else if (nextLine == 5) {
                handleCheckableItemBooking(out, scan, checkablesMovies);
            } else if (nextLine == 6) {
                Messages.quitMessage(out);
                return;
            }
        }
    }

    public static Integer validInputWithErrorMessage(PrintStream out, Scanner scan, String[] menuOptions) {

        Integer nextLine = Utilities.tryParse(scan.nextLine());
        if (nextLine == null || nextLine > menuOptions.length || nextLine <= 0) {
            out.println("Please select a valid option!");
            return null;
        }
        return nextLine;
    }
}
