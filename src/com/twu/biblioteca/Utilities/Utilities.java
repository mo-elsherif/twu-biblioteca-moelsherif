package com.twu.biblioteca.Utilities;

import com.twu.biblioteca.Checkable.CheckableItem;

import java.util.HashMap;

public class Utilities {

    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Object getRandomHashMapElement(HashMap<String, ? extends Object> hashmap) {
        return hashmap.get(hashmap.entrySet().iterator()
                .next().getKey());
    }
}
