public class User {

    /**
     *Initializes variables for name and ID number.
     */
    protected String name;
    protected int IDNumber;

    /**
     * Constructs a user using the following parameters.
     * @param name of user
     * @param IDNumber of user
     */
    public User(String name, int IDNumber) {
        this.name = name;
        this.IDNumber = IDNumber;
    }

    /**
     * Gets the name of user selected
     * @return name of user
     */
    public String getName() {
        return name;
    }

    /**
     * Gets ID number of user selected
     * @return ID number of currently selected user
     */
    public int getIDNumber() {
        return IDNumber;
    }

    /**
     * Sets name to current user's name selected
     * @param name of current user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets IDNumber to current user's ID number
     * @param IDNumber of current user selected
     */
    public void setIDNumber(int IDNumber) {
        this.IDNumber = IDNumber;
    }

}

/**
 *
 */
class Admin extends User{
    /**
     * Initializes variables for AdminID of user.
     */
    private int employeeID;

    /**
     * Constructs an employee using the following parameters
     * @param name of Admin
     * @param employeeID of Admin
     */
    public Admin(String name, int employeeID){
        super(name, employeeID);

    }

    /**
     * Gets the employee ID of current Admin
     * @return current employee's ID
     */
    public int getEmployeeID(){
        return employeeID;
    }


    /**
     * Sets employee ID to current employee's ID
     * @param employeeID of current employee selected
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
}



class Customer extends User{


    /**
     *Initializes customerID variable.
     */
    private int customerID;
    private final String customerAddress;
    private final int customerPhone;
    /**
     * This method constructs a customer user
     * @param name of the customer
     * @param customerID for the current customer
     * @param customerAddress for the current customer
     * @param customerPhone for the current customer
     */
    public Customer(String name, int customerID, String customerAddress, int customerPhone) {
        super(name, customerID);
        this.customerAddress = customerAddress;
        this.customerID = customerID;
        this.customerPhone = customerPhone;
    }

    /**
     * Gets the ID of te current student selected
     * @return customerID
     */
    public int getCustomerID(){
        return customerID;
    }

    /**
     * Sets customerID to ID of current customer selected
     * @param customerID of current customer
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}

