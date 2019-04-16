package com.twu.biblioteca.Console.Menu;

import com.twu.biblioteca.CheckableController;
import com.twu.biblioteca.CheckableItem;
import com.twu.biblioteca.Exception.ItemAlreadyCheckedOutException;
import com.twu.biblioteca.Utilities;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class CheckoutACheckableItem extends MenuEntry {
    private final CheckableController controllerItems;
    private final String itemName;
    private final HashMap<String, CheckableItem> checkablesMap;
    
    public CheckoutACheckableItem(HashMap<String, CheckableItem> checkablesMap, CheckableController controllerItems) {
        this.checkablesMap = checkablesMap;
        this.controllerItems =controllerItems;
        this.itemName = Utilities.getRandomHashMapElement(checkablesMap).getItemName();
    }


    public boolean execute(PrintStream printStream, String input) {

        CheckableItem item = checkablesMap.get(input);
        if(item!=null && controllerItems.canCheckOut(item.getName()))
        {
            try {
                controllerItems.checkOutAnItem(item.getName());
                printStream.println("Thank you! Enjoy the "+itemName);
                return true;
            } catch (ItemAlreadyCheckedOutException e) {
                e.printStackTrace();
            }
        }
        printStream.println("Sorry that "+itemName+" is not available");
        return false;
    }

    @Override
    public void execute(PrintStream printStream, Scanner scan) {
        printStream.println("Please type in the name of the " + itemName + " you want to borrow");
        boolean returnVal=false;
        while (!returnVal) {
            returnVal=execute(printStream,scan.nextLine());
        }
    }
}
