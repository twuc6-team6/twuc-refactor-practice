package com.twu.refactoring;

public class ChildrenMovie extends Movie{
    public static final int CHILDRENS_CRITICAL_DAY = 3;
    public static final double CHILDRENS_POINTS = 1.5;
    public static final double CHILDRENS_EXTRE_POINTS = 1.5;

    public ChildrenMovie(String title) {
        super(title);
    }

    public double getAmount(int daysRented){
        double amount = 0;
        amount += CHILDRENS_POINTS;
        if (daysRented > CHILDRENS_CRITICAL_DAY)
            amount += (daysRented - CHILDRENS_CRITICAL_DAY) * CHILDRENS_EXTRE_POINTS;
        return amount;
    }
}
