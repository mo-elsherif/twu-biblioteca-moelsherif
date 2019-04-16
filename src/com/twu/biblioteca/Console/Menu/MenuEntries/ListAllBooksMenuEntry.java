package com.twu.biblioteca.Console.Menu.MenuEntries;

import com.twu.biblioteca.Book.Book;
import com.twu.biblioteca.Checkable.CheckableItem;
import com.twu.biblioteca.Printer.PrintableEntry;
import com.twu.biblioteca.Utilities.IntTuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.ToIntFunction;

public class ListAllBooksMenuEntry extends ListAllItemsMenuEntry {
    public ListAllBooksMenuEntry(HashMap<String, CheckableItem> checkableMap) {
        super(checkableMap);
    }

    public ArrayList<PrintableEntry> getBookPrintableEntries(CheckableItem item) {
        Book book = (Book) item;
        IntTuple nameAndAuthorMaxLength = getMoviesNameAndDirectMaxLength();
        return new ArrayList<PrintableEntry>() {
            {
                add(new PrintableEntry(book.getName(), nameAndAuthorMaxLength.x));
                add(new PrintableEntry(book.getAuthor(), nameAndAuthorMaxLength.y));
                add(new PrintableEntry(book.getYear(), 0));
            }
        };
    }

    public IntTuple getMoviesNameAndDirectMaxLength() {
        int longestNameLength = computePropertyMaxLength(((ToIntFunction<? super Book>)
                (i -> i.getName().length())));
        int longestAuthorLength = computePropertyMaxLength(((ToIntFunction<? super Book>)
                (i -> i.getAuthor().length())));
        return new IntTuple(longestNameLength, longestAuthorLength);
    }


}
