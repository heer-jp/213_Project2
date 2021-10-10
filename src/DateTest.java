package project2;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateTest {

	/*
	 *  prof said use meaningful method names as well as allowing us to
	 *  just call it ...test123 - EZ FIX
	 *  also not sure if we need to javadoc this & do you want to 
	 *  put it in it's own package? prof has no preference
	 */
	
	@Test
	public void testIsValid() {
		Date date = new Date("31/1/2021"); //invalid date format
		assertFalse(date.isValid());
	}

	@Test
	public void testIsValid2() {
		Date date = new Date("11/1/2021"); //valid date check -- valid format / month / year
		assertTrue(date.isValid());
	} //CURRENTLY INVALID DUE TO COMPATETO DATE METHOD
	
	@Test
	public void testIsValid3() {
		Date date = new Date("13/1/2021"); //invalid month check
		assertFalse(date.isValid());
	}
	
	@Test
	public void testIsValid4() {
		Date date = new Date("2/29/2022"); //invalid leap year
		assertFalse(date.isValid());
	}
	
	@Test
	public void testIsValid5() {
		Date date = new Date("2/29/2024"); //valid leap year check
		assertTrue(date.isValid());
	}//CURRENTLY INVALID DUE TO COMPATETO DATE METHOD
	
	@Test
	public void testIsValid6() {
		Date date = new Date("8/27/2020"); //invalid year check
		assertFalse(date.isValid());
	}
	
	@Test
	public void testIsValid7() {
		Date date = new Date("11/32/2021"); //invalid day check
		assertFalse(date.isValid());
	}
	
	@Test
	public void testIsValid8() {
		Date date = new Date("4/31/2021"); //invalid 31st day in 30-day month
		assertFalse(date.isValid());
	}
	
	@Test
	public void testIsValid9() {
		Date date = new Date("5/31/2021"); //valid 31st day in 31-day month
		assertTrue(date.isValid());
	}
	
	
} // class end - DELETE COMMENT
