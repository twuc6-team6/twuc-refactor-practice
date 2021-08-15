package com.twu.refactoring;

public class RegularMovie extends Movie{
    public static final int REGULAR_POINTS = 2;
    public static final int REGULAR_CRITICAL_DAY = 2;
    public static final double REGULAR_EXTRE_POINTS = 1.5;

    public RegularMovie(String title) {
        super(title);
    }

    public double getAmount(int daysRented){
        double amount = 0;
        amount += REGULAR_POINTS;
        if (daysRented > REGULAR_CRITICAL_DAY)
            amount += (daysRented - REGULAR_CRITICAL_DAY) * REGULAR_EXTRE_POINTS;
        return amount;
    }
}
