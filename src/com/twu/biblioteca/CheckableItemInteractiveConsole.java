package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CheckableItemInteractiveConsole {

    public static void handleCheckableItemBooking(PrintStream printStream, Scanner scanner, ArrayList<? extends CheckableItem> checkables) {
        CheckableItem checkableItem;
        do {
            printStream.println("Please type in the name of the " + ((CheckableItem) checkables.get(0)).getItemName() + " you want to borrow");
            checkableItem = getCheckedOutMovie(printStream, scanner, checkables);
        } while (checkableItem == null);
        checkableItem.setCheckedOut(true);
    }

    private static CheckableItem getCheckedOutMovie(PrintStream printStream, Scanner scanner, ArrayList<? extends CheckableItem> checkables) {
        String itemName = (checkables.get(0)).getItemName();
        return getBookAndHandlePrintingOfMessages(printStream, scanner, checkables, false,
                "Thank you! Enjoy the " + itemName,
                "Sorry that " + itemName + " is not available");
    }

    public static CheckableItem getBookAndHandlePrintingOfMessages(PrintStream out, Scanner scan, ArrayList<? extends CheckableItem> checkables, boolean bookShouldBeCheckedOut,
                                                                   String successMessage, String errorMessage) {
        while (scan.hasNext()) {
            String nextLine = scan.nextLine();

            ArrayList<CheckableItem> ff = checkables.stream().filter(i -> i.getName().equals(nextLine) && i.isCheckedOut() == bookShouldBeCheckedOut).collect(Collectors.toCollection(ArrayList::new));
            if (ff.size() == 1) {
                out.println(successMessage);
                CheckableItem b = ff.get(0);
                return b;
            }
            out.println(errorMessage);
        }
        return null;
    }

    public static CheckableItem getBookReturned(PrintStream out, Scanner scan, ArrayList<? extends CheckableItem> checkables) {
        String itemName = (checkables.get(0)).getItemName();
        return getBookAndHandlePrintingOfMessages(out, scan, checkables, true,
                "Thank you for returning the "+itemName, "This is not a valid "+itemName+" for return.");
    }

    public static void handleBookReturning(PrintStream out, Scanner scan, ArrayList<? extends CheckableItem> checkables) {
        CheckableItem book;
        String itemName = (checkables.get(0)).getItemName();
        do {
            out.println("Please type in the name of the "+itemName+" you want to return");
            book = getBookReturned(out, scan, checkables);
        } while (book == null);
        book.setCheckedOut(false);
    }
}
