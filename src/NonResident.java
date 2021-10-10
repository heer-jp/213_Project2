package project2;

import java.text.DecimalFormat;

public class NonResident extends Student {

    public static final int PARTTIME_CREDIT_HOUR_COST = 966;
    public static final int FULLTIME_TUITION_COST = 29737;

    /**
     * Creates NonResident student object type by calling super class variable.
     * @param name String given by user input - student's name
     * @param major major given by user input - student's major
     * @param credits int given by user - student's credit
     */
    public NonResident(String name, String major, int credits) {
        super(name, major, credits);
    }
    
    /**
     * ??Sets up tuition calculations for when calculation method is called
     */
    @Override
    public void tuitionDue() {
        // TODO Auto-generated method stub
        int credits = getCredits();
        int enrollmentTypeCost = 0;
        double universityFee = 0;
        int additionalCreditsCost = credits > CREDIT_EXCEED_LIMIT
                ? (credits - CREDIT_EXCEED_LIMIT) * PARTTIME_CREDIT_HOUR_COST
                : 0;

        if (credits >= MIN_FULLTIME_CREDITS) {
            enrollmentTypeCost = FULLTIME_TUITION_COST;
            universityFee = UNIVERSITY_FEE;
        } else {
            enrollmentTypeCost = credits * PARTTIME_CREDIT_HOUR_COST;
            universityFee = UNIVERSITY_FEE * PARTTIME_FEE_RATE;
        }

        setTuition(enrollmentTypeCost + universityFee + additionalCreditsCost);
    }
    
    /**
     * Converts student information into a string printout.
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        if (this.getLastPaymentDate() == null) {
            return getProfile().toString() + ":" + getCredits() + " credit hours:tuition due:" + new DecimalFormat("#,##0.00").format(getTuition())
                    + ":total payment:" + new DecimalFormat("#,##0.00").format(getLastPaymentAmount()) + ":last payment date: " + "--/--/--" + ":non-resident";
        }
        return getProfile().toString() + ":" + getCredits() + " credit hours:tuition due:" + new DecimalFormat("#,##0.00").format(getTuition())
                + ":total payment:" + new DecimalFormat("#,##0.00").format(getLastPaymentAmount()) + ":last payment date: " + getLastPaymentDate().toString()
                + ":non-resident";

    }
}
