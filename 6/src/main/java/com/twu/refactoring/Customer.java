package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {
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
		thisAmount += each.getMovie().getAmount(each.getDaysRented());
		return thisAmount;
	}

	private int countFrequentRenterPoints(Rental each){
		int curFrequentRenterPoints = 0;
		curFrequentRenterPoints++;
		if ((each.getMovie() instanceof NewReleaseMovie)
				&& each.getDaysRented() > 1)
			curFrequentRenterPoints++;
		return curFrequentRenterPoints;
	}

}
