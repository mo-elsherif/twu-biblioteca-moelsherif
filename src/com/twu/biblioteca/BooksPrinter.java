package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.ToIntFunction;

public class BooksPrinter {

    public static void printAllBooks(ArrayList<Book> books) {
        int longestNameLength = computePropertyMaxLength(books, (i -> i.getName().length()));
        int longestAuthorLength = computePropertyMaxLength(books, (i -> i.getAuthor().length()));
        for (Book book : books) {
            int spacesNecessaryAfterName = longestNameLength - book.getName().length();
            int spacesNecessaryAfterAuthor = longestAuthorLength - book.getAuthor().length();
            System.out.println(String.format("- %s%s | %s%s | %d", book.getName(),
                    spaces(spacesNecessaryAfterName), book.getAuthor(), spaces(spacesNecessaryAfterAuthor)
                    , book.getPublicationYear()));
        }
    }

    private static Integer computePropertyMaxLength(ArrayList<Book> books, ToIntFunction<? super Book> mapper) {
        return books.stream().mapToInt(mapper).max().orElse(0);
    }

    private static String spaces(int n) {
        return String.join("", Collections.nCopies(n, " "));
    }

}
