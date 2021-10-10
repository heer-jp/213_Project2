package project2;

public class Profile {
    private String name;
    private Major major;

    /**
     * Creates profile object type that assigns the appropriate major enum to 
     * the student based off of user input.
     * @param String name given by user - student's name
     * @param major String major given by user - student's major
     */
    public Profile(String name, String major) {
        this.name = name;

        switch (major.toUpperCase()) {
            case "CS":
                this.major = Major.CS;
                break;
            case "IT":
                this.major = Major.IT;
                break;
            case "BA":
                this.major = Major.BA;
                break;
            case "EE":
                this.major = Major.EE;
                break;
            case "ME":
                this.major = Major.ME;
                break;
            default:
                this.major = null;
        }
    }

    /**
     * Converts String name and major in one string with name : major format.
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return name + ":" + major;
    }

    /**
     * Returns String student name.
     * @return String student name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the student major type.
     * @return enum major 
     */
    public Major getMajor() {
        return major;
    }
    
    /**
     * Compares profile objects.
     * Returns true if the profile objects are equal.
     * Returns false if they objects are not equal.
     */
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (!(obj instanceof Profile))
            return false;

        Profile tmp = (Profile) obj;
        return name.equals(tmp.getName()) && major == tmp.getMajor();
    }
}
