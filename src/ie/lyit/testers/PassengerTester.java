package ie.lyit.testers;

import java.util.ArrayList;

//import ie.lyit.flight.Date;
//import ie.lyit.flight.Name;
import ie.lyit.flight.Passenger;

public class PassengerTester {

	public static void main(String[] args) {
		
//		Date dob = new Date(19,12,1992);
//		Name name = new Name("Mr", "Sean", "Toman");
		ArrayList<Passenger> passengers = new ArrayList<>();
		passengers.add(new Passenger("Mr", "Sean", "Toman", 19, 12, 1992, 1, true));
		passengers.add(new Passenger("Mr", "Sean", "Toman", 19, 12, 1992, 1, true));
		passengers.add(new Passenger("Mr", "Sean", "Toban", 19, 12, 1992, 1, true));
		
		for(Passenger p : passengers) {
			System.out.println(p + "\n");
		}
		System.out.println();
		
		System.out.println("1st + 2nd equal? " + passengers.get(0).equals(passengers.get(1)));
		System.out.println("2nd + 3rd equal? " + passengers.get(1).equals(passengers.get(2)));
		
		for(Passenger p : passengers) {
			p.setPriorityBoarding(false);
			p.setNoBags(3);
			System.out.println(p);
		}
	}

}
