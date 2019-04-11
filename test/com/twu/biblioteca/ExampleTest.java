package com.twu.biblioteca;

import java.io.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ExampleTest {
    ByteArrayOutputStream bytes;
    PrintStream console;
    String[] consoleLines;

    @Before
    public void setUp() {
        bytes = new ByteArrayOutputStream();
        console = System.out;
        System.setOut(new PrintStream(bytes));
        BibliotecaApp.main();
        consoleLines = bytes.toString().split("\n");
    }

    @After
    public void tearDown() {
        System.setOut(console);
    }

    @Test
    public void testBibliothecaGreetingMessage() {
        assertThat(consoleLines[0], is(equalTo(
                "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore")));
    }

    @Test
    public void testViewListOfAllBooks() {
        int i=1;
        assertThat(consoleLines[i++], is(equalTo(
                "- Harry Potter      | J. K. Rowling        | 1990")));
        assertThat(consoleLines[i++], is(equalTo(
                "- Lord of the Rings | J. R. R. Tolkien     | 1995")));
        assertThat(consoleLines[i++], is(equalTo(
                "- Tarzan            | Edgar Rice Burroughs | 1970")));
    }
}
