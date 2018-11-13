package ie.lyit.flight;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DateTest {
	
	private Date d1;

	@Before
	public void setUp() throws Exception {
		d1 = new Date();
	}

	@Test
	public void testDate() {
		// Check dates have been set properly
		assertEquals(d1.getDay(), 0);
		assertEquals("Month should be 0", d1.getMonth(), 0);
		
	}

	@Test
	public void testDateIntIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDay() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMonth() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetYear() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
