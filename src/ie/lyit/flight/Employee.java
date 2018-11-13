package ie.lyit.flight;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Employee extends Person implements Payable, Serializable {

	/**
	 * Added to keep eclipse happy
	 */
	private static final long serialVersionUID = -4019574959476457467L;

	// Maximum allowable salary
	private final int MAX_SALARY = 150000;
	
	// Finals returned by readDetails() method depending on if user entered details in correct format or cancelled
	public static final int OK = 1;
	public static final int CANCEL = 2;
	public static final int ERROR = 3;
	
	// Unique employee number
	private static int nextEmployeeNo = 1000;
	
	// Employee instance variables
	private double salary;
	private Date startDate;
	private int employeeNo;
	
	public Employee() {
		super();
		salary = 0;
		startDate = new Date();
		employeeNo = nextEmployeeNo++;
	}
	
	public Employee(String t, String fn, String ln, int d, int m, int y, double salary, int sd, int sm, int sy) {
		super(t, fn, ln, d, m, y);
		try {
			startDate = new Date(sd, sm, sy);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Date Range Error", JOptionPane.ERROR_MESSAGE);
		}
		this.salary = salary;
		employeeNo = nextEmployeeNo++;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public int getEmployeeNo() {
		return employeeNo;
	}
	
	public void setSalary(double salary) {
		if(salary > MAX_SALARY)
			this.salary = MAX_SALARY;
		else
			this.salary = salary;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public void setStartDate(int d, int m, int y) {
		try {
			this.startDate.setDay(d);
			this.startDate.setMonth(m);
			this.startDate.setYear(y);
		} catch (IllegalArgumentException e) {
			System.out.println("Please enter a valid range\n" + e.getMessage());
		}
	}
	
	/**
	 * Read employee details from user
	 * @return true if all details have been entered in correct format
	 * Return false if format is wrong or user cancels
	 */
	public int readDetails() {
		// display employee number, make sure user can't change it
		JTextField txtEmployeeNo = new JTextField();
	    txtEmployeeNo.setText("" + this.getEmployeeNo());
	    txtEmployeeNo.setEditable(false);
	    
	    // combobox with available title options
	    JComboBox<String> jcbxTitle = new JComboBox<>();
	    for(String t : Name.TITLES) {
	    	jcbxTitle.addItem(t);
	    }

	    // first name and surname
	    JTextField txtFName = new JTextField();
	    txtFName.requestFocus();
	    JTextField txtSurname = new JTextField();
	    
	    // TODO - uncomment if JDateChooser doesnt work because i had to download it to import it
	    JTextField txtDOB = new JTextField();
	    
	    // salary
	    JTextField txtSalary = new JTextField();
	    
	    // start date chooser
	    JTextField txtStartDate = new JTextField();
  
	    // TODO - add txtDOB and delete dobChooser, add txtStartDate and delete startChooser
	    Object[] message = {
	        "Employee Number:", txtEmployeeNo,
	        "Title:", jcbxTitle,
	        "First Name:", txtFName,
	        "Surname:", txtSurname,
	        "D.O.B. (dd/mm/yyyy):", txtDOB,
	        "Salary:", txtSalary,
	        "Start Date (dd/mm/yyyy):", txtStartDate
	    };
	    
	    // display confirm dialog box for user to enter details
	    int option = JOptionPane.showConfirmDialog(null, message, "Enter Employee Details", JOptionPane.OK_CANCEL_OPTION);

	    if (option == JOptionPane.OK_OPTION){
	    	
	    	// Format of dates (eg. 25/10/2018)
	    	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    	
	        // Parse dob and start date to access get methods to get day, month and year int values to create Date objects with.
	    	try {
	    		// TODO - uncomments if date chooser doesn't work
	    		LocalDate dobIn = LocalDate.parse(txtDOB.getText(), dateFormat);
	    		LocalDate startIn = LocalDate.parse(txtStartDate.getText(), dateFormat);
	    		
	    		// Set title, first name and lastname
	    		this.setName((String)jcbxTitle.getSelectedItem(), txtFName.getText(), txtSurname.getText());
		        
	    		// set DOB Adding one to month because in Java calendar class January is represented as 0
	    		// getting calendar into its own variable as code was too long calling dobChooser.getCalendar() 3 times
	    		
	    		// TODO - uncomment if JDateChooser doesn't work
	    		this.setDob(dobIn.getDayOfMonth(), dobIn.getMonthValue(), dobIn.getYear());
	    		
		        // Set salary, catch numberFormatException
		        this.salary = Double.parseDouble(txtSalary.getText());
		        
		        // set start date
		        // TODO - uncomment if JDateChooser doesn't work
		        this.startDate = new Date(startIn.getDayOfMonth(), startIn.getMonthValue(), startIn.getYear());
		        
		        return OK; // everything worked ok
	    	} catch (DateTimeParseException e) {
	    		String dateFormatMessage = "Please enter date in [dd/mm/yyyy] format\n" + e.getMessage();
	    		JOptionPane.showMessageDialog(null, dateFormatMessage, "Date Format Error", JOptionPane.ERROR_MESSAGE);
	    		//System.out.println("Error, date is in wrong format\n" + e.getMessage());
	    	} catch (NumberFormatException e) {
	    		String numFormatMessage = "Please enter a double value (eg. 10.55) for salary\n" + e.getMessage();
	    		JOptionPane.showMessageDialog(null, numFormatMessage, "Wrong value in Salary", JOptionPane.ERROR_MESSAGE);
	    		//System.out.println("Error, Please enter a double value for salary\n" + e.getMessage());
	    	} catch (IllegalArgumentException e) {
	    		JOptionPane.showMessageDialog(null, "Invalid date range entered\n" + e.getMessage(), "Date Range Error", JOptionPane.ERROR_MESSAGE);
	    	} catch (NullPointerException e) {
	    		JOptionPane.showMessageDialog(null, "Date text will be green when in correct format\n" + e.getMessage(), "Date Format Error", JOptionPane.ERROR_MESSAGE);;
	    	}
	    } else if(option == JOptionPane.CANCEL_OPTION) {
	    	return CANCEL; // user cancelled
	    }
	    return ERROR; // there was an error 
	}
	
	/**
	 * Updates unique employee number so there are no duplicates
	 * @param nextNo
	 */
	public static void updateEmployeeNo(int nextNo) {
		nextEmployeeNo = nextNo;
	}
	
	@Override
	public double calculateWage(double taxPercentage) {
		//calculate monthly wage
		double wage = this.salary / 12;
		//calculate tax
		double tax = wage * (taxPercentage / 100);
		//return wage less tax
		return wage - tax;
	}

	@Override
	public double incrementSalary(double incrementAmount) {
		if(salary + incrementAmount > MAX_SALARY) 
			salary = MAX_SALARY;
		else
			salary += incrementAmount;

		return salary;
	}

	@Override
	public String toString() {
		return super.toString() + ", " + salary + ", " + startDate + ", " + employeeNo;
	}
	
	@Override
	public boolean equals(Object obj) {
		Employee e;
		
		if(obj == null)
			return false;
		
		if(obj instanceof Employee)
			e = (Employee) obj;
		else
			return false;
		
		// Only need to check employee number because its Unique
		return this.employeeNo == e.getEmployeeNo();
	}
}
