package Enrollees;

import java.util.Objects;

public class Enrollees {
    private String userID;
    private String firstName;
    private String lastName;
    private int version;
    private String insuranceCompany;

    public Enrollees(String userID, String firstName, String lastName, int version, String insuranceCompany) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.version = version;
        this.insuranceCompany = insuranceCompany;
    }

    public static int compareTwoLastNames(Enrollees e1, Enrollees e2) {
        return e1.lastName.compareTo(e2.lastName);
    }

    public static int compareTwoFirstNames(Enrollees e1, Enrollees e2) {
        return e1.firstName.compareTo(e2.firstName);
    }

    public static int compareLastAndFirstName(Enrollees e1, Enrollees e2) {
        int comp = compareTwoLastNames(e1,e2);

        if (comp == 0) {
            return compareTwoFirstNames(e1,e2);
        }
        return comp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enrollees)) return false;
        Enrollees enrollees = (Enrollees) o;
        return
                Objects.equals(getUserID(), enrollees.getUserID()) &&
                        Objects.equals(getFirstName(), enrollees.getFirstName()) &&
                        Objects.equals(getLastName(), enrollees.getLastName()) &&
                        Objects.equals(getInsuranceCompany(), enrollees.getInsuranceCompany());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserID(), getFirstName(), getLastName(), getVersion(), getInsuranceCompany());
    }

    @Override
    public String toString() {
        return "Enrollees{" +
                "userID='" + userID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", version=" + version +
                ", insuranceCompany='" + insuranceCompany + '\'' +
                '}';
    }

    public String formatForFile () {
        return lastName + ',' + firstName + ',' + insuranceCompany + ',' + userID + ',' + version;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }
}

