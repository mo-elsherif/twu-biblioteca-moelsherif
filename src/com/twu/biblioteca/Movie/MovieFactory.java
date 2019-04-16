package com.twu.biblioteca.Movie;

import com.twu.biblioteca.Checkable.CheckableItem;

import java.util.HashMap;

public class MovieFactory {

    public static HashMap<String, CheckableItem> createHardCodedMoviesMap() {
        return new HashMap<String, CheckableItem>() {
            {
                put("Die Hard", new Movie("Die Hard", 1998, "John McTiernan", 5));
                put("Gladiator", new Movie("Gladiator", 2000, "Ridley Scott", 9));
                put("The Shining", new Movie("The Shining", 1997, "Mick Garris ", 0));
            }
        };
    }
}
