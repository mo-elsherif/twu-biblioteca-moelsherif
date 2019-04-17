package com.twu.biblioteca.Console.Menu;

import com.twu.biblioteca.*;
import com.twu.biblioteca.Checkable.CheckableController;
import com.twu.biblioteca.Console.Menu.MenuEntries.*;
import com.twu.biblioteca.Users.UserController;

import java.util.ArrayList;

public class MainMenu {


    private ArrayList<MenuItem> menuItems=new ArrayList<>();

    private MenuItem userInfo;

    public MainMenu() {
        UserController controllerUsers = new UserController(BibliotecaApp.users,this);
        CheckableController controllerMovies = new CheckableController(BibliotecaApp.movies,controllerUsers);
        CheckableController controllerBooks = new CheckableController(BibliotecaApp.books,controllerUsers);

        menuItems.add(new MenuItem("List all books", new ListAllBooksMenuEntry(BibliotecaApp.books)));

        menuItems.add(new MenuItem("Checkout a Book", new CheckoutACheckableItemMenuEntry(BibliotecaApp.books, controllerBooks,controllerUsers)));

        menuItems.add(new MenuItem("Return a Book", new ReturnACheckableItemMenuEntry(BibliotecaApp.books, controllerBooks,controllerUsers)));

        menuItems.add(new MenuItem("List all movies", new ListAllMoviesMenuEntry(BibliotecaApp.movies)));

        menuItems.add(new MenuItem("Checkout a Movie", new CheckoutACheckableItemMenuEntry(BibliotecaApp.movies, controllerMovies,controllerUsers)));

        menuItems.add(new MenuItem("Login", new UserLoginMenuEntry(BibliotecaApp.users,controllerUsers)));

        userInfo=new MenuItem("User Info", new ViewUserInfoMenuEntry(controllerUsers),false);
        menuItems.add(userInfo);

        menuItems.add(new MenuItem("Quit Application", new QuitApplicationMenuEntry()));

    }

    public void activateUserInfo(){
        userInfo.setActive(true);
    }

    public void deactivateUserInfo(){
        userInfo.setActive(false);
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public ArrayList<MenuItem> getActiveMenuItems() {
        ArrayList<MenuItem> result=((ArrayList<MenuItem>)menuItems.clone());
        result.removeIf(s -> !s.isActive());
        return result;
    }
}
