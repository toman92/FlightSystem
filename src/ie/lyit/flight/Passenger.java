package ie.lyit.flight;

public class Passenger extends Person {
	
	/**
	 * Added to keep eclipse happy
	 */
	private static final long serialVersionUID = 59529160736440139L;
	
	private int noBags;
	private boolean priorityBoarding;
	
	public Passenger() {
		super();
		this.noBags = 0;
		this.priorityBoarding = false;
	}
	
	public Passenger(String t, String fn, String ln, int d, int m, int y, int noBags, boolean priorityBoarding) {
		super(t, fn, ln, d, m, y);
		this.noBags = noBags;
		this.priorityBoarding = priorityBoarding;
	}
	
	public int getNoBags() {
		return noBags;
	}
	
	public boolean isPriorityBoarding() {
		return priorityBoarding;
	}
	
	public void setNoBags(int noBags) {
		this.noBags = noBags;
	}
	
	public void setPriorityBoarding(boolean isPriorityBoarding) {
		this.priorityBoarding = isPriorityBoarding;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", " + noBags + ", Priority Boarding? " 
				+ (priorityBoarding?"Yes":"No");
	}
	
	@Override
	public boolean equals(Object obj) {
		Passenger p;
		
		if(obj == null)
			return false;
		
		if(obj instanceof Passenger) 
			p = (Passenger) obj;
		else
			return false;
		
		return this.getName().equals(p.getName()) 
				&& this.getDob().equals(p.getDob()) 
				&& this.noBags == p.getNoBags() 
				&& this.priorityBoarding == p.isPriorityBoarding();
	}
}
