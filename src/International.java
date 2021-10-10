package project2;

import java.text.DecimalFormat;

public class International extends NonResident{

    private boolean studyingAbroad;

    public static final int ADDITIONAL_FEE = 2650;
    public static final int MIN_CREDITS = 12;
    
    /**
     * Creates International student object type by calling super class variable.
     * @param name String given by user input - student's name
     * @param major major given by user input - student's major
     * @param credits int given by user - student's credit
     * @param studyingAbroad boolean given by user - if student is in the
     * study abroad program
     */
    public International(String name, String major, int credits, boolean studyingAbroad) {
        super(name, major, credits);
        this.studyingAbroad = studyingAbroad;
    }

    /**
     * ??Sets up tuition calculations for when calculation method is called
     */
    @Override
    public void tuitionDue() {
        // TODO Auto-generated method stub
        int enrollmentTypeCost = studyingAbroad ? 0 : FULLTIME_TUITION_COST;
        int additionalCreditsCost = getCredits() > CREDIT_EXCEED_LIMIT
                ? (getCredits() - CREDIT_EXCEED_LIMIT) * PARTTIME_CREDIT_HOUR_COST
                : 0;
        setTuition(enrollmentTypeCost + UNIVERSITY_FEE + ADDITIONAL_FEE + additionalCreditsCost);
    }
    
    /**
     * ??????? Modify international student to be a part of the study abroad program.
     * @param studyingAbroad variable given by user
     */
    public void setStudyAbroad(boolean studyingAbroad) {
        if (this.studyingAbroad == studyingAbroad)
            return;
        this.studyingAbroad = studyingAbroad;
        if (studyingAbroad && getCredits() > MIN_CREDITS)
            setCredits(MIN_CREDITS);
        tuitionDue();
        setLastPaymentDate(null);
        setLastPaymentAmount(-getLastPaymentAmount());
    }
    
    /**
     * Converts student information into a string printout.
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        if (this.getLastPaymentDate() == null) {
            if (studyingAbroad) {
                return getProfile().toString() + ":" + getCredits() + " credit hours:tuition due:" + new DecimalFormat("#,##0.00").format(getTuition())
                        + ":total payment:" + new DecimalFormat("#,##0.00").format(getLastPaymentAmount()) + ":last payment date: " + "--/--/--" + ":non-resident:international:study abroad";
            }
            return getProfile().toString() + ":" + getCredits() + " credit hours:tuition due:" + new DecimalFormat("#,##0.00").format(getTuition())
                    + ":total payment:" + new DecimalFormat("#,##0.00").format(getLastPaymentAmount()) + ":last payment date: " + "--/--/--"
                    + ":non-resident:international";
        } else {
            if (studyingAbroad) {
                return getProfile().toString() + ":" + getCredits() + " credit hours:tuition due:" + new DecimalFormat("#,##0.00").format(getTuition())
                        + ":total payment:" + new DecimalFormat("#,##0.00").format(getLastPaymentAmount()) + ":last payment date: " + getLastPaymentDate().toString() + ":non-resident:international:study abroad";
            }
            return getProfile().toString() + ":" + getCredits() + " credit hours:tuition due:" + new DecimalFormat("#,##0.00").format(getTuition())
                    + ":total payment:" + new DecimalFormat("#,##0.00").format(getLastPaymentAmount()) + ":last payment date: " + getLastPaymentDate().toString()
                    + ":non-resident:international";
        }
    }
}
