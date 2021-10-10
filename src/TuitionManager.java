package project2;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * A class that handles input and output for the entire program.
 * Takes in command line arguments and prints out a response.
 * @author Sai Maduri, Heer Patel
 *
 */
public class TuitionManager {
    public static final int ADD_MODIFY_STUDENT_MIN_ARGUMENTS_COUNT = 3;
    public static final int ADD_MODIFY_STUDENT_ADDITIONAL_ARGUMENTS_COUNT = 1;
    public static final int ADD_MODIFY_STUDENT_MISSING_ARGUMENTS_COUNT = 2;
    public static final int REMOVE_STUDENT_ARGUMENTS_COUNT = 2;
    public static final int CALCULATE_TUITION_ARGUMENTS_COUNT = 4;
    public static final int CALCULATE_TUITION_MISSING_ARGUMENTS_COUNT = 2;
    public static final int PRINT_QUIT_CALCULATE_ARGUMENTS_COUNT = 1;
    public static final int SET_STUDY_ABROAD_ARGUMENTS_COUNT = 3;
    public static final int SET_FINANCIAL_AID_ARGUMENTS_COUNT = 3;

    /**
     *  Reads through user input and performs valid commands until terminated. 
     *  If command is valid StringTokenizer separates the user input line into tokens
     *  for readability. The appropriate method is called to perform the command.
     *  While loop terminated by Q command.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Roster roster = new Roster();
        System.out.println("Tuition Manager starts running.");
        while (scanner.hasNextLine()) {
            String inputLine = scanner.nextLine();
            StringTokenizer input = new StringTokenizer(inputLine, ",");
            if (input.hasMoreTokens()) {
                String command = input.nextToken();
                switch (command.trim()) {
                    case "AR":
                        addStudentToRoster(roster, input, "AR");
                        break;
                    case "AN":
                        addStudentToRoster(roster, input, "AN");
                        break;
                    case "AT":
                        addStudentToRoster(roster, input, "AT");
                        break;
                    case "AI":
                        addStudentToRoster(roster, input, "AI");
                        break;
                    case "R":
                        removeStudentFromRoster(roster, input);
                        break;
                    case "C":
                        calculateTuition(roster);
                        break;
                    case "T":
                        payTuition(roster, input);
                        break;
                    case "S":
                        setStudyAbroad(roster, input);
                        break;
                    case "F":
                        setFinancialAid(roster, input);
                        break;
                    case "P":
                        roster.print();
                        break;
                    case "PN":
                        roster.printByName();
                        break;
                    case "PT":
                        roster.printByPaymentDate();
                        break;
                    case "Q":
                        System.out.println("Tuition Manager terminated.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Command '" + command + "' not supported!");
                }
            }
        }
    }

    /**
     * Creates the correct student object type based on status and user input specifications.
     * Student object is to be added to the Roster class student array. If student values are valid
     * AR status creates a Resident student object type.
     * AN status creates a NonResident student object type.
     * AT status creates a TriState student object type.
     * AI status creates a International student object type.
     * If the student object is created and added to the student roster, the appropriate output is
     * printed. Student object is not added to student roster if student already exists in the 
     * student roster and the appropriate statement is output.
     * @param roster object that correlates to the array that will hold students
     * @param input user student information information 
     * @param status command given by user
     */
    private void addStudentToRoster(Roster roster, StringTokenizer input, String status) {
        String[] studentArguments = parseAddStudentArguments(input);
        if (studentArguments == null)
            return;
        Student studentToBeAdded = null;
        String name = studentArguments[0];
        String major = studentArguments[1];
        int credits = Integer.parseInt(studentArguments[2]);
        switch (status) {
            case "AR":
                studentToBeAdded = new Resident(name, major, credits);
                break;
            case "AN":
                studentToBeAdded = new NonResident(name, major, credits); // change for nonresident
                break;
            case "AT":
                if (input.countTokens() != ADD_MODIFY_STUDENT_ADDITIONAL_ARGUMENTS_COUNT) {
                    System.out.println("Missing data in command line.");
                    return;
                }
                String state = input.nextToken().toUpperCase();
                if (!checkTriStateValidity(state)) {
                    System.out.println("Not part of the tri-state area.");
                    return;
                }
                studentToBeAdded = new TriState(name, major, credits, state); // change for tristate
                break;
            case "AI":
                if (input.countTokens() != ADD_MODIFY_STUDENT_ADDITIONAL_ARGUMENTS_COUNT) {
                    System.out.println("Missing data in command line.");
                    return;
                }
                boolean studyingAbroad = Boolean.parseBoolean(input.nextToken());
                if (!checkInternationalValidity(credits, studyingAbroad)) {
                    System.out.println("International students must enroll at least 12 credits.");
                    return;
                }
                studentToBeAdded = new International(name, major, credits, studyingAbroad); // change for international
                break;
        }
        if (roster.add(studentToBeAdded)) {
            System.out.println("Student added.");
        } else {
            System.out.println("Student is already in the roster.");
        }
    }
    
    /**
     * Removes student from the Roster class array.
     * If student exist in the array it is deleted and the appropriate statement 
     * is printed. If student is not in the array the appropriate statement in printed.
     * @param roster object that correlates to the array that will hold students
     * @param input student information user input
     */
    private void removeStudentFromRoster(Roster roster, StringTokenizer input) {
        if (input.countTokens() < REMOVE_STUDENT_ARGUMENTS_COUNT) {
            System.out.println("Missing data in command line.");
            return;
        }
        String name = input.nextToken();
        String major = input.nextToken();
        Student studentToBeDeleted = new Student(name, major);
        if (roster.remove(studentToBeDeleted)) {
            System.out.println("Student removed from the roster.");
        } else {
            System.out.println("Student is not in the roster.");
        }
    }
    
    /**
     * Calls Roster class calculateTuition() method to print out the 
     * tuition due for each student in the student roster.
     * @param roster object that correlates to the array that will hold students
     */
    private void calculateTuition(Roster roster) {
        roster.calculateTuition();
        System.out.println("Calculation completed.");
    }
    
    /**
     * Allows user to pay tuition fee as long as payment amount is less than the tuition.
     * The adjusted fee is updated and the payment date is accounted for.
     * @param roster object that correlates to the array that will hold students
     * @param input student information user input
     */
    private void payTuition(Roster roster, StringTokenizer input) {
        if (input.countTokens() <= CALCULATE_TUITION_MISSING_ARGUMENTS_COUNT) {
            System.out.println("Payment amount missing.");
            return;
        }
        String name = input.nextToken();
        String major = input.nextToken();
        double paymentAmount = Double.parseDouble(input.nextToken());
        if (!checkPaymentAmountValidity(paymentAmount)) {
            System.out.println("Invalid amount.");
            return;
        }
        Date paymentDate = new Date(input.nextToken());
        if (!paymentDate.isValid()) {
            System.out.println("Payment date invalid.");
            return;
        }
        Student studentToPayTuition = roster.getStudent(new Student(name, major));
        if (studentToPayTuition == null) {
            System.out.println("Student not in the roster.");
        }
        if (!studentToPayTuition.makePayment(paymentAmount, paymentDate)) {
            System.out.println("Amount is greater than amount due.");
            return;
        }
        System.out.println("Payment applied.");
    }
    
    /**
     * Sets the payment to 0 if international student is valid and a higher value 
     * of credit hours than 12. Recalculates and updates the tuition in the student roster. 
     * @param roster object that correlates to the array that will hold students
     * @param input student information user input
     */
    public void setStudyAbroad(Roster roster, StringTokenizer input) {
        if (input.countTokens() < SET_STUDY_ABROAD_ARGUMENTS_COUNT) {
            System.out.println("Missing study abroad arguments");
            return;
        }
        String name = input.nextToken();
        String major = input.nextToken();
        boolean studyingAbroad = Boolean.parseBoolean(input.nextToken());
        Student studentToSetStudyingAbroadStatus = roster.getStudent(new Student(name, major));
        if (!(studentToSetStudyingAbroadStatus instanceof International)) {
            System.out.println("Couldn't find the international student.");
            return;
        }
        ((International) studentToSetStudyingAbroadStatus).setStudyAbroad(studyingAbroad);
        System.out.println("Tuition updated.");
    }
    
    /**
     * Validates the financial aid amount for residents - can not be negative
     * or greater than $10,000
     * @param roster object that correlates to the array that will hold students
     * @param input student information user input
     */
    public void setFinancialAid(Roster roster, StringTokenizer input) {
        if (input.countTokens() < SET_FINANCIAL_AID_ARGUMENTS_COUNT) {
            System.out.println("Missing the amount.");
            return;
        }
        String name = input.nextToken();
        String major = input.nextToken();
        double financialAidAmount = Double.parseDouble(input.nextToken());
        if (!checkFinancialAidAmountValidity(financialAidAmount)) {
            System.out.println("Invalid amount.");
            return;
        }
        Student studentToSetFinancialAidAmount = roster.getStudent(new Student(name, major));
        if (studentToSetFinancialAidAmount == null) {
            System.out.println("Student not in the roster.");
            return;
        }
        if (!(studentToSetFinancialAidAmount instanceof Resident)) {
            System.out.println("Not a resident student.");
            return;
        }
        Resident residentToSetFinancialAidAmount = (Resident)studentToSetFinancialAidAmount;
        if (residentToSetFinancialAidAmount.getCredits() < Student.MIN_FULLTIME_CREDITS) {
            System.out.println("Parttime student doesn't qualify for the award.");
            return;
        }
        if (residentToSetFinancialAidAmount.getFinancialAidStatus()) {
            System.out.println("Awarded once already.");
            return;
        }
        residentToSetFinancialAidAmount.giveFinancialAid(financialAidAmount);
        System.out.println("Tuition updated.");
    }

    /**
     * ?? Allows user to adjust student input?
     * @param input student information user input
     * @return
     */
    private String[] parseAddStudentArguments(StringTokenizer input) {
        if (input.countTokens() < ADD_MODIFY_STUDENT_MISSING_ARGUMENTS_COUNT) {
            System.out.println("Missing data in command line.");
            return null;
        } else if (input.countTokens() < ADD_MODIFY_STUDENT_MIN_ARGUMENTS_COUNT) {
            System.out.println("Credit hours missing.");
            return null;
        }
        String name = input.nextToken();
        String major = input.nextToken();
        String credits = input.nextToken();
        if (!checkMajorValidity(major))
            return null;
        if (!checkCreditsValidity(credits))
            return null;
        return new String[] { name, major, credits };
    }
    
    /**
     * Validates credit input based on student credit restrictions.
     * @param credits given by user input of student information
     * @return true if credits are a valid value, false if they are invalid.
     */
    private boolean checkCreditsValidity(String credits) {
        if (!(credits.matches("-?\\d+"))) {
            System.out.println("Invalid credit hours.");
            return false;
        } else if (((Integer.valueOf(credits) < 0))) {
            System.out.println("Credit hours cannot be negative.");
            return false;
        } else if (((Integer.valueOf(credits) < Student.MIN_CREDITS))) {
            System.out.println("Minimum credit hours is 3.");
            return false;
        } else if (((Integer.valueOf(credits) > Student.MAX_CREDITS))) {
            System.out.println("Credit hours exceed the maximum 24.");
            return false;
        }
        return true;
    }
    
    /**
     * Validates if student is a TriState students based on residence in NY or CT. 
     * @param state
     * @return true if states are NY or CT, false if not.
     */
    private boolean checkTriStateValidity(String state) {
        return state.equals("NY") || state.equals("CT");
    }
    
    /**
     * Methods checks if International student is part of the study abroad program.
     * @param credits given by user input of student information
     * @param studyingAbroad given by user input of student information
     * @return true if student is an international student part of the study abroad program, 
     * otherwise returns false.
     */
    private boolean checkInternationalValidity(int credits, boolean studyingAbroad) {
        if (studyingAbroad)
            return true;
        return credits >= International.MIN_CREDITS;
    }

    /**
     * Validates if student major is a valid major.
     * @param major given by user input of student information
     * @return false if major is not valid, true if major is valid
     */
    private boolean checkMajorValidity(String major) {
        if (major.equalsIgnoreCase("CS") || major.equalsIgnoreCase("IT") || major.equalsIgnoreCase("BA")
                || major.equalsIgnoreCase("EE") || major.equalsIgnoreCase("ME"))
            return true;
        System.out.println("'" + major + "' is not a valid major.");
        return false;
    }
    
    /**
     * Checks to see if payment amount entered by the user is valid - greater than 0.
     * @param paymentAmount entered by the user 
     * @return true if the payment amount is valid, false if invalid
     */
    private boolean checkPaymentAmountValidity(double paymentAmount) {
        return paymentAmount > 0;
    }
    
    /**
     * Checks to see if the financial aid amount for a student is valid by being greater 
     * than 0, but less than $10,000.
     * @param financialAidAmount
     * @return true if financial aid is valid, false if invalid
     */
    private boolean checkFinancialAidAmountValidity(double financialAidAmount) {
        return financialAidAmount > 0 && financialAidAmount < Resident.MAX_FINANCIAL_AID_AMOUNT;
    }
}
