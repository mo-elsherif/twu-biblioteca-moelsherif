package com.twu.biblioteca.Users;

import java.util.HashMap;

public class UserFactory {

    public static HashMap<String, User> createHardCodedUsers() {
        return new HashMap<String, User>() {
            {
                put("123-4567", new User("123-4567", "abcde", "John McTiernan", "asd@hotmai.c", "+49.."));
                put("123-4568", new User("123-4568", "abcde", "John Doe", "bsd@hotmai.c", "+48.."));
                put("123-4569", new User("123-4569", "abcde", "John Phillips", "csd@hotmai.c", "+47.."));
            }
        };
    }
}
