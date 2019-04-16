package com.twu.biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.twu.biblioteca.Book.Book;
import com.twu.biblioteca.Exception.ItemAlreadyCheckedOutException;
import com.twu.biblioteca.Exception.ItemAlreadyReturnedException;
import com.twu.biblioteca.Movie.Movie;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ExampleTest {

    static int menuLength;
    static String indexQuit;
    static Book toRemoveBook;
    static Movie toRemoveMovie;
    static Movie toRemoveMovieMap;

    @BeforeClass
    public static void setUp() {
        menuLength = BibliotecaApp.menuOptions.length;
        indexQuit = menuLength + ""; //because its always the last option
        toRemoveBook = BibliotecaApp.books.get(0);
        toRemoveMovie = BibliotecaApp.movies.get(0);
        toRemoveMovieMap = (Movie) BibliotecaApp.moviesMap.get(BibliotecaApp.moviesMap.entrySet().iterator()
                .next().getKey());
    }

    @After
    public void tearDown() {
        toRemoveBook.setCheckedOut(false);
        toRemoveMovie.setCheckedOut(false);
        toRemoveMovieMap.setCheckedOut(false);
    }

    public ByteArrayInputStream addLineSeparatorBetweenWordsAndReturnByteArrayInputStream(String... inputs) {
        String result = "";
        for (String s : inputs) {
            result += s + System.lineSeparator();
        }
        return new ByteArrayInputStream(result.getBytes());
    }

    enum Stage {
        BASIC,
        BOOKBOOKING,
        BOOKRETURNING,
        MOVIEBOOKING,
    }

    public String[] applyConsoleCommands(Stage stage, String... args) {
        ByteArrayInputStream inputStream = addLineSeparatorBetweenWordsAndReturnByteArrayInputStream(args);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bytes);

        switch (stage) {
            case BASIC:
                BibliotecaApp.printConsoleMessages(printStream, inputStream);
                break;
            case BOOKBOOKING:
                CheckableItemInteractiveConsole.handleCheckableItemBooking(printStream, new Scanner(inputStream), BibliotecaApp.books);
                break;
            case BOOKRETURNING:
                CheckableItemInteractiveConsole.handleBookReturning(printStream, new Scanner(inputStream), BibliotecaApp.books);
                break;
            case MOVIEBOOKING:
                CheckableItemInteractiveConsole.handleCheckableItemBooking(printStream, new Scanner(inputStream), BibliotecaApp.movies);
                break;
        }
        String[] strArr = bytes.toString().split("\n");
        return strArr;
    }

    public String[] normalSetup(String... args) {
        return applyConsoleCommands(Stage.BASIC, args);
    }

    public String[] bookBookingNormalSetup(String... args) {
        return applyConsoleCommands(Stage.BOOKBOOKING, args);
    }

    public String[] movieBookingNormalSetup(String... args) {
        return applyConsoleCommands(Stage.MOVIEBOOKING, args);
    }

    public String[] bookReturningNormalSetup(String... args) {
        return applyConsoleCommands(Stage.BOOKRETURNING, args);
    }

    public String[] getPrintedBooksInAllBookOption() {
        ByteArrayOutputStream localBytes = new ByteArrayOutputStream();
        PrintStream localPrintStream = new PrintStream(localBytes);
        PrinterClass.printAllBooks(localPrintStream, BibliotecaApp.books);
        return localBytes.toString().split("\n");
    }

    @Test
    public void testBibliothecaGreetingMessage() {
        String[] console = normalSetup("1", indexQuit);
        assertThat(console[0], is(equalTo(
                "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore")));
    }

    @Test
    public void testViewListOfAllBooks() {
        String[] console = normalSetup("1", indexQuit);
        int i = menuLength + 1;
        assertThat(console[i++], is(equalTo(
                "- Harry Potter      | J. K. Rowling        | 1990")));
        assertThat(console[i++], is(equalTo(
                "- Lord of the Rings | J. R. R. Tolkien     | 1995")));
        assertThat(console[i++], is(equalTo(
                "- Tarzan            | Edgar Rice Burroughs | 1970")));
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
            String[] console = normalSetup(errorCase, "1", indexQuit);
            assertThat(console[menuLength + 1], is(equalTo(
                    "Please select a valid option!")));
        }
    }

    @Test
    public void testQuitApplicationOnQuit() {
        String[] console = normalSetup(indexQuit);
        assertThat(console[menuLength + 1], is(equalTo(
                "Thank you for using our Application.")));
    }

    @Test
    public void checkedOutBooksDoNotAppearInListOfAllBooks() {

        String[] printedBooksBeforeCheckingOut = getPrintedBooksInAllBookOption();

        boolean stateBeforeCheckingOut = toRemoveBook.isCheckedOut();

        normalSetup("2", toRemoveBook.getName(), "1", indexQuit);

        boolean stateAfterCheckingOut = toRemoveBook.isCheckedOut();
        String[] printedBooksAfterCheckingOut = getPrintedBooksInAllBookOption();

        assertThat(printedBooksAfterCheckingOut.length + 1, is(equalTo(printedBooksBeforeCheckingOut.length)));
        assertThat(stateBeforeCheckingOut, is(equalTo(false)));
        assertThat(stateAfterCheckingOut, is(equalTo(true)));
    }

    @Test
    public void successMessageOnBookCheckOut() {
        String[] strArr = bookBookingNormalSetup(toRemoveBook.getName());
        assertThat(strArr[1], is(equalTo(
                "Thank you! Enjoy the book")));
    }

    @Test
    public void unsuccessfullMessageOnBookCheckOut() {
        String[] strArr = bookBookingNormalSetup("XXX", toRemoveBook.getName());
        assertThat(strArr[1], is(equalTo(
                "Sorry that book is not available")));
    }

    @Test
    public void successfullMessageOnBookReturn() {
        bookBookingNormalSetup(toRemoveBook.getName());
        String[] strArr = bookReturningNormalSetup(toRemoveBook.getName());
        boolean stateAfterReturning = toRemoveBook.isCheckedOut();
        assertThat(stateAfterReturning, is(equalTo(false)));
        assertThat(strArr[1], is(equalTo(
                "Thank you for returning the book")));
    }

    @Test
    public void unsuccessfullMessageOnBookReturn() {
        toRemoveBook.setCheckedOut(true);
        String[] strArr = bookReturningNormalSetup("XXX", toRemoveBook.getName());
        assertThat(strArr[1], is(equalTo(
                "This is not a valid book for return.")));
    }

    @Test
    public void testViewListOfAllMovies() {
        String[] console = normalSetup("4", indexQuit);
        int i = menuLength + 1;
        assertThat(console[i++], is(equalTo(
                "- Die Hard    | John McTiernan | 1998 | 5")));
        assertThat(console[i++], is(equalTo(
                "- Gladiator   | Ridley Scott   | 2000 | 9")));
        assertThat(console[i++], is(equalTo(
                "- The Shining | Mick Garris    | 1997 | 0")));
    }

    @Test
    public void checkOutAMovie() {
        String[] strArr = movieBookingNormalSetup(toRemoveMovie.getName());
        assertThat(strArr[1], is(equalTo(
                "Thank you! Enjoy the movie")));
    }

    @Test
    public void checkableController() throws ItemAlreadyCheckedOutException {
        CheckableController controller = new CheckableController(BibliotecaApp.moviesMap);
        controller.checkOutAnItem(toRemoveMovieMap.getName());
        assertThat(toRemoveMovieMap.isCheckedOut(), is(equalTo(true)));
    }

    @Test(expected = ItemAlreadyCheckedOutException.class)
    public void cantCheckOutAlreadyCheckedOutItem() throws ItemAlreadyCheckedOutException {
        toRemoveMovieMap.setCheckedOut(true);
        CheckableController controller = new CheckableController(BibliotecaApp.moviesMap);
        controller.checkOutAnItem(toRemoveMovieMap.getName());
    }

    @Test
    public void canReturnAlreadyCheckedOutItem() throws ItemAlreadyReturnedException, ItemAlreadyCheckedOutException {
        CheckableController controller = new CheckableController(BibliotecaApp.moviesMap);
        controller.checkOutAnItem(toRemoveMovieMap.getName());
        controller.returnAnItem(toRemoveMovieMap.getName());
    }

    @Test(expected = ItemAlreadyReturnedException.class)
    public void cantReturnAlreadyReturnedItem() throws ItemAlreadyReturnedException {
        CheckableController controller = new CheckableController(BibliotecaApp.moviesMap);
        controller.returnAnItem(toRemoveMovieMap.getName());
    }

    @Test
    public void getListOfAllItemsAndCheckOutAnItemAndMakeSureItsRemovedFromTheList() throws ItemAlreadyCheckedOutException {
        CheckableController controller = new CheckableController(BibliotecaApp.moviesMap);
        ArrayList<CheckableItem> items=controller.getListOfCheckableItems();
        controller.checkOutAnItem(toRemoveMovieMap.getName());
        ArrayList<CheckableItem> itemsAfterCheckOut=controller.getListOfCheckableItems();
        items.removeAll(itemsAfterCheckOut);
        assertThat(items.size(), is(equalTo(1)));
        assertThat(items.get(0).getName(), is(equalTo(toRemoveMovieMap.getName())));
    }

}
