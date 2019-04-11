package com.twu.biblioteca;

import java.io.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void test() {
        assertEquals(1, 1);
    }


        private static final String EOL =
                System.getProperty("line.separator");

        @Test
        public void testShowUsageWhenInsufficientArgumentsSupplied() {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            PrintStream console = System.out;
            try {
                System.setOut(new PrintStream(bytes));
                BibliotecaApp.main();
            } finally {
                System.setOut(console);
            }
            assertEquals(String.format(
                    "Welcome to Bibliotheca. Your one-stop-shop for great book titles in Bangalore\n"),
                    bytes.toString());
        }
    }
