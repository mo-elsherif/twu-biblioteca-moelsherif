package com.twu.biblioteca.Console.Menu;

import com.twu.biblioteca.Book.Book;
import com.twu.biblioteca.CheckableItem;
import com.twu.biblioteca.Printer.PrintableEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.ToIntFunction;

public class ListAllBooks extends ListAllItems {
    public ListAllBooks(HashMap<String, CheckableItem> checkableMap) {
        super(checkableMap);
    }

    public ArrayList<ArrayList<PrintableEntry>> getBookPrintableEntries() {
        ArrayList<ArrayList<PrintableEntry>> result = new ArrayList<>();
        ArrayList<Book> books = (ArrayList) checkablesMovies.values();

        int longestNameLength = computePropertyMaxLength(books, ((ToIntFunction<? super Book>)
                (i -> i.getName().length())));

        int longestAuthorLength = computePropertyMaxLength(books, ((ToIntFunction<? super Book>)
                (i -> i.getAuthor().length())));

        for (Book book : books) {
            if (book.isCheckedOut()) {
                continue;
            }
            ArrayList<PrintableEntry> item = new ArrayList<PrintableEntry>() {
                {
                    add(new PrintableEntry(book.getName(), longestNameLength));
                    add(new PrintableEntry(book.getAuthor(), longestAuthorLength));
                    add(new PrintableEntry(book.getYear(), 0));
                }
            };
            result.add(item);
        }
        return result;
    }
}
