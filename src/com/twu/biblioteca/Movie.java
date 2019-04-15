package com.twu.biblioteca;

public class Movie {
    private String name;
    private int year;
    private String director;
    private short rating; //0 means no rating

    public Movie(String name, int year, String director, short rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }


}
