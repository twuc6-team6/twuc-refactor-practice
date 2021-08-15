package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {
	public static final double REGULAR_EXTRE_POINTS = 1.5;
	public static final double CHILDRENS_EXTRE_POINTS = 1.5;
	public static final int REGULAR_POINTS = 2;
	public static final double CHILDRENS_POINTS = 1.5;
	public static final int REGULAR_CRITICAL_DAY = 2;
	public static final int CHILDRENS_CRITICAL_DAY = 3;
	private String name;
	private ArrayList<Rental> rentalList = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = rentalList.iterator();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasNext()) {
			double thisAmount = 0;
			Rental each = rentals.next();
            thisAmount = this.determineAmountsForEachLine(each);
			frequentRenterPoints += countFrequentRenterPoints(each);
			result += "\t" + each.getMovie().getTitle() + "\t"
					+ String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;

		}
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

	private double determineAmountsForEachLine(Rental each){
		double thisAmount = 0;
		switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				thisAmount += REGULAR_POINTS;
				if (each.getDaysRented() > REGULAR_CRITICAL_DAY)
					thisAmount += (each.getDaysRented() - REGULAR_CRITICAL_DAY) * REGULAR_EXTRE_POINTS;
				break;
			case Movie.NEW_RELEASE:
				thisAmount += each.getDaysRented() * 3;
				break;
			case Movie.CHILDRENS:
				thisAmount += CHILDRENS_POINTS;
				if (each.getDaysRented() > CHILDRENS_CRITICAL_DAY)
					thisAmount += (each.getDaysRented() - CHILDRENS_CRITICAL_DAY) * CHILDRENS_EXTRE_POINTS;
				break;


		}
		return thisAmount;
	}

	private int countFrequentRenterPoints(Rental each){
		int curFrequentRenterPoints = 0;
		curFrequentRenterPoints++;
		if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
				&& each.getDaysRented() > 1)
			curFrequentRenterPoints++;
		return curFrequentRenterPoints;
	}

}
