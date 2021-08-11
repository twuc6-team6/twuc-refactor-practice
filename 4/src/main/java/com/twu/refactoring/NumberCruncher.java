package com.twu.refactoring;

import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class NumberCruncher {
    private final int[] numbers;
    private IntPredicate intPredicate;

    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    public int countEven() {
        intPredicate = number -> number%2 == 0;
        return filter(intPredicate);
    }

    public int countOdd() {
        intPredicate = number -> number%2 ==1;
        return filter(intPredicate);
    }

    public int countPositive() {
        intPredicate = number -> number >= 0;
        return filter(intPredicate);
    }

    public int countNegative() {
        intPredicate = number -> number < 0;
        return filter(intPredicate);
    }

    private int filter(IntPredicate predicate){
        return (int)Arrays.stream(numbers).filter(predicate).count();
    }

}
