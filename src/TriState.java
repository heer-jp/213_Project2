package project2;

import java.text.DecimalFormat;

public class TriState extends NonResident {

    private String state;

    public static final int NY_TRISTATE_DISCOUNT = 4000;
    public static final int CT_TRISTATE_DISCOUNT = 5000;

    /**
     * Creates TriState student object type
     * @param name String given by user input - student's name
     * @param major major given by user input - student's major
     * @param credits int given by user - student's credit
     * @param state boolean given by user - if student is in the 
     * study abroad program
     */
    public TriState(String name, String major, int credits, String state) {
        super(name, major, credits);
        this.state = state;
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
        int tristateDiscount = 0;
        int additionalCreditsCost = credits > CREDIT_EXCEED_LIMIT
                ? (credits - CREDIT_EXCEED_LIMIT) * PARTTIME_CREDIT_HOUR_COST
                : 0;

        if (credits >= MIN_FULLTIME_CREDITS) {
            enrollmentTypeCost = FULLTIME_TUITION_COST;
            universityFee = UNIVERSITY_FEE;
            if (state.equals("CT")) {
                tristateDiscount = CT_TRISTATE_DISCOUNT;
            } else {
                tristateDiscount = NY_TRISTATE_DISCOUNT;
            }
        } else {
            enrollmentTypeCost = credits * PARTTIME_CREDIT_HOUR_COST;
            universityFee = UNIVERSITY_FEE * PARTTIME_FEE_RATE;
        }

        setTuition(enrollmentTypeCost + universityFee + additionalCreditsCost - tristateDiscount);
    }
    
    /**
     * Converts student information into a string printout.
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        if (this.getLastPaymentDate() == null) {
            return getProfile().toString() + ":" + getCredits() + " credit hours:tuition due:" + new DecimalFormat("#,##0.00").format(getTuition())
                    + ":total payment:" + new DecimalFormat("#,##0.00").format(getLastPaymentAmount()) + ":last payment date: " + "--/--/--" + ":non-resident(tri-state):" + state;
        }
        return getProfile().toString() + ":" + getCredits() + " credit hours:tuition due:" + new DecimalFormat("#,##0.00").format(getTuition())
                + ":total payment:" + new DecimalFormat("#,##0.00").format(getLastPaymentAmount()) + ":last payment date: " + getLastPaymentDate().toString()
                + ":non-resident(tri-state):" + state;

    }

}
