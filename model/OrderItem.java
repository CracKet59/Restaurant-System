package model;

// This class keeps track of one menu item and how many times it was ordered
public class OrderItem {
    private MenuItem item; // The actual food item
    private int quantity;  // How many the customer wants

    // Set it up with the item and how many of it were ordered
    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    // Get the menu item
    public MenuItem getItem() {
        return item;
    }

    // Get how many were ordered
    public int getQuantity() {
        return quantity;
    }

    // Update the quantity (like if the person orders more)
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Find the total price for this item based on the quantity
    public double getTotalPrice() {
        return item.getPrice() * quantity;
    }

    // Show the item name, quantity, and total price when printing
    @Override
    public String toString() {
        return item.getName() + " x" + quantity + " - $" + getTotalPrice();
    }
}