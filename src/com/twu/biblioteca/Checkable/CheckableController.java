package com.twu.biblioteca.Checkable;

import com.twu.biblioteca.Exception.ItemAlreadyCheckedOutException;
import com.twu.biblioteca.Exception.ItemAlreadyReturnedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CheckableController {

    private HashMap<String, CheckableItem> checkables;

    public CheckableController(HashMap<String, CheckableItem> checkables) {
        this.checkables = checkables;
    }

    public boolean canCheckOut(String checkableName) {
        return checkables.containsKey(checkableName) && !checkables.get(checkableName).isCheckedOut();
    }

    public void checkOutAnItem(String checkableName) throws ItemAlreadyCheckedOutException {
        CheckableItem m = checkables.get(checkableName);
        if (m.isCheckedOut()) {
            throw new ItemAlreadyCheckedOutException();
        } else {
            m.setCheckedOut(true);
        }
    }

    public void returnAnItem(String checkableName) throws ItemAlreadyReturnedException {
        CheckableItem m = checkables.get(checkableName);
        if (!m.isCheckedOut()) {
            throw new ItemAlreadyReturnedException();
        } else {
            m.setCheckedOut(false);
        }
    }

    public ArrayList<CheckableItem> getListOfCheckableItems(){
        return checkables.values().stream().filter(i -> !i.isCheckedOut()).collect(Collectors.toCollection(ArrayList::new));
    }
}
