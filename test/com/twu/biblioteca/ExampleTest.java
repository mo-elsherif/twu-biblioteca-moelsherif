package com.twu.biblioteca;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.twu.biblioteca.Book.Book;
import com.twu.biblioteca.Checkable.CheckableController;
import com.twu.biblioteca.Checkable.CheckableItem;

import com.twu.biblioteca.Console.ConsoleApplication;
import com.twu.biblioteca.Console.Menu.MainMenu;
import com.twu.biblioteca.Console.Menu.MenuEntries.*;
import com.twu.biblioteca.Exception.InvalidUserOperationException;
import com.twu.biblioteca.Exception.ItemAlreadyCheckedOutException;
import com.twu.biblioteca.Exception.ItemAlreadyReturnedException;
import com.twu.biblioteca.Movie.Movie;
import com.twu.biblioteca.Printer.PrintableEntry;
import com.twu.biblioteca.Printer.PrinterFormat;
import com.twu.biblioteca.Users.User;
import com.twu.biblioteca.Users.UserController;
import com.twu.biblioteca.Utilities.Utilities;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.twu.biblioteca.BibliotecaApp.startApp;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ExampleTest {

    private static User defaultUser;
    private static Book defaultBook;
    private static Movie defaultMovie;
    private static HashMap<String, CheckableItem> movies;
    private static HashMap<String, CheckableItem> books;
    private static CheckableController controllerMovies;
    private static CheckableController controllerBooks;
    private static UserController controllerUsers;
    private static ByteArrayOutputStream bytes;
    private static PrintStream printStream;
    private static HashMap<String, User> users;

    @BeforeClass
    public static void setUp() {
        books = BibliotecaApp.books;
        movies = BibliotecaApp.movies;
        users = BibliotecaApp.users;
        controllerUsers = new UserController(users);
        controllerMovies = new CheckableController(movies, controllerUsers);
        controllerBooks = new CheckableController(books, controllerUsers);

        defaultBook = (Book) Utilities.getRandomHashMapElement(books);
        defaultMovie = (Movie) Utilities.getRandomHashMapElement(movies);
        defaultUser = (User) Utilities.getRandomHashMapElement(users);
    }

    @Before
    public void beforeTest() {
        bytes = new ByteArrayOutputStream();
        printStream = new PrintStream(bytes);
    }

    @After
    public void tearDown() {
        defaultBook.setCheckedOutUser(null);
        defaultMovie.setCheckedOutUser(null);
        logout();
    }

    public void login() {
        controllerUsers.logIn(defaultUser.getUserId(), defaultUser.getPassword());
    }

    private void logout() {
        controllerUsers.logOut();
    }


    @Test
    public void checkableController() throws ItemAlreadyCheckedOutException, InvalidUserOperationException {
        login();
        CheckableController controller = new CheckableController(movies, controllerUsers);
        controller.checkOutAnItem(defaultMovie.getName(), defaultUser);
        assertThat(defaultMovie.isCheckedOut(), is(equalTo(true)));
    }

    @Test(expected = ItemAlreadyCheckedOutException.class)
    public void cantCheckOutAlreadyCheckedOutItem() throws ItemAlreadyCheckedOutException, InvalidUserOperationException {
        login();
        defaultMovie.setCheckedOutUser(defaultUser);
        CheckableController controller = new CheckableController(movies, controllerUsers);
        controller.checkOutAnItem(defaultMovie.getName(), defaultUser);
    }

    @Test
    public void canReturnAlreadyCheckedOutItem() throws ItemAlreadyReturnedException, ItemAlreadyCheckedOutException, InvalidUserOperationException {
        login();
        CheckableController controller = new CheckableController(movies, controllerUsers);
        controller.checkOutAnItem(defaultMovie.getName(), defaultUser);
        controller.returnAnItem(defaultMovie.getName(), defaultUser);
    }

    @Test(expected = ItemAlreadyReturnedException.class)
    public void cantReturnAlreadyReturnedItem() throws ItemAlreadyReturnedException, InvalidUserOperationException {
        login();
        CheckableController controller = new CheckableController(movies, controllerUsers);
        controller.returnAnItem(defaultMovie.getName(), defaultUser);
    }

    @Test
    public void getListOfAllItemsAndCheckOutAnItemAndMakeSureItsRemovedFromTheList() throws ItemAlreadyCheckedOutException, InvalidUserOperationException {
        login();
        CheckableController controller = new CheckableController(movies, controllerUsers);
        ArrayList<CheckableItem> items = controller.getListOfCheckableItems();
        controller.checkOutAnItem(defaultMovie.getName(), defaultUser);
        ArrayList<CheckableItem> itemsAfterCheckOut = controller.getListOfCheckableItems();
        items.removeAll(itemsAfterCheckOut);
        assertThat(items.size(), is(equalTo(1)));
        assertThat(items.get(0).getName(), is(equalTo(defaultMovie.getName())));
    }


    @Test
    public void testWelcomeMessageAndQuitApplication() {
        MainMenu menu = new MainMenu();
        int quitIndex = menu.getMenuItems().length - 1; //should be the last one
        InputStream stream = new ByteArrayInputStream((quitIndex + "").getBytes());
        startApp(menu, printStream, new Scanner(stream));
        String[] strArr = bytes.toString().split("\n");
        assertThat(strArr[0], is(equalTo("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore")));
        assertThat(strArr[strArr.length - 1], is(equalTo("Thank you for using our Application.")));
    }

    @Test
    public void testMainMenuOptionsListInvalidState() {
        ArrayList<String> errorCases = new ArrayList<String>() {
            {
                add("99");
                add(" ");
                add("0at");
                add("a");
            }
        };

        for (String errorCase : errorCases) {
            InputStream stream = new ByteArrayInputStream(errorCase.getBytes());
            ConsoleApplication application = new ConsoleApplication(new MainMenu(), printStream, new Scanner(stream));
            Integer integer = application.getIntOrPrintErrorMessage();
            String[] strArr = bytes.toString().split("\n");
            assertThat(integer, is(equalTo(null)));
            assertThat(strArr[0], is(equalTo("Please select a valid option!")));
        }
    }

    @Test
    public void checkOutABook() {
        login();
        CheckoutACheckableItemMenuEntry menuEntry = new CheckoutACheckableItemMenuEntry(books, controllerBooks, controllerUsers);
        menuEntry.execute(printStream, defaultBook.getName());
        String[] strArr = bytes.toString().split("\n");
        assertThat(strArr[0], is(equalTo(
                "Thank you! Enjoy the book")));
    }

    @Test
    public void checkOutAMovie() {
        login();
        CheckoutACheckableItemMenuEntry menuEntry = new CheckoutACheckableItemMenuEntry(movies, controllerMovies, controllerUsers);
        menuEntry.execute(printStream, defaultMovie.getName());
        String[] strArr = bytes.toString().split("\n");
        assertThat(strArr[0], is(equalTo(
                "Thank you! Enjoy the movie")));
    }

    @Test
    public void checkOutAnInvalidBook() {
        CheckoutACheckableItemMenuEntry menuEntry = new CheckoutACheckableItemMenuEntry(books, controllerBooks, controllerUsers);
        menuEntry.execute(printStream, "XXX");
        String[] strArr = bytes.toString().split("\n");
        assertThat(strArr[0], is(equalTo(
                "Sorry that book is not available")));
    }

    @Test
    public void returnAValidBook() {
        login();
        defaultBook.setCheckedOutUser(defaultUser);
        ReturnACheckableItemMenuEntry menuEntry = new ReturnACheckableItemMenuEntry(books, controllerBooks, controllerUsers);
        menuEntry.execute(printStream, defaultBook.getName());
        String[] strArr = bytes.toString().split("\n");
        assertThat(strArr[0], is(equalTo(
                "Thank you for returning the book")));
    }

    @Test
    public void returnAnInValidBook() {
        ReturnACheckableItemMenuEntry menuEntry = new ReturnACheckableItemMenuEntry(books, controllerBooks, controllerUsers);
        menuEntry.execute(printStream, "XXX");
        String[] strArr = bytes.toString().split("\n");
        assertThat(strArr[0], is(equalTo(
                "This is not a valid book for return.")));
    }

    @Test
    public void checkedOutBooksDoNotAppearInListOfAllBooks() {
        login();
        ListAllBooksMenuEntry listAllBooksMenuEntry = new ListAllBooksMenuEntry(books);
        ArrayList<ArrayList<PrintableEntry>> allEntries = listAllBooksMenuEntry.getAllMapEntries();
        defaultBook.setCheckedOutUser(defaultUser);
        ArrayList<ArrayList<PrintableEntry>> allEntriesAfterCheckOut = listAllBooksMenuEntry.getAllMapEntries();
        assertThat(allEntriesAfterCheckOut.size(), is(equalTo(allEntries.size() - 1)));
    }

    @Test
    public void checkBookPrintContainsEssentialInfo() {
        ListAllBooksMenuEntry menuEntry = new ListAllBooksMenuEntry(books);
        PrinterFormat x = new PrinterFormat();
        String elmInfo = x.checkableItem(menuEntry.getBookPrintableEntries(defaultBook));
        assertThat(elmInfo, CoreMatchers.containsString(defaultBook.getName()));
        assertThat(elmInfo, CoreMatchers.containsString(defaultBook.getAuthor()));
        assertThat(elmInfo, CoreMatchers.containsString(defaultBook.getYear() + ""));
    }

    @Test
    public void checkNumberOfLinesPrintedInAllBooksPrintISEqualToNumberOfBooks() {
        ListAllBooksMenuEntry menuEntry = new ListAllBooksMenuEntry(books);
        ArrayList<ArrayList<PrintableEntry>> allMapEntries = menuEntry.getAllMapEntries();
        assertThat(allMapEntries.size(), is(equalTo(books.size())));
    }

    @Test
    public void checkMoviePrintContainsEssentialInfo() {
        ListAllMoviesMenuEntry menuEntry = new ListAllMoviesMenuEntry(movies);
        PrinterFormat x = new PrinterFormat();
        String elmInfo = x.checkableItem(menuEntry.getBookPrintableEntries(defaultMovie));
        assertThat(elmInfo, CoreMatchers.containsString(defaultMovie.getName()));
        assertThat(elmInfo, CoreMatchers.containsString(defaultMovie.getYear() + ""));
        assertThat(elmInfo, CoreMatchers.containsString(defaultMovie.getDirector()));
    }


    @Test
    public void loginUnsuccessfull() {
        UserLoginMenuEntry menuEntry = new UserLoginMenuEntry(users, controllerUsers);
        menuEntry.execute(printStream, "XXX", "");
        String[] strArr = bytes.toString().split("\n");
        assertThat(strArr[0], is(equalTo(
                "This user data is not valid. Please type your info again.")));
    }

    @Test
    public void loginSuccesfull() {
        UserLoginMenuEntry menuEntry = new UserLoginMenuEntry(users, controllerUsers);
        menuEntry.execute(printStream, "123-4567", "abcde");
        String[] strArr = bytes.toString().split("\n");
        assertThat(strArr[0], is(equalTo(
                "You have successfully signed in")));
    }

    @Test(expected = InvalidUserOperationException.class)
    public void cantCheckOutItemIfNotLoggedIn() throws ItemAlreadyCheckedOutException, InvalidUserOperationException {
        controllerMovies.checkOutAnItem(defaultMovie.getName(), defaultUser);
    }

    @Test(expected = InvalidUserOperationException.class)
    public void cantReturnItemIfNotLoggedIn() throws ItemAlreadyReturnedException, InvalidUserOperationException, ItemAlreadyCheckedOutException {
        login();
        controllerMovies.checkOutAnItem(defaultMovie.getName(), defaultUser);
        logout();
        controllerMovies.returnAnItem(defaultMovie.getName(), defaultUser);
    }
}