package model;

public class test {
    public static void main(String[] args){

        // Test Admin class
        Admin admin = new Admin("Amy",1234);
        System.out.println("Admin Name: " + admin.getName());
        System.out.println("Admin ID: " + admin.getEmployeeID());
        admin.setEmployeeID(4321);
        System.out.println("Updated Admin ID: " + admin.getEmployeeID()+ "\n");

        //Test Customer class
        Customer customer = new Customer("Nick",1235,"123 Hickory Lane", 360123456);
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Customer Address: " + customer.getCustomerAddress());
        System.out.println("Customer Phone: " + customer.getCustomerPhone());
        customer.setCustomerAddress("25 Main Street");
        System.out.println("Updated Customer Address: " + customer.getCustomerAddress() + "\n");

        // Test Menu Item class
        MenuItem item = new MenuItem("Nachos", 10.99);
        System.out.println("MenuItem Name: " + item.getName());
        System.out.println("MenuItem Price: " + item.getPrice());
        item.setName("Cheesy Nachos");
        System.out.println("Updated Menu Item: " + item.getName()+ "\n");

        //Test Order class
        Order order = new Order();
        order.addItem(item);
        System.out.println("Item has been added to the order: " + order.getTotal()+ "\n");
        System.out.println("Here is the order: ");
        order.displayOrder();
        System.out.println("\n");

        //Test Order Item class
        OrderItem orderItem = new OrderItem(item, 5);
        System.out.println("Item has been added to the order: " + orderItem.getItem());
        System.out.println("The number of items order: "+ orderItem.getQuantity());
        orderItem.setQuantity(2);
        System.out.println("The amount of items has been changed to: " + orderItem.getQuantity());
        System.out.println("The total price has been changed to: " + orderItem.getTotalPrice()+ "\n");

        //Test User class
        User user = new User("Bob", 6789);
        System.out.println("User Name: " + user.getName());
        System.out.println(user.getName()+ "'s ID number is : "+ user.getIDNumber());
        user.setName("Mary");
        System.out.println("The user's name has been updated to : " + user.getName());
        user.setIDNumber(2025);
        System.out.println("The user's ID number has been updated to : "+ user.getIDNumber()+ "\n");

    }
}

