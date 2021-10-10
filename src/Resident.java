package project2;

import java.text.DecimalFormat;

public class Resident extends Student {
    private boolean hasFinancialAid;
    private double financialAidAmount;

    public static final int PARTTIME_CREDIT_HOUR_COST = 404;
    public static final int FULLTIME_TUITION_COST = 12536;
    
    public static final int MAX_FINANCIAL_AID_AMOUNT = 10000;

    /**
     * Creates Resident student object type by calling super class variable.
     * @param name String given by user input - student's name
     * @param major major given by user input - student's major
     * @param credits int given by user - student's credit
     */
    public Resident(String name, String major, int credits) {
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
     * Checks if resident receives financial aid.
     * @param financialAid taken from Tuition Manger class
     * @return true if student receives financial aid, false if not
     */
    public boolean giveFinancialAid(double financialAid) {
        if (hasFinancialAid)
            return false;
        setTuition(getTuition() - financialAid);
        hasFinancialAid = true;
        financialAidAmount = financialAid;
        return true;
    }

    public boolean getFinancialAidStatus() {
        return hasFinancialAid;
    }

    /**
     * Converts student information into a string printout.
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        if (this.getLastPaymentDate() == null) {
            if (hasFinancialAid) {
                return getProfile().toString() + ":" + getCredits() + " credit hours:tuition due:" + new DecimalFormat("#,##0.00").format(getTuition())
                        + ":total payment:" + new DecimalFormat("#,##0.00").format(getLastPaymentAmount()) + ":last payment date: " + "--/--/--" + ":resident:financial aid $" + new DecimalFormat("#,##0.00").format(financialAidAmount);
            }
            return getProfile().toString() + ":" + getCredits() + " credit hours:tuition due:" + new DecimalFormat("#,##0.00").format(getTuition())
                    + ":total payment:" + new DecimalFormat("#,##0.00").format(getLastPaymentAmount()) + ":last payment date: " + "--/--/--" + ":resident";
        }
        if (hasFinancialAid) {
            return getProfile().toString() + ":" + getCredits() + " credit hours:tuition due:" + new DecimalFormat("#,##0.00").format(getTuition())
                    + ":total payment:" + new DecimalFormat("#,##0.00").format(getLastPaymentAmount()) + ":last payment date: " + getLastPaymentDate().toString() + ":resident:financial aid $" + new DecimalFormat("#,##0.00").format(financialAidAmount);
        }
        return getProfile().toString() + ":" + getCredits() + " credit hours:tuition due:" + new DecimalFormat("#,##0.00").format(getTuition())
                + ":total payment:" + new DecimalFormat("#,##0.00").format(getLastPaymentAmount()) + ":last payment date: " + getLastPaymentDate().toString()
                + ":resident";

    }
}
