package com.twu.biblioteca;

public class Movie extends CheckableItem {
    private String director;
    private int rating; //0 means no rating

    public Movie(String name, int year, String director, int rating) {
        super(name, year);
        this.director = director;
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }
}
