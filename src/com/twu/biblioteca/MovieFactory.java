package com.twu.biblioteca;

import java.util.ArrayList;

public class MovieFactory {
    public static ArrayList<Movie> createHardCodedMovies() {
        return new ArrayList<Movie>() {
            {
                add(new Movie("Die Hard",1998, "John McTiernan", 5));
                add(new Movie("Gladiator",2000, "Ridley Scott", 9));
                add(new Movie("The Shining",1997, "Mick Garris ", 0));
            }
        };
    }
}
