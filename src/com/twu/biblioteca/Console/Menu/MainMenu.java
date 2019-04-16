package com.twu.biblioteca.Console.Menu;

import com.twu.biblioteca.*;
import com.twu.biblioteca.Checkable.CheckableController;
import com.twu.biblioteca.Console.Menu.MenuEntries.*;
import com.twu.biblioteca.Users.UserController;

public class MainMenu {


    private MenuItem[] menuItems;

    public MainMenu() {
        UserController controllerUsers = new UserController(BibliotecaApp.users);
        CheckableController controllerMovies = new CheckableController(BibliotecaApp.movies,controllerUsers);
        CheckableController controllerBooks = new CheckableController(BibliotecaApp.books,controllerUsers);

        MenuItem listAllBooks = new MenuItem("List all books", new ListAllBooksMenuEntry(BibliotecaApp.books));

        MenuItem checkoutABook = new MenuItem("Checkout a Book", new CheckoutACheckableItemMenuEntry(BibliotecaApp.books, controllerBooks,controllerUsers));

        MenuItem returnABook = new MenuItem("Return a Book", new ReturnACheckableItemMenuEntry(BibliotecaApp.books, controllerBooks,controllerUsers));

        MenuItem listAllMovies = new MenuItem("List all movies", new ListAllMoviesMenuEntry(BibliotecaApp.movies));

        MenuItem checkoutAMovie = new MenuItem("Checkout a Movie", new CheckoutACheckableItemMenuEntry(BibliotecaApp.movies, controllerMovies,controllerUsers));

        MenuItem login = new MenuItem("Login", new UserLoginMenuEntry(BibliotecaApp.users,controllerUsers));

        MenuItem quit = new MenuItem("Quit Application", new QuitApplicationMenuEntry());

        this.menuItems = new MenuItem[]{listAllBooks, checkoutABook, returnABook, listAllMovies, checkoutAMovie,login, quit};
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }
}
