package com.twu.biblioteca.Checkable;

import com.twu.biblioteca.Exception.ItemAlreadyCheckedOutException;
import com.twu.biblioteca.Exception.ItemAlreadyReturnedException;
import com.twu.biblioteca.Exception.InvalidUserOperationException;
import com.twu.biblioteca.Users.User;
import com.twu.biblioteca.Users.UserController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CheckableController {

    private HashMap<String, CheckableItem> checkables;
    private final UserController userController;

    public CheckableController(HashMap<String, CheckableItem> checkables, UserController userController) {
        this.checkables = checkables;
        this.userController = userController;
    }

    public boolean canCheckOut(String checkableName,User user) {
        return checkables.containsKey(checkableName) && !checkables.get(checkableName).isCheckedOut()
                && (user==checkables.get(checkableName).getUser() || checkables.get(checkableName).getUser()==null);
    }

    public boolean UserIsNullOrNotSignedIn(User user){
        return user != userController.getCurrentSignedInUser() || user ==null;
    }

    public void checkOutAnItem(String checkableName, User user) throws ItemAlreadyCheckedOutException, InvalidUserOperationException {
        CheckableItem m = checkables.get(checkableName);

        if (m.isCheckedOut()) {
            throw new ItemAlreadyCheckedOutException();
        } else if ((m.getUser()!=null && m.getUser()!=user) || UserIsNullOrNotSignedIn(user)) {
            throw new InvalidUserOperationException();
        } else {
            m.setCheckedOutUser(user);
        }
    }

    public void returnAnItem(String checkableName, User user) throws ItemAlreadyReturnedException, InvalidUserOperationException {
        CheckableItem m = checkables.get(checkableName);
        if (!m.isCheckedOut()) {
            throw new ItemAlreadyReturnedException();
        } else if ((m.getUser()!=user)|| UserIsNullOrNotSignedIn(user)) {
            throw new InvalidUserOperationException();
        } else {
            m.setCheckedOutUser(null);
        }
    }

    public ArrayList<CheckableItem> getListOfCheckableItems() {
        return checkables.values().stream().filter(i -> !i.isCheckedOut()).collect(Collectors.toCollection(ArrayList::new));
    }
}
