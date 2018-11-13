package ie.lyit.testers;

import java.util.ArrayList;

import ie.lyit.flight.Employee;

public class EmployeeTester {

	public static void main(String[] args) {
		
		ArrayList<Employee> employees = new ArrayList<>();
		
		Employee test1 = new Employee("Mr", "Sean", "Toman", 19, 12, 1992, 50000, 1, 10, 2018);
		Employee test2 = new Employee("Miss", "Jane", "Bloggs", 17, 4, 1994, 120000, 31, 9, 2017);
		
		employees.add(test1);
		employees.add(test2);
		employees.add(new Employee("Mr", "Sean", "Toman", 19, 12, 1992, 50000, 1, 10, 2018));
		employees.add(new Employee("Mr", "Joe", "Bloggs", 16, 8, 2000, 80000, 17, 9, 2018));
		
		System.out.println(test1);
		System.out.println(test2);
		
		System.out.println("Test 1 & 2 equal? " + test1.equals(test2) + "\n");
		
		System.out.println(employeeSearch(test1, employees));
		System.out.println(employeeSearch(new Employee("Mr", "Ciaran", "Toman", 26, 7, 1889, 90000, 27, 9, 2018), employees));

	}
	
	public static boolean employeeSearch(Employee emp, ArrayList<Employee> employees) {
		for(Employee e : employees) {
			if(e.equals(emp)) 
				return true;
		}
		return false;
	}

}
