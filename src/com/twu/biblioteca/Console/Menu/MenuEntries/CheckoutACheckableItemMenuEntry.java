package com.twu.biblioteca.Console.Menu.MenuEntries;

import com.twu.biblioteca.Checkable.CheckableController;
import com.twu.biblioteca.Checkable.CheckableItem;
import com.twu.biblioteca.Exception.InvalidUserOperationException;
import com.twu.biblioteca.Exception.ItemAlreadyCheckedOutException;
import com.twu.biblioteca.Messages;
import com.twu.biblioteca.Users.UserController;
import com.twu.biblioteca.Utilities.Utilities;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class CheckoutACheckableItemMenuEntry extends MenuEntry {
    private final CheckableController controllerItems;
    private final String itemName;
    private final UserController userController;
    private final HashMap<String, CheckableItem> checkablesMap;
    
    public CheckoutACheckableItemMenuEntry(HashMap<String, CheckableItem> checkablesMap, CheckableController controllerItems, UserController userController) {
        this.checkablesMap = checkablesMap;
        this.controllerItems =controllerItems;
        this.itemName = ((CheckableItem) Utilities.getRandomHashMapElement(checkablesMap)).getItemName();
        this.userController = userController;
    }


    public boolean execute(PrintStream printStream, String input) {

        CheckableItem item = checkablesMap.get(input);
        if(item!=null && controllerItems.canCheckOut(item.getName(),userController.getCurrentSignedInUser()))
        {
            try {
                controllerItems.checkOutAnItem(item.getName(),userController.getCurrentSignedInUser());
                printStream.println("Thank you! Enjoy the "+itemName);
                return true;
            } catch (ItemAlreadyCheckedOutException e) {
                printStream.println("Sorry that "+itemName+" is not available");
                return false;
            } catch (InvalidUserOperationException e) {
                printStream.println(Messages.userShouldSignInMessage());
                return false;
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
