package unitTesting;

import static org.junit.Assert.*;
import tuition.Date;
import org.junit.Test;

public class DateTest {

	@Test
    public void testIsValidInvalidDate() {
        Date date = new Date("31/1/2021");
        assertFalse(date.isValid());
    }

    @Test
    public void testIsValidValidDate() {
        Date date = new Date("1/31/2021"); 
        assertTrue(date.isValid());
    }
    
    @Test
    public void testIsValidInvalidMonth() {
        Date date = new Date("13/1/2021");
        assertFalse(date.isValid());
    }
    
    @Test
    public void testIsValidValidMonth() {
        Date date = new Date("9/1/2021");
        assertTrue(date.isValid());
    }
    
    @Test
    public void testIsValidInvalidLeapyear() {
        Date date = new Date("2/29/2021");
        assertFalse(date.isValid());
    }
    
    @Test
    public void testIsValidInvalidYear() {
        Date date = new Date("8/27/2020");
        assertFalse(date.isValid());
    }
    
    @Test
    public void testIsValidValidYear() {
        Date date = new Date("8/27/2021");
        assertTrue(date.isValid());
    }
    
    @Test
    public void testIsValidInvalidDay() {
        Date date = new Date("9/32/2021"); 
        assertFalse(date.isValid());
    }
    
    @Test
    public void testIsValidValidDay() {
        Date date = new Date("9/30/2021");
        assertTrue(date.isValid());
    }
    
    @Test
    public void testIsValidInvalid31stDay() {
        Date date = new Date("9/31/2021");
        assertFalse(date.isValid());
    }
    
    @Test
    public void testIsValidValid31stDay() {
        Date date = new Date("5/31/2021");
        assertTrue(date.isValid());
    }
}
