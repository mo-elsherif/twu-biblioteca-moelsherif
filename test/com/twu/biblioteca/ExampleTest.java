package com.twu.biblioteca;

import java.io.*;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ExampleTest {

    ByteArrayOutputStream bytes;
    PrintStream console;
    String[] startingConsoleLines;
    ByteArrayInputStream in;

    @Before
    public void setUp() {
        console = System.out;
        in = new ByteArrayInputStream(("1").getBytes());
        setBytesToOut();
    }

    @After
    public void tearDown() {
        System.setOut(console);
    }

    public void setBytesToOut() {
        bytes = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bytes));
    }

    public void normalSetup() {
        BibliotecaApp.printConsoleMessages(in, System.out);
        startingConsoleLines = bytes.toString().split("\n");
    }

    @Test
    public void testBibliothecaGreetingMessage() {
        normalSetup();
        assertThat(startingConsoleLines[0], is(equalTo(
                "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore")));
    }

    @Test
    public void testViewListOfAllBooks() {
        normalSetup();
        int i = 2;
        //+System.lineSeparator()+"2"
        assertThat(startingConsoleLines[i++], is(equalTo(
                "- Harry Potter      | J. K. Rowling        | 1990")));
        assertThat(startingConsoleLines[i++], is(equalTo(
                "- Lord of the Rings | J. R. R. Tolkien     | 1995")));
        assertThat(startingConsoleLines[i++], is(equalTo(
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
            in = new ByteArrayInputStream((errorCase +System.lineSeparator() + "1").getBytes());
            BibliotecaApp.printConsoleMessages(in, System.out);
            startingConsoleLines = bytes.toString().split("\n");
            assertThat(startingConsoleLines[2], is(equalTo(
                    "Please select a valid option!")));
        }
    }
}
