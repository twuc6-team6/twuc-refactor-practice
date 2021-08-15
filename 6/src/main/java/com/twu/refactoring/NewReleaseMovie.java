package com.twu.refactoring;

public class NewReleaseMovie extends Movie{

    public NewReleaseMovie(String title) {
        super(title);
    }

    public double getAmount(int daysRented){
        return daysRented*3;
    }
}
