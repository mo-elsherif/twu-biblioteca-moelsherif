package com.twu.biblioteca.Console.Menu;

import com.twu.biblioteca.*;
import com.twu.biblioteca.Checkable.CheckableController;
import com.twu.biblioteca.Console.Menu.MenuEntries.*;

public class MainMenu {


    private MenuItem[] menuItems;

    public MainMenu() {
        CheckableController controllerMovies = new CheckableController(BibliotecaApp.movies);
        CheckableController controllerBooks = new CheckableController(BibliotecaApp.books);
        MenuItem listAllBooks = new MenuItem("List all books", new ListAllBooksMenuEntry(BibliotecaApp.books));

        MenuItem checkoutABook = new MenuItem("Checkout a Book", new CheckoutACheckableItemMenuEntry(BibliotecaApp.books, controllerBooks));

        MenuItem returnABook = new MenuItem("Return a Book", new ReturnACheckableItemMenuEntry(BibliotecaApp.books, controllerBooks));

        MenuItem listAllMovies = new MenuItem("List all movies", new ListAllMoviesMenuEntry(BibliotecaApp.movies));

        MenuItem checkoutAMovie = new MenuItem("Checkout a Movie", new CheckoutACheckableItemMenuEntry(BibliotecaApp.movies, controllerMovies));

        MenuItem quit = new MenuItem("Quit Application", new QuitApplicationMenuEntry());

        this.menuItems = new MenuItem[]{listAllBooks, checkoutABook, returnABook, listAllMovies, checkoutAMovie, quit};
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }
}
