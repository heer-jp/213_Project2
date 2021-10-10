package project2;
/**
 * A driver class for Project2 which calls the TuitionManager class run() method to start the Tuition Manager.
 * 
 * @author Sai Maduri, Heer Patel
 *
 */

public class RunProject2 {
	/**
	 * Calls TuitionManager run() method which deals with input/output.
	 * @param args user input
	 */
    public static void main (String[] args) {
        new TuitionManager().run();
    }
}
