package project2;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * A class that stores a date based off of an input string in mm/dd/yyyy format.
 * The month, day, and year is stored in their respective variables.
 * @author Sai Maduri, Heer Patel
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int FEBRUARY_LEAP_YEAR_MONTH_END = 29;
    public static final int MONTH_START = 1;
    public static final int MONTH_END = 31;
    public static final int CURRENT_YEAR = 2021;

    /**
     * Creates Date object using user input date.
     * @param date entered by user.
     */
    public Date(String date) {
        StringTokenizer st = new StringTokenizer(date, "/");
        month = Integer.parseInt(st.nextToken());
        day = Integer.parseInt(st.nextToken());
        year = Integer.parseInt(st.nextToken());
    }

    /**
     * Creates date object using today's date.
     */
    public Date() {
        Calendar rightNow = Calendar.getInstance();
        month = rightNow.get(Calendar.MONTH) + 1;
        day = rightNow.get(Calendar.DAY_OF_MONTH);
        year = rightNow.get(Calendar.YEAR);
    }

    /**
     * Creates date object using pre-existing date object information.
     * @param date object created from user input.
     */
    public Date(Date date) {
        month = date.getMonth();
        day = date.getDay();
        year = date.getYear();
    }

    // I DO NOT THINK THIS IS A REQUIREMENT - CAUSES FAILURES IN JUNIT TESTER
    /**
     * Checks for invalid date by comparing user input date to current date. 
     * Invalid if user input date passes current date.
     */
    @Override
    public int compareTo(Date date) {
        // TODO Auto-generated method stub
        if (date.year < this.year) {
            return -1;
        } else {
            if (date.year == this.year) {
                if (date.month < this.month) {
                    return -1;
                } else {
                    if (date.month == this.month) {
                        if (date.day < this.day) {
                            return -1;
                        } else {
                            return 1;
                        }
                    } else {
                        return 1;
                    }
                }
            } else {
                return 1;
            }
        }
    }

    /**
     * Method returns date object of today's date.
     * @return new date object.
     */
    public Date today() {
        return new Date();
    }

    /**
     * Checks if date is valid. 
     * For leap year dates calls isLeapYear method to validate the date.
     * @return false if date is invalid, true if date is valid
     */
    public boolean isValid() {
        Calendar paymentDate = Calendar.getInstance();
        paymentDate.set(year, month - 1, day);
        Calendar currentDate = Calendar.getInstance();
        if (paymentDate.after(currentDate) || year < CURRENT_YEAR || day > MONTH_END || day < MONTH_START)
            return false;
        switch (month - 1) {
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.OCTOBER:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.DECEMBER:
                return day <= MONTH_END;
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                return day != MONTH_END;
            case Calendar.FEBRUARY:
                if (day > FEBRUARY_LEAP_YEAR_MONTH_END) {
                    return false;
                } else if (isLeapYear()) {
                    return day <= FEBRUARY_LEAP_YEAR_MONTH_END;
                } else {
                    return day < FEBRUARY_LEAP_YEAR_MONTH_END;
                }
            default:
                return false;
        }
    }

    /**
     * Verifies if the user date is a valid leap year date.
     * @return true if date is valid a leap year date, false if date is an invalid
     *         leap year date.
     */
    public boolean isLeapYear() {
        if (year % QUATERCENTENNIAL == 0) {
            return false;
        } else if (year % QUADRENNIAL == 0 || year % CENTENNIAL == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * String formed by combining month, day, and year into date format.
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return month + "/" + day + "/" + year;
    }

    /**
     * Returns date object's day.
     * @return day in string form.
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns date object/s month.
     * @return month in string form.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Returns date object's year.
     * @return year in string form.
     */
    public int getYear() {
        return year;
    }

    /**
     * Main method holds test mains.
     * @param args test mains.
     */
    public static void main(String[] args) {
        // Test Case #1 - checking an invalid date format
        System.out.println("Running Test Case #1a:");
        Date testCase1a = new Date("31/1/2021");
        if (!testCase1a.isValid()) {
            System.out.println("Test Case #1a, checking a date with dd/mm/yy format. Passed");
        } else {
            System.out.println("Test Case #1a, checking a date with dd/mm/yy format. Failed");
        }

        System.out.println("Running Test Case #1b:");
        Date testCase1b = new Date("12/1/2021");
        if (testCase1b.isValid()) {
            System.out.println("Test Case #1a, checking a date with mm/dd/yy format. Passed");
        } else {
            System.out.println("Test Case #1a, checking a date with mm/dd/yy format. Failed");
        }

        // Test Case #2 - checking an invalid month
        System.out.println("Running Test Case #2a:");
        Date testCase2a = new Date("13/1/2021");
        if (!testCase2a.isValid()) {
            System.out.println("Test Case #2a, checking a date with invalid month. Passed");
        } else {
            System.out.println("Test Case #2a, checking a date with invalid month. Failed");
        }

        System.out.println("Running Test Case #2b:");
        Date testCase2b = new Date("12/1/2021");
        if (testCase2b.isValid()) {
            System.out.println("Test Case #2b, checking a date with valid month. Passed");
        } else {
            System.out.println("Test Case #2b, checking a date with valid month. Failed");
        }

        // Test Case #3 - checking 2/29 on a non-leap year
        System.out.println("Running Test Case #3a:");
        Date testCase3a = new Date("2/29/2021");
        if (!testCase3a.isValid()) {
            System.out.println("Test Case #3a, checking a date with 2/29 on a non-leap year. Passed");
        } else {
            System.out.println("Test Case #3a, checking a date with 2/29 on a non-leap year. Failed");
        }

        System.out.println("Running Test Case #3b:");
        Date testCase3b = new Date("2/29/2024");
        if (testCase3b.isValid()) {
            System.out.println("Test Case #3b, checking a date with 2/29 on a leap year. Passed");
        } else {
            System.out.println("Test Case #3b, checking a date with 2/29 on a leap year. Failed");
        }

        // Test Case #4 - checking a date before 2021
        System.out.println("Running Test Case #4a:");
        Date testCase4a = new Date("12/31/2020");
        if (!testCase4a.isValid()) {
            System.out.println("Test Case #4a, checking a date before 2021. Passed");
        } else {
            System.out.println("Test Case #4a, checking a date before 2021. Failed");
        }

        System.out.println("Running Test Case #4b:");
        Date testCase4b = new Date("1/1/2021");
        if (testCase4b.isValid()) {
            System.out.println("Test Case #4b, checking a date after 2021. Passed");
        } else {
            System.out.println("Test Case #4b, checking a date after 2021. Failed");
        }

        // Test Case #6 - checking a date with an invalid day
        System.out.println("Running Test Case #6a:");
        Date testCase6a = new Date("3/32/2021");
        if (!testCase6a.isValid()) {
            System.out.println("Test Case #6a, checking a date with an invalid day. Passed");
        } else {
            System.out.println("Test Case #6a, checking a date with an invalid day. Failed");
        }

        System.out.println("Running Test Case #6b:");
        Date testCase6b = new Date("3/30/2021");
        if (testCase6b.isValid()) {
            System.out.println("Test Case #6b, checking a date with a valid day. Passed");
        } else {
            System.out.println("Test Case #6b, checking a date with a valid day. Failed");
        }

        // Test Case #7 - checking a date with a day of 31 in a 30-day month
        System.out.println("Running Test Case #7a:");
        Date testCase7a = new Date("4/31/2021");
        if (!testCase7a.isValid()) {
            System.out.println("Test Case #7a, checking a date with a day of 31 in a 30-day month. Passed");
        } else {
            System.out.println("Test Case #7a, checking a date with a day of 31 in a 30-day month. Failed");
        }

        System.out.println("Running Test Case #7b:");
        Date testCase7b = new Date("5/31/2021");
        if (testCase7b.isValid()) {
            System.out.println("Test Case #7b, checking a date with a day of 31 in a 31-day month. Passed");
        } else {
            System.out.println("Test Case #7b, checking a date with a day of 31 in a 31-day month. Failed");
        }
    }
}