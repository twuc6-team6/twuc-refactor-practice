package com.twu.refactoring;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class ReceiptTest {
    /**
     * Fixed Charges - 50 Rs.
     * Minimum Total Charges - 150 Rs.
     * <p/>
     * Non AC - 15 Rs/Km. for first 10 Km., 12 Rs/Km. from there on.
     * AC - 20 Rs/Km. for first 10 Km., 17 Rs/Km. from there on.
     * 20 % extra for peak time
     * <p/>
     * Sales Tax - 10 % on total bill amount
     */
    @Test
    public void shouldCalculateChargesForACTaxiFor30Kms() {
        double cost = new Receipt(new Taxi(true, 30, false)).getTotalCost();
        assertThat(cost, closeTo(649D, 0.01D));
    }

    @Test
    public void shouldCalculateChargesForNonACTaxiFor30Kms() {
        double cost = new Receipt(new Taxi(false, 30, false)).getTotalCost();
        assertThat(cost, closeTo(484D, 0.01D));
    }

    @Test
    public void shouldCalculateForACChargesFor30KmsPeakTime() {
        double cost = new Receipt(new Taxi(true, 30, true)).getTotalCost();
        assertThat(cost, closeTo(767.8D, 0.01D));

    }

    @Test
    public void shouldCalculateChargesForNonACTaxiFor30KmsPeakTime() {
        double cost = new Receipt(new Taxi(false, 30, true)).getTotalCost();
        assertThat(cost, closeTo(569.8D, 0.01D));
    }
}
