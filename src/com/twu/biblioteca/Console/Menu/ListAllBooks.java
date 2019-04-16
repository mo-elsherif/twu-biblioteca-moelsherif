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

    public ArrayList<ArrayList<PrintableEntry>> getAllMapEntries() {
        ArrayList<ArrayList<PrintableEntry>> result = new ArrayList<>();
        HashMap<String, CheckableItem> books= checkablesMovies;

        int longestNameLength = computePropertyMaxLength(new ArrayList<> (books.values()), ((ToIntFunction<? super Book>)
                (i -> i.getName().length())));

        int longestAuthorLength = computePropertyMaxLength(new ArrayList<> (books.values()), ((ToIntFunction<? super Book>)
                (i -> i.getAuthor().length())));

        for (CheckableItem itm : books.values()){
            Book book=(Book) itm;
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
