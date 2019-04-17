package com.twu.biblioteca.Console.Menu;

import com.twu.biblioteca.Console.Menu.MenuEntries.MenuEntry;

public class MenuItem {
    private String menuItemName;
    private MenuEntry menuEntry;


    private boolean isActive;

    public MenuItem(String menuItemName, MenuEntry menuItem, boolean isActive) {
        this.menuItemName = menuItemName;
        this.menuEntry = menuItem;
        this.isActive = isActive;
    }

    public MenuItem(String menuItemName, MenuEntry menuItem) {
        this(menuItemName, menuItem, true);
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public MenuEntry getMenuEntry() {
        return menuEntry;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
