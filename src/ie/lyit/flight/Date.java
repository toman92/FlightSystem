package ie.lyit.flight;

import java.io.Serializable;

public class Date implements Serializable{
	
	/**
	 * Added to keep eclipse happy.
	 */
	private static final long serialVersionUID = -8120280467667688686L;
	
	// Instance variables
	private int day;
	private int month;
	private int year;
	
	// Default constructor
	public Date() {
		this.day = 1;
		this.month = 1;
		this.year = 1900;
	}
	
	/**
	 * 
	 * @param day int value (1-31)
	 * @param month int value (1-12)
	 * @param year int value (1900 or above)
	 * @throws IllegalArgumentException if values are out of range
	 */
	public Date(int day, int month, int year) throws IllegalArgumentException {
		// Checks if year is valid before setting. Throws Exception if not valid
		if(year < 1900)
			throw new IllegalArgumentException("Year must be above 1900");
		this.year = year;
		
		// Checks if month is valid before setting. Throws Exception if not valid
		if(month < 1 || month > 12)
			throw new IllegalArgumentException("Month out of range [1-12]");
		this.month = month;
		
		// Checks if day is valid based on the month previously set. Throws exception if not valid
		if(!this.isDayValid(day))
			throw new IllegalArgumentException("Day out of range [1-28, 30, 31]");
		this.day = day;
	}
	
	/**
	 * Get mathod for day
	 * @return day int value
	 */
	public int getDay() {
		return day;
	}
	
	/**
	 * Month get method
	 * @return month in value
	 */
	public int getMonth() {
		return month;
	}
	
	/**
	 * Year get method
	 * @return year int value
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Day set method
	 * @param day in value (1-31)
	 * @throws IllegalArgumentException if day value is out of range
	 */
	public void setDay(int day) throws IllegalArgumentException {
		if(!this.isDayValid(day)) 
			throw new IllegalArgumentException("Day out of range [1-28, 30, 31]");
		
		this.day = day;
	}
	
	/**
	 * Month set method
	 * @param month int value (1-12)
	 * @throws IllegalArgumentException if month value is out of range
	 */
	public void setMonth(int month) throws IllegalArgumentException{
		if(month < 1 || month > 12) 
			throw new IllegalArgumentException("Month out of range [1-12]");
		
		this.month = month;
	}
	
	/**
	 * Year set method
	 * @param year int value (1900 or above)
	 * @throws IllegalArgumentException if year value is below 1900
	 */
	public void setYear(int year) throws IllegalArgumentException {
		if(year < 1900)
			throw new IllegalArgumentException("Year must be above 1900");
		
		this.year = year;
	}
	
	@Override
	public String toString() {
		return "" + (day > 9? day : "0" + day) + "/" 
				+ (month > 9? month : "0" + month) + "/"
				+ year;
	}
	
	@Override
	public boolean equals(Object obj) {
		Date d;
		
		if(obj == null)
			return false;
		
		if(obj instanceof Date)
			d = (Date)obj;
		else
			return false;
		
		return this.day == d.getDay() && this.month == d.getMonth() && this.year == d.getYear();
	}
	
	/**
	 * Private method to check if a day is valid based on given month
	 * @param day
	 * @return
	 */
	private boolean isDayValid(int day) {
		if(day < 1)
			return false;
		if(month == 2) {
			if(day > 28)
				return false;
		} else if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			if(day > 31)
				return false;
		} else {
			if(day > 30)
				return false;
		}
		return true;
	}

}
