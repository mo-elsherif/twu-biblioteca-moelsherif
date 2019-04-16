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
import com.twu.biblioteca.Console.Menu.MenuEntries.CheckoutACheckableItemMenuEntry;
import com.twu.biblioteca.Console.Menu.MenuEntries.ListAllBooksMenuEntry;
import com.twu.biblioteca.Console.Menu.MenuEntries.ListAllMoviesMenuEntry;
import com.twu.biblioteca.Console.Menu.MenuEntries.ReturnACheckableItemMenuEntry;
import com.twu.biblioteca.Exception.ItemAlreadyCheckedOutException;
import com.twu.biblioteca.Exception.ItemAlreadyReturnedException;
import com.twu.biblioteca.Movie.Movie;
import com.twu.biblioteca.Printer.PrintableEntry;
import com.twu.biblioteca.Printer.PrinterFormat;
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

    static Book toRemoveBook;
    static Movie toRemoveMovie;
    static HashMap<String, CheckableItem> movies;
    static HashMap<String, CheckableItem> books;
    static CheckableController controllerMovies;
    static CheckableController controllerBooks;
    static ByteArrayOutputStream bytes;
    static PrintStream printStream;

    @BeforeClass
    public static void setUp() {
        books = BibliotecaApp.books;
        movies = BibliotecaApp.movies;
        controllerMovies = new CheckableController(movies);
        controllerBooks = new CheckableController(books);
        toRemoveBook = (Book) Utilities.getRandomHashMapElement(books);
        toRemoveMovie = (Movie) Utilities.getRandomHashMapElement(movies);
    }

    @Before
    public void beforeTest() {
        bytes = new ByteArrayOutputStream();
        printStream = new PrintStream(bytes);
    }

    @After
    public void tearDown() {
        toRemoveBook.setCheckedOut(false);
        toRemoveMovie.setCheckedOut(false);
    }

    @Test
    public void checkableController() throws ItemAlreadyCheckedOutException {
        CheckableController controller = new CheckableController(movies);
        controller.checkOutAnItem(toRemoveMovie.getName());
        assertThat(toRemoveMovie.isCheckedOut(), is(equalTo(true)));
    }

    @Test(expected = ItemAlreadyCheckedOutException.class)
    public void cantCheckOutAlreadyCheckedOutItem() throws ItemAlreadyCheckedOutException {
        toRemoveMovie.setCheckedOut(true);
        CheckableController controller = new CheckableController(movies);
        controller.checkOutAnItem(toRemoveMovie.getName());
    }

    @Test
    public void canReturnAlreadyCheckedOutItem() throws ItemAlreadyReturnedException, ItemAlreadyCheckedOutException {
        CheckableController controller = new CheckableController(movies);
        controller.checkOutAnItem(toRemoveMovie.getName());
        controller.returnAnItem(toRemoveMovie.getName());
    }

    @Test(expected = ItemAlreadyReturnedException.class)
    public void cantReturnAlreadyReturnedItem() throws ItemAlreadyReturnedException {
        CheckableController controller = new CheckableController(movies);
        controller.returnAnItem(toRemoveMovie.getName());
    }

    @Test
    public void getListOfAllItemsAndCheckOutAnItemAndMakeSureItsRemovedFromTheList() throws ItemAlreadyCheckedOutException {
        CheckableController controller = new CheckableController(movies);
        ArrayList<CheckableItem> items = controller.getListOfCheckableItems();
        controller.checkOutAnItem(toRemoveMovie.getName());
        ArrayList<CheckableItem> itemsAfterCheckOut = controller.getListOfCheckableItems();
        items.removeAll(itemsAfterCheckOut);
        assertThat(items.size(), is(equalTo(1)));
        assertThat(items.get(0).getName(), is(equalTo(toRemoveMovie.getName())));
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
        CheckoutACheckableItemMenuEntry menuEntry = new CheckoutACheckableItemMenuEntry(books, controllerBooks);
        menuEntry.execute(printStream, toRemoveBook.getName());
        String[] strArr = bytes.toString().split("\n");
        assertThat(strArr[0], is(equalTo(
                "Thank you! Enjoy the book")));
    }

    @Test
    public void checkOutAMovie() {
        CheckoutACheckableItemMenuEntry menuEntry = new CheckoutACheckableItemMenuEntry(movies, controllerMovies);
        menuEntry.execute(printStream, toRemoveMovie.getName());
        String[] strArr = bytes.toString().split("\n");
        assertThat(strArr[0], is(equalTo(
                "Thank you! Enjoy the movie")));
    }

    @Test
    public void checkOutAnInvalidBook() {
        CheckoutACheckableItemMenuEntry menuEntry = new CheckoutACheckableItemMenuEntry(books, controllerBooks);
        menuEntry.execute(printStream, "XXX");
        String[] strArr = bytes.toString().split("\n");
        assertThat(strArr[0], is(equalTo(
                "Sorry that book is not available")));
    }

    @Test
    public void returnAValidBook() {
        toRemoveBook.setCheckedOut(true);
        ReturnACheckableItemMenuEntry menuEntry = new ReturnACheckableItemMenuEntry(books, controllerBooks);
        menuEntry.execute(printStream, toRemoveBook.getName());
        String[] strArr = bytes.toString().split("\n");
        assertThat(strArr[0], is(equalTo(
                "Thank you for returning the book")));
    }

    @Test
    public void returnAnInValidBook() {
        ReturnACheckableItemMenuEntry menuEntry = new ReturnACheckableItemMenuEntry(books, controllerBooks);
        menuEntry.execute(printStream, "XXX");
        String[] strArr = bytes.toString().split("\n");
        assertThat(strArr[0], is(equalTo(
                "This is not a valid book for return.")));
    }

    @Test
    public void checkedOutBooksDoNotAppearInListOfAllBooks() {
        ListAllBooksMenuEntry listAllBooksMenuEntry = new ListAllBooksMenuEntry(books);
        ArrayList<ArrayList<PrintableEntry>> allEntries = listAllBooksMenuEntry.getAllMapEntries();
        toRemoveBook.setCheckedOut(true);
        ArrayList<ArrayList<PrintableEntry>> allEntriesAfterCheckOut = listAllBooksMenuEntry.getAllMapEntries();
        assertThat(allEntriesAfterCheckOut.size(), is(equalTo(allEntries.size() - 1)));
    }

    @Test
    public void checkBookPrintContainsEssentialInfo() {
        ListAllBooksMenuEntry menuEntry = new ListAllBooksMenuEntry(books);
        PrinterFormat x = new PrinterFormat();
        String elmInfo = x.checkableItem(menuEntry.getBookPrintableEntries(toRemoveBook));
        assertThat(elmInfo, CoreMatchers.containsString(toRemoveBook.getName()));
        assertThat(elmInfo, CoreMatchers.containsString(toRemoveBook.getAuthor()));
        assertThat(elmInfo, CoreMatchers.containsString(toRemoveBook.getYear() + ""));
    }

    @Test
    public void checkNumberOfLinesPrintedInAllBooksPrintISEqualToNumberOfBooks() {
        ListAllBooksMenuEntry menuEntry = new ListAllBooksMenuEntry(books);
        PrinterFormat x = new PrinterFormat();
        ArrayList<ArrayList<PrintableEntry>> allMapEntries = menuEntry.getAllMapEntries();
        assertThat(allMapEntries.size(), is(equalTo(books.size())));
    }

    @Test
    public void checkMoviePrintContainsEssentialInfo() {
        ListAllMoviesMenuEntry menuEntry = new ListAllMoviesMenuEntry(movies);
        PrinterFormat x = new PrinterFormat();
        String elmInfo = x.checkableItem(menuEntry.getBookPrintableEntries(toRemoveMovie));
        assertThat(elmInfo, CoreMatchers.containsString(toRemoveMovie.getName()));
        assertThat(elmInfo, CoreMatchers.containsString(toRemoveMovie.getYear() + ""));
        assertThat(elmInfo, CoreMatchers.containsString(toRemoveMovie.getDirector()));
    }


}
