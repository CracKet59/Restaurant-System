package model;

import java.util.ArrayList;

// This class holds the full order with everything the customer picked
public class Order {
    private ArrayList<OrderItem> items; // List of all the items in the order

    // Start with an empty order
    public Order() {
        this.items = new ArrayList<>();
    }

    // Add a menu item to the order
    // If it’s already there, just add 1 to the quantity
    // If it’s not there, add it with quantity 1
    public void addItem(MenuItem item) {
        for (OrderItem orderItem : items) {
            if (orderItem.getItem().getName().equals(item.getName())) {
                int newQuantity = orderItem.getQuantity() + 1;
                orderItem.setQuantity(newQuantity);
                return;
            }
        }
        items.add(new OrderItem(item, 1));
    }

    // Get the full total for everything in the order
    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    // Print everything the customer ordered and the total
    public void displayOrder() {
        System.out.println("Your Order:");
        for (OrderItem item : items) {
            System.out.println("- " + item);
        }
        System.out.println("Total: $" + getTotal());
    }
}