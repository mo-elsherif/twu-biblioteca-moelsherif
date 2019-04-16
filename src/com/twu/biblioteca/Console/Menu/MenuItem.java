package com.twu.biblioteca.Console.Menu;

public class MenuItem {
    private String menuItemName;
    private MenuEntry menuEntry;

    public MenuItem(String menuItemName, MenuEntry menuItem) {
        this.menuItemName = menuItemName;
        this.menuEntry = menuItem;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public MenuEntry getMenuEntry() {
        return menuEntry;
    }
}
