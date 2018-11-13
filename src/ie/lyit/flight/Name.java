package ie.lyit.flight;

import java.io.Serializable;
import java.util.ArrayList;

public class Name implements Serializable {
	
	/**
	 * Added to keep eclipse happy.
	 */
	private static final long serialVersionUID = -6317839394079456544L;
	
	// Available titles
	public static final String[] TITLES = {"Mr", "Miss", "Mrs", "Ms"};
	
	// Instance variables
	private String title;
	private String firstName;
	private String surname;
	
	public Name() {
		this.title = null;
		this.firstName = null;
		this.surname = null;
	}
	
	public Name(String title, String firstName, String surname) throws IllegalArgumentException {
		if(!isTitleValid(title))
			throw new IllegalArgumentException("Title not valid [Mr, Miss, Mrs, Ms]");
			
		this.title = title;
		this.firstName = firstName;
		this.surname = surname;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setTitle(String title) throws IllegalArgumentException {
		if(!isTitleValid(title))
			throw new IllegalArgumentException("Title is not valid [Mr, Miss, Mrs, Ms]");
		
		this.title = title;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public boolean isFemale() {
		String[] titles = {"Miss", "Ms", "Mrs"};
		for(String s : titles) {
			if(this.title.equals(s))
				return true;
		}
		return false;
	}
	
	public boolean nameSearch(Name name, ArrayList<Name> names) {
		for(Name n : names) {
			if(n.equals(name))
				return true;
		}
		
		return false;
	}
	
	/**
	 *  Private method to check if title is a valid title
	 * @param title to be validated
	 * @return true if title is valid
	 */
	private boolean isTitleValid(String title) {
		for(String t : TITLES) {
			if(t.equals(title))
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return title + " " + firstName + " " + surname; 
	}
	
	@Override
	public boolean equals(Object obj) {
		Name n;
		
		if(obj instanceof Name)
			n = (Name) obj;
		else
			return false;
		
		return this.title.equals(n.title) && this.firstName.equals(n.firstName) 
				&& this.surname.equals(n.surname);
	}

}
