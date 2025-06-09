package model;

/**
 * Represents an admin user who has an employee ID.
 * Only Admins can log in, access and modify menu data.
 */
public class Admin extends User {

    /**
     * Constructs an admin using the following parameters.
     * @param name of Admin
     * @param employeeID of Admin
     */
    public Admin(String name, int employeeID) {
        super(name, employeeID); // employeeID stored as IDNumber
    }

    /**
     * Gets the employee ID of current Admin.
     * @return current employee's ID
     */
    public int getEmployeeID() {
        return IDNumber;
    }

    /**
     * Sets employee ID to current employee's ID.
     * @param employeeID of current employee selected
     */
    public void setEmployeeID(int employeeID) {
        this.IDNumber = employeeID;
    }
}
