package com.twu.biblioteca;

import com.twu.biblioteca.Movie.Movie;

import java.util.HashMap;

public class Utilities {

    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static CheckableItem getRandomHashMapElement(HashMap<String,CheckableItem> hashmap) {
        return  hashmap.get(hashmap.entrySet().iterator()
                .next().getKey());
    }
}
