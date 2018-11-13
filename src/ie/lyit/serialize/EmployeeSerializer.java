package ie.lyit.serialize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ie.lyit.flight.Employee;

public class EmployeeSerializer implements Serializable {
	
	/**
	 * Added to keep eclipse happy
	 */
	private static final long serialVersionUID = 2387587185998580355L;

	private final String FILENAME = "employees.bin"; // filename of binary file to save objects to
	private ArrayList<Employee> employees;
	
	public EmployeeSerializer() {
		employees = new ArrayList<Employee>();
	}
	
	/**
	 * Creates a new employee object and calls its read method to read details from user.
	 * If the user cancels, the unique employee number is reset and the employee is not added to the list
	 * If some details are entered incorrectly the employee will not be added to list and the method will recursively
	 * loop to the user cancels or enters details in the correct format
	 */
	public void add() {
		// Create a new Employee and read details from user 
		Employee e = new Employee();
		int isOk = e.readDetails(); // Returns public final depending on if it worked, was cancelled or there was a format error
		
		// Add newly created employee to arrayList if details have been entered correctly
		if(isOk == Employee.OK) {
			employees.add(e);
		} else {
			// Reset static employee number because employee was not added to list
			if(!employees.isEmpty())
				Employee.updateEmployeeNo(employees.get(employees.size() - 1).getEmployeeNo() + 1);
			
			// if it was user input error and not the user cancelling, repeat to input is entered in correct format or user cancels 
			if(isOk == Employee.ERROR)
				add();
		}
	
	}
	
	/**
	 * allows user to enter an employee number,
	 * Displays the employees details to screen if employee is in list
	 * Displays appropriate message if user cancels operation or employee is not in list
	 * @return Employee to be viewed
	 */
	public Employee view() {
		// boolean flag (assuming employee is not is list)
		boolean isFound = false;
		
		// Text field to get employee number from user
		JTextField userEmpNo = new JTextField();
		userEmpNo.requestFocus();
		
		Object[] numMessage = {"Enter Employee Number:", userEmpNo};
		
		// to hold employee number entered by user
		int userKey = -1;
		
		// shows dialog box for user to enter employee number, or cancel
		int option = JOptionPane.showConfirmDialog(null, numMessage, "Employee Number", JOptionPane.OK_CANCEL_OPTION);
		
		if(option == JOptionPane.OK_OPTION) {
			// try parse integer value from text field, catch exception
			try {
				userKey = Integer.parseInt(userEmpNo.getText());
			} catch (NumberFormatException e) {
				System.out.println("FAILED!! Please enter an integer\n" + e.getMessage());
			}

			// finds employee number in employees list, displays employee and returns employee
			for(Employee e : employees) {
				if(userKey == e.getEmployeeNo()) {
					isFound = true; // employee found
					
					Object[] empDetails = {
							"Employee Number: " + e.getEmployeeNo(),
							"Name: " + e.getName(),
							"DOB: " + e.getDob(),
							"Salary: " + e.getSalary(),
							"Start Date: " + e.getStartDate()
					};
					JOptionPane.showMessageDialog(null, empDetails, "Employee Details", JOptionPane.INFORMATION_MESSAGE);
					
					System.out.println(e);
					return e;
				}
			}	
		} else {
			// assume employee has been found but user no longer wants the employee returned
			isFound = true;
			JOptionPane.showMessageDialog(null, "Operation Cancelled", "Cancelled", JOptionPane.WARNING_MESSAGE);
		}
		
		// Display appropriate message if employee isn't found in list.
		// Give user option to search again
		if(!isFound) {
			JOptionPane.showMessageDialog(null, "Employee not found in list", "Error", JOptionPane.ERROR_MESSAGE);
			
			// ask user if they want to search again. 
			int searchAgain = JOptionPane.showConfirmDialog(null, "Do you want to search again?", "Search", JOptionPane.OK_CANCEL_OPTION);
			
			//If ok option, call view() method again and return the employee object returned by it
			if(searchAgain == JOptionPane.OK_OPTION)
				return view();
		}
		return null;
	}
	
	/**
	 * for each employee on record, print details to console
	 */
	public void list() {
		// display message if no employees in list , otherwise print details to screen
		if(employees.isEmpty()) { 
			JOptionPane.showMessageDialog(null, "There are no employees to list", "No Employees", JOptionPane.INFORMATION_MESSAGE);
		} else {
			for(Employee e : employees) {
				System.out.println(e);
			}
		}
	}
	
	/**
	 * allows user to edit an employees details
	 */
	public void edit() {
		// get employee to be viewed from user
		Employee e = view();
		
		// find position of employee in array list
		if(e != null) {
			int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to edit this employee?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
			
			if(option == JOptionPane.OK_OPTION) {
				// get position of employee object in array list
				int empPos = employees.indexOf(e);
				
				// read in new details from user
				int isOk = e.readDetails();
				
				if(isOk == Employee.OK) {
					// overwrite employee in array list with new object
					employees.set(empPos, e);
					JOptionPane.showMessageDialog(null, "Employee successfully edited", "Success", JOptionPane.INFORMATION_MESSAGE);
				} else if (isOk == Employee.CANCEL) {
					JOptionPane.showMessageDialog(null, "Operation Cancelled", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Make sure all fields are in correct format", "Format Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	/**
	 * allows a user to delete an employee from the list
	 */
	public void delete() {
		Employee e = view();
		if(e != null) {
			// if employee is found, confirm with user before delete
			int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this employee?", "Employee Found", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				employees.remove(e);
				JOptionPane.showMessageDialog(null, "Employee successfully deleted from system", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
	
	/** 
	 * serializes (saves) all objects in the array list to a binary file
	 */
	public void serializeEmployees() {
		
		try {
			FileOutputStream fs = new FileOutputStream(FILENAME);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(employees);
			os.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NotSerializableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	/** 
	 * Loads all saved employee objects from binary file into array list
	 */
	@SuppressWarnings("unchecked")
	public void deserializeEmployees() {
		Path filePath = Paths.get(FILENAME);
		
		if(Files.exists(filePath)) {
			try {
				FileInputStream fs = new FileInputStream(FILENAME);
				ObjectInputStream os = new ObjectInputStream(fs);
			
				// read object from file and check it is an arraylist
				employees = (ArrayList<Employee>) os.readObject();
				os.close();
				
				// update unique employee number after deserialization so no duplicates.
				if(!employees.isEmpty())
					Employee.updateEmployeeNo(employees.get(employees.size() - 1).getEmployeeNo() + 1);
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NotSerializableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
