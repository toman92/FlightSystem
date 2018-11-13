package ie.lyit.testers;

import java.util.ArrayList;

import ie.lyit.flight.Name;

public class NameTester {

	public static void main(String[] args) {
		
		// Initialising classes and testing toString method
		Name name1, name2, name3;
		
		name1 = new Name("Mr", "Sean", "Toman");
		name2 = new Name("Miss", "Jane", "Blogs");
		name3 = new Name("Miss", "Jane", "Blogs");
		
		System.out.println(name1.toString() + "\n");
		System.out.println(name2.toString() + "\n");
		System.out.println(name3.toString() + "\n");
		System.out.println();
		
		// Check if equals method works
		System.out.print("Name 1 and 2 equal? ");
		System.out.println(name1.equals(name2));
		System.out.println();
		
		System.out.print("Name 2 and 3 equal? ");
		System.out.println(name2.equals(name3));
		System.out.println();
		
		System.out.print("Name 1 Female? ");
		System.out.println(name1.isFemale());
		
		System.out.print("Name 2 Female? ");
		System.out.println(name2.isFemale());
		
		System.out.print("Name 3 Female? ");
		System.out.println(name3.isFemale());
		System.out.println();
		
		// ArrayList
		ArrayList<Name> names = new ArrayList<>();
		names.add(name1);
		names.add(name2);
		names.add(name3);
		
		System.out.println("***After ArrayList***");
		for(Name n : names) {
			System.out.println(n.toString() + "\n");
		}
		
		// Testing name search method
		System.out.println("***After Name Search Method***");
		
		Name name4 = new Name("Mr", "Joe", "Blogs");
		System.out.print("Does List contain name 3? ");
		System.out.println(name4.nameSearch(name3, names));
		System.out.println();
		
		System.out.print("Does List contain name 4? ");
		System.out.println(name4.nameSearch(name4, names));
		System.out.println();
				
	}

}
