package model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Main application class for the restaurant system GUI.
 * Customers can view menu and make reservations without logging in.
 * Admins can log in and manage menu items (add/edit/delete).
 */
public class Restaurant extends JFrame implements ActionListener {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private JPanel adminPanel;  // Admin menu management panel

    /**
     * Constructor that sets up the main GUI layout.
     */
    public Restaurant() {
        setTitle("Taste Heaven");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Font titleFont = new Font("Times New Roman", Font.BOLD, 24);
        Font bodyFont = new Font("Arial", Font.PLAIN, 16);
        Color primary = new Color(60, 63, 65);
        Color accent = new Color(244, 180, 0);

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        navPanel.setBackground(primary);
        String[] sections = {"About Us", "Menu", "Reservation", "Payment", "Login"};
        for (String section : sections) {
            JButton button = new JButton(section);
            button.setFocusPainted(false);
            button.setBackground(accent);
            button.setForeground(Color.BLACK);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setActionCommand(section);
            button.addActionListener(this);
            navPanel.add(button);
        }

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        contentPanel.add(createAboutUsPanel(titleFont, bodyFont), "About Us");
        contentPanel.add(new JScrollPane(createMenuPanel(titleFont, bodyFont)), "Menu");
        contentPanel.add(createReservationPanel(titleFont, bodyFont), "Reservation");
        contentPanel.add(createLoginPanel(titleFont, bodyFont), "Login");
        contentPanel.add(createPaymentPanel(titleFont, bodyFont), "Payment");


        // Add Admin Menu Management Panel (initially hidden)
        adminPanel = createAdminPanel();
        contentPanel.add(adminPanel, "AdminPanel");

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        topPanel.add(navPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        cardLayout.show(contentPanel, "About Us");
    }

    private JPanel createPaymentPanel(Font titleFont, Font bodyFont) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));
        panel.setBackground(new Color(255, 250, 240)); // Light beige

        JLabel title = new JLabel("💳 Payment");
        title.setFont(titleFont);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(153, 51, 0));
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));

        JLabel instructions = new JLabel("Please review your order and enter your details below:");
        instructions.setFont(new Font("Serif", Font.ITALIC, 16));
        instructions.setForeground(new Color(90, 90, 90));
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(instructions);
        panel.add(Box.createVerticalStrut(25));

        JTextArea orderSummary = new JTextArea(6, 30);
        orderSummary.setFont(new Font("Monospaced", Font.PLAIN, 14));
        orderSummary.setEditable(false);
        orderSummary.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        updateCartDisplay(); // in case cartDisplay is reused
        panel.add(orderSummary);
        panel.add(Box.createVerticalStrut(20));

        // Name field
        panel.add(createBodyLabel("Full Name:", bodyFont));
        JTextField nameField = new JTextField();
        panel.add(nameField);
        panel.add(Box.createVerticalStrut(10));

        // Card field
        panel.add(createBodyLabel("Card Number:", bodyFont));
        JTextField cardField = new JTextField();
        panel.add(cardField);
        panel.add(Box.createVerticalStrut(20));

        JButton payButton = new JButton("Confirm Payment");
        payButton.setFont(new Font("Arial", Font.BOLD, 14));
        payButton.setBackground(new Color(153, 204, 255));
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        payButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String card = cardField.getText().trim();

            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Your cart is empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (name.isEmpty() || card.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter your name and card number.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "Payment successful! 🍽️ Thank you for your order, " + name + "!");
                cart.clear();
                orderSummary.setText("");
                nameField.setText("");
                cardField.setText("");
                updateCartDisplay();
            }
        });

        panel.add(payButton);

        // Update the summary field whenever payment tab is shown
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                orderSummary.setText("");
                double total = 0;
                for (String[] item : cart) {
                    orderSummary.append(item[0] + " — $" + item[1] + "\n");
                    total += Double.parseDouble(item[1]);
                }
                if (!cart.isEmpty()) {
                    orderSummary.append("\nTotal: $" + String.format("%.2f", total));
                }
            }
        });

        return panel;
    }

    /**
     * Creates header with title and icon.
     */
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(new Color(255, 240, 200));
        header.setBorder(new EmptyBorder(20, 10, 10, 10));

        JLabel icon = new JLabel("\uD83C\uDF7D️");
        icon.setFont(new Font("Serif", Font.PLAIN, 36));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("Taste Heaven");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        titleLabel.setForeground(new Color(244, 180, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(icon);
        header.add(titleLabel);

        return header;
    }

    /**
     * Handles switching between views.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        cardLayout.show(contentPanel, e.getActionCommand());
    }

    /**
     * Creates the About Us page panel.
     */
    private JPanel createAboutUsPanel(Font titleFont, Font bodyFont) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(40, 60, 40, 60)); // More breathing room
        panel.setBackground(new Color(255, 250, 240)); // Soft beige

        // Title
        JLabel titleLabel = new JLabel("🍽️ Welcome to Taste Heaven 🍽️");
        titleLabel.setFont(titleFont);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(153, 51, 0));
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(25));

        // Subheading
        JLabel subheading = new JLabel("Where every meal feels like home.");
        subheading.setFont(new Font("Serif", Font.ITALIC, 18));
        subheading.setAlignmentX(Component.CENTER_ALIGNMENT);
        subheading.setForeground(new Color(100, 100, 100));
        panel.add(subheading);
        panel.add(Box.createVerticalStrut(30));

        // Body Content with spacing
        String[] lines = {
                "✨ Fresh handcrafted meals made with love and locally sourced ingredients.",
                "🌿 A cozy ambiance where family, friends, and flavors come together.",
                "👨‍🍳 Inspired by global cuisines, made with passion by our chefs.",
                "🎉 Perfect for romantic dinners, family outings, and casual get-togethers."
        };
        for (String line : lines) {
            panel.add(createBodyLabel(line, bodyFont));
            panel.add(Box.createVerticalStrut(15));
        }


        panel.add(Box.createVerticalStrut(25));
        JLabel signature = new JLabel("We can't wait to serve you!");
        signature.setFont(new Font("Times New Roman", Font.BOLD, 18));
        signature.setAlignmentX(Component.CENTER_ALIGNMENT);
        signature.setForeground(new Color(180, 100, 0));
        panel.add(signature);

        return panel;
    }

    /**
     * Creates the customer Menu panel (view-only).
     */
    private java.util.List<String[]> cart = new ArrayList<String[]>();
    private JTextArea cartDisplay;

    private JScrollPane createMenuPanel(Font titleFont, Font bodyFont) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));
        panel.setBackground(new Color(255, 250, 240)); // Light beige background

        JLabel titleLabel = new JLabel("🍽️ Our Menu 🍽️");
        titleLabel.setFont(titleFont);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(153, 51, 0));
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));

        JLabel intro = new JLabel("Explore our chef-curated dishes below!");
        intro.setFont(new Font("Serif", Font.ITALIC, 16));
        intro.setAlignmentX(Component.CENTER_ALIGNMENT);
        intro.setForeground(new Color(90, 90, 90));
        panel.add(intro);
        panel.add(Box.createVerticalStrut(25));

        // Cart display panel on the side
        cartDisplay = new JTextArea(8, 30);
        cartDisplay.setEditable(false);
        cartDisplay.setFont(new Font("Monospaced", Font.PLAIN, 14));
        cartDisplay.setBorder(BorderFactory.createLineBorder(new Color(220, 200, 180)));

        cartDisplay.setMaximumSize(new Dimension(500, 160));
        cartDisplay.setPreferredSize(new Dimension(500, 160));
        cartDisplay.setMinimumSize(new Dimension(500, 160));

        updateCartDisplay(); // show total and items
        panel.add(cartDisplay);
        panel.add(Box.createVerticalStrut(15));

        // Menu items
        String[][] menuItems = {
                {"🍕 Margherita Pizza", "12", "Classic pizza with tomatoes, basil & mozzarella."},
                {"🍔 Classic Burger", "10", "Juicy beef patty with lettuce, tomato, and special sauce."},
                {"🥗 Caesar Salad", "9", "Fresh romaine, croutons, parmesan & Caesar dressing."},
                {"🍝 Spaghetti Bolognese", "13", "Rich meat sauce over perfectly cooked pasta."},
                {"🍣 Sushi Platter", "18", "An assortment of fresh sushi and sashimi."},
                {"🍗 Grilled Chicken", "15", "Marinated chicken served with vegetables and rice."}
        };

        for (String[] item : menuItems) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BorderLayout());
            itemPanel.setBackground(new Color(255, 255, 250));
            itemPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(244, 180, 0), 1),
                    new EmptyBorder(10, 10, 10, 10)
            ));

            JLabel name = new JLabel(item[0] + " — $" + item[1]);
            name.setFont(bodyFont);

            JLabel desc = new JLabel(item[2]);
            desc.setFont(new Font("Arial", Font.PLAIN, 13));
            desc.setForeground(new Color(100, 100, 100));

            JButton addToCartBtn = new JButton("Add to Cart");
            addToCartBtn.setFont(new Font("Arial", Font.BOLD, 12));
            addToCartBtn.setBackground(new Color(244, 180, 0));
            addToCartBtn.setFocusPainted(false);
            addToCartBtn.addActionListener(e -> {
                cart.add(new String[]{item[0], item[1]});
                updateCartDisplay();
            });

            JPanel centerPanel = new JPanel(new BorderLayout());
            centerPanel.setOpaque(false);
            centerPanel.add(name, BorderLayout.NORTH);
            centerPanel.add(desc, BorderLayout.CENTER);

            itemPanel.add(centerPanel, BorderLayout.CENTER);
            itemPanel.add(addToCartBtn, BorderLayout.EAST);

            panel.add(itemPanel);
            panel.add(Box.createVerticalStrut(10));
        }

        // Checkout Button
        JButton checkoutBtn = new JButton("Checkout");
        checkoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkoutBtn.setFont(new Font("Arial", Font.BOLD, 14));
        checkoutBtn.setBackground(new Color(153, 204, 255));
        checkoutBtn.addActionListener(e -> {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Your cart is empty!");
            } else {
                JOptionPane.showMessageDialog(panel, "Order placed! Thank you for ordering 🍽️");
                cart.clear();
                updateCartDisplay();
            }
        });

        panel.add(Box.createVerticalStrut(20));
        panel.add(checkoutBtn);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        return scrollPane;
    }

    private void updateCartDisplay() {
        cartDisplay.setText("");
        double total = 0;
        for (String[] item : cart) {
            cartDisplay.append(item[0] + " — $" + item[1] + "\n");
            total += Double.parseDouble(item[1]);
        }
        cartDisplay.append("\nTotal: $" + String.format("%.2f", total));
    }

    /**
     * Creates the reservation panel.
     */
    private JPanel createReservationPanel(Font titleFont, Font bodyFont) {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Name field
        panel.add(createBodyLabel("Name:", bodyFont));
        JTextField nameField = new JTextField();
        panel.add(nameField);

        // Date field
        panel.add(createBodyLabel("Date (MM/DD/YYYY):", bodyFont));
        JTextField dateField = new JTextField();
        dateField.setToolTipText("Enter date as MM/DD/YYYY (e.g. 06/01/2025)");
        panel.add(dateField);

        // Time field
        panel.add(createBodyLabel("Time (HH:MM AM/PM):", bodyFont));
        JTextField timeField = new JTextField();
        timeField.setToolTipText("Enter time as HH:MM AM/PM (e.g. 06:30 PM)");
        panel.add(timeField);

        // Guests field
        panel.add(createBodyLabel("Guests:", bodyFont));
        JTextField guestsField = new JTextField();
        panel.add(guestsField);

        // Reserve button
        JButton reserveBtn = new JButton("Reserve");
        reserveBtn.setBackground(new Color(244, 180, 0));
        reserveBtn.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(reserveBtn);

        // Add action listener for button
        reserveBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String date = dateField.getText().trim();
            String time = timeField.getText().trim();
            String guests = guestsField.getText().trim();

            if (name.isEmpty() || date.isEmpty() || time.isEmpty() || guests.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel,
                        "Reservation confirmed for " + name + " on " + date + " at " + time +
                                " for " + guests + " guest(s).",
                        "Reservation Confirmed",
                        JOptionPane.INFORMATION_MESSAGE);

                // Clear fields after submission
                nameField.setText("");
                dateField.setText("");
                timeField.setText("");
                guestsField.setText("");
            }
        });

        return panel;
    }





    /**
     * Creates the login form for Admins.
     */
    private JPanel createLoginPanel(Font titleFont, Font bodyFont) {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(createBodyLabel("Username:", bodyFont));
        usernameField = new JTextField();
        panel.add(usernameField);
        panel.add(createBodyLabel("Password:", bodyFont));
        passwordField = new JPasswordField();
        panel.add(passwordField);
        panel.add(new JLabel());
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(244, 180, 0));
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.addActionListener(e -> verifyLogin());
        panel.add(loginBtn);
        return panel;
    }

    /**
     * Creates a panel for admin users to manage menu items.
     * Admins can load, add, delete, or update menu items stored in the database.
     *
     * @return JPanel with menu management controls
     */
    private JPanel createAdminPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Admin Menu Management", SwingConstants.CENTER);
        title.setFont(new Font("Times New Roman", Font.BOLD, 22));
        panel.add(title, BorderLayout.NORTH);

        JTextArea menuTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(menuTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton loadBtn = new JButton("Load Menu");
        loadBtn.addActionListener(e -> {
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/restaurantsystem",
                    "root",
                    "Stacey 170"
            );
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM MenuItem")) {
                menuTextArea.setText("");
                while (rs.next()) {
                    menuTextArea.append(rs.getInt("itemID") + ". " + rs.getString("itemName") + " - $" + rs.getDouble("price") + "\n");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading menu: " + ex.getMessage());
            }
        });

        JButton addBtn = new JButton("Add Item");
        addBtn.addActionListener(e -> {
            String itemName = JOptionPane.showInputDialog("Enter item name:");
            String priceStr = JOptionPane.showInputDialog("Enter price:");
            try {
                double price = Double.parseDouble(priceStr);
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/restaurantsystem",
                        "root",
                        "Stacey 170"
                );                String query = "INSERT INTO MenuItem (itemName, price) VALUES (?, ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, itemName);
                ps.setDouble(2, price);
                ps.executeUpdate();
                ps.close();
                conn.close();
                JOptionPane.showMessageDialog(this, "Item added!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding item: " + ex.getMessage());
            }
        });

        JButton deleteBtn = new JButton("Delete Item");
        deleteBtn.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Enter item ID to delete:");
            try {
                int id = Integer.parseInt(idStr);
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/restaurantsystem",
                        "root",
                        "Stacey 170"
                );
                String query = "DELETE FROM MenuItem WHERE itemID = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                int rows = ps.executeUpdate();
                ps.close();
                conn.close();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Item deleted!");
                } else {
                    JOptionPane.showMessageDialog(this, "No item found with that ID.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error deleting item: " + ex.getMessage());
            }
        });

        JButton editBtn = new JButton("Edit Item Price");
        editBtn.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Enter item ID to update:");
            String priceStr = JOptionPane.showInputDialog("Enter new price:");
            try {
                int id = Integer.parseInt(idStr);
                double price = Double.parseDouble(priceStr);
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/restaurantsystem",
                        "root",
                        "Stacey 170"
                );                String query = "UPDATE MenuItem SET price = ? WHERE itemID = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setDouble(1, price);
                ps.setInt(2, id);
                int rows = ps.executeUpdate();
                ps.close();
                conn.close();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Price updated!");
                } else {
                    JOptionPane.showMessageDialog(this, "No item found with that ID.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error updating price: " + ex.getMessage());
            }
        });

        btnPanel.add(loadBtn);
        btnPanel.add(addBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(editBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Verifies admin login and switches to admin panel if successful.
     */
    private void verifyLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/restaurantsystem",
                    "root",
                    "Stacey 170"
            );            String query = "SELECT * FROM Admin WHERE adminName = ? AND adminPassword = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Admin login successful!");
                cardLayout.show(contentPanel, "AdminPanel");
            } else {
                JOptionPane.showMessageDialog(this, "Login failed. Admin not found.");
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }

    }

    /**
     * Utility to create title labels.
     */
    private JLabel createTitleLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    /**
     * Utility to create body labels.
     */
    private JLabel createBodyLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    /**
     * Main method to run the GUI.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Restaurant().setVisible(true));
    }
}