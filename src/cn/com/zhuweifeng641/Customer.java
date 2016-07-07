package cn.com.zhuweifeng641;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
	private String _name;
	private Vector<Rental> _rentals = new Vector<Rental>();
	
	public Customer(String name){
		_name = name;
	}
	public void addRental(Rental rental){
		_rentals.addElement(rental);
	}
	public String getName() {
		return _name;
	}
	public String statement(){
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration<Rental> rentals = _rentals.elements(); 
		String result = "Rental Record for " + getName() + "\n";
		while(rentals.hasMoreElements()){
			double thisAmount = 0;
			Rental each = rentals.nextElement();
			
			//determine amounts for each line
			switch(each.get_movie().get_priceCode()){
				case Movie.REGULAR:
					thisAmount += 2;
					if(each.get_daysRented() > 2){
						thisAmount += (each.get_daysRented() - 2) * 1.5;
					}
					break;
				case Movie.NEW_RELEASE:
					thisAmount += each.get_daysRented() * 3;
					break;
				case Movie.CHILDRENS:
					thisAmount += 1.5;
					if (each.get_daysRented() > 3){
						thisAmount += (each.get_daysRented() - 3) * 1.5;
					}
					break;
			}
			//add frequent renter points
			frequentRenterPoints ++;
			if ((each.get_movie().get_priceCode() == Movie.NEW_RELEASE) && each.get_daysRented() > 1){
				frequentRenterPoints ++;
			}
			result += "\t" + each.get_movie().get_title() + "\t" + String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		
		return result;
	}
}
