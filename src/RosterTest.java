package project2;

import static org.junit.Assert.*;

import org.junit.Test;

public class RosterTest {

	@Test
	public void testAdd() { //add resident with 20 credits
		Student student = new Student ("John Doe", "it", 20);
		Roster roster = new Roster();
		assertTrue(roster.add(student));
	}
	
	@Test
	public void testAdd2() { //add non-resident with 20 credits
		NonResident student = new NonResident ("Hannah Dez", "CS", 20);
		Roster roster = new Roster();
		assertTrue(roster.add(student));
	}
	
	@Test
	public void testAdd3() { //add tristate student with 20 credits
		TriState student = new TriState ("Ali Winchester", "bA", 20,"CT");
		Roster roster = new Roster();
		assertTrue(roster.add(student));
	}
	
	@Test
	public void testAdd4() { //add international student with 20 credits
		International student = new International ("Jo Joe", "Ee", 12, false);
		Roster roster = new Roster();
		assertTrue(roster.add(student));
	}

	@Test
	public void testRemove() { //remove a non existing resident student
		Student student = new Student ("Jay Park", "me", 15);
		Roster roster = new Roster();
		assertFalse(roster.remove(student));
	}
	
	@Test
	public void testRemove2() {//remove existing resident 
		Student student = new Student ("Rick Tay", "iT", 8);
		Roster roster = new Roster();
		roster.add(student);
		assertTrue(roster.remove(student));
	}
	@Test
	public void testRemove3() { //remove a non existing non-resident student
		NonResident student = new NonResident ("Donald Grover", "cs", 20);
		Roster roster = new Roster();
		assertFalse(roster.remove(student));
	}
	
	@Test
	public void testRemove4() {//remove existing non-resident 
		NonResident student = new NonResident ("Lia Doe", "bA", 20);
		Roster roster = new Roster();
		roster.add(student);
		assertTrue(roster.remove(student));
	}
	
	@Test
	public void testRemove5() { //remove a non existing tristate student
		TriState student = new TriState ("John River", "eE", 20,"NY");
		Roster roster = new Roster();
		assertFalse(roster.remove(student));
	}
	
	@Test
	public void testRemove6() {//remove existing tristate student 
		TriState student = new TriState ("Jess Pierre", "me", 20,"CT");
		Roster roster = new Roster();
		roster.add(student);
		assertTrue(roster.remove(student));
	}
	
	@Test
	public void testRemove7() { //remove a non existing international student
		International student = new International ("Manu Estes", "IT", 12, true);
		Roster roster = new Roster();
		assertFalse(roster.remove(student));
	}
	
	@Test
	public void testRemove8() {//remove existing international student 
		International student = new International ("Johnny Suh", "ba", 16, false);
		Roster roster = new Roster();
		roster.add(student);
		assertTrue(roster.remove(student));
	}
	
	
	

}
