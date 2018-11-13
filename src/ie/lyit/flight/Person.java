package ie.lyit.flight;

import java.io.Serializable;

import javax.swing.JOptionPane;

public abstract class Person implements Serializable {
	
	
	/**
	 * Added to keep eclipse happy
	 */
	private static final long serialVersionUID = -6813367610716611298L;
	
	private Name name;
	private Date dob;
	
	public Person() {
		this.name = new Name();
		this.dob = new Date();
	}
	
	public Person(String t, String fN, String lN, int day, int month, int year) {
		try {
			this.name = new Name(t, fN, lN);
			this.dob = new Date(day, month, year);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Not valid", JOptionPane.ERROR_MESSAGE);
			//System.out.println("Date not valid\n" + e.getMessage());
		}
	}
	
	public Name getName() {
		return name;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public void setName(String t, String fn, String ln) {
		try {
			name.setTitle(t);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Title Error", JOptionPane.ERROR_MESSAGE);
		}
		name.setFirstName(fn);
		name.setSurname(ln);
	}
	
	public void setName(Name name) {
		this.name = name;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public void setDob(int d, int m, int y) {
		try {
			dob.setDay(d);
			dob.setMonth(m);
			dob.setYear(y);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Date Range Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public String toString() {
		return name + ", " + dob;
	}
}
