package model;

/**
 * Represents a customer user with address and phone number.
 * Customers can make reservations and place/pay for orders but do not log in.
 */
public class Customer extends User {

    /**
     * Initializes variables for customer address and phone number.
     */
    private String customerAddress;
    private int customerPhone;

    /**
     * Constructs a customer using the following parameters.
     * @param name the customer's name
     * @param customerID the customer's ID
     * @param customerAddress the customer's address
     * @param customerPhone the customer's phone number
     */
    public Customer(String name, int customerID, String customerAddress, int customerPhone) {
        super(name, customerID);
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
    }

    /**
     * Gets the ID of the current customer selected.
     * @return customerID
     */
    public int getCustomerID() {
        return IDNumber;
    }

    /**
     * Sets customerID to ID of current customer selected.
     * @param customerID of current customer
     */
    public void setCustomerID(int customerID) {
        this.IDNumber = customerID;
    }

    /**
     * Gets the address of the current customer.
     * @return customer address
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * Sets the address of the current customer.
     * @param customerAddress of current customer
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * Gets the phone number of the current customer.
     * @return customer phone number
     */
    public int getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Sets the phone number of the current customer.
     * @param customerPhone of current customer
     */
    public void setCustomerPhone(int customerPhone) {
        this.customerPhone = customerPhone;
    }
}
