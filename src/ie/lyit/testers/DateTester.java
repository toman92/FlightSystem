package ie.lyit.testers;

import java.util.ArrayList;

import ie.lyit.flight.Date;

public class DateTester {

	public static void main(String[] args) {
		
		ArrayList<Date> dates = new ArrayList<>();
		
		dates.add(new Date(17, 9, 2018));
		dates.add(new Date(5, 12, 2018));
		dates.add(new Date(4, 4, 2018));
		dates.add(new Date(4, 4, 2018));
		
		for(Date d : dates) {
			System.out.println(d);
		}
		
		System.out.print("Is date 1 and 2 equal? ");
		System.out.println(dates.get(0).equals(dates.get(1)));
		System.out.println();
		
		System.out.print("Is date 3 and 4 equal? ");
		System.out.println(dates.get(2).equals(dates.get(3)));
		System.out.println();
		
		dates.get(0).setDay(25);
		dates.get(1).setMonth(6);
		dates.get(2).setYear(2015);
		
		for(Date d : dates) {
			System.out.println(d);
		}

	}

}
