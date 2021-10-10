package project2;

import static org.junit.Assert.*;

import org.junit.Test;

public class InternationalTest {
	
    public static final int FULLTIME_TUITION_COST = 29737;
    public static final int UNIVERSITY_FEE = 3268;
    public static final int ADDITIONAL_FEE = 2650;
    public static final int NON_STUDY_ABROAD_FEE = FULLTIME_TUITION_COST + UNIVERSITY_FEE + ADDITIONAL_FEE;
    public static final int STUDY_ABROAD_FEE = UNIVERSITY_FEE + ADDITIONAL_FEE;
    public static final int UNCALCULATED_FEE = 0;
    
	//can I add magic numbers
  //big issue is that it's never calculated ..
    // only two cases that come to mind should I test for invalid students even though they wouldn't come through

	@Test
	public void testTuitionDue() { //test to see if international study abroad students pay $5918
		International student = new International("Sam Walsh","CS",12,true); 
		System.out.print(student.getTuition());
		if(student.getIsTuitionCalculated()) {
		assertTrue(student.getTuition() == STUDY_ABROAD_FEE);
		}
		else {
			assertFalse(student.getTuition() != UNCALCULATED_FEE);
		}
	}
	
	@Test
	public void testTuitionDue2() { //test to see if non international study abroad students pay $35,655
		International student = new International("Jane Doe","CS",12,false);
		if(student.getIsTuitionCalculated()) {
		assertTrue(student.getTuition() == NON_STUDY_ABROAD_FEE);
		}
		else {
			assertFalse(student.getTuition() != UNCALCULATED_FEE);
		}
	}

}
