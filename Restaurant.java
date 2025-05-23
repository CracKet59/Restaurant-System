import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Restaurant extends JFrame implements ActionListener {

    private CardLayout cardLayout;
    private JPanel contentPanel;

    public Restaurant() {
        setTitle("Restaurant App");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Navigation buttons
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] sections = {"About Us", "Menu", "Reservation", "Login"};
        for (String section : sections) {
            JButton button = new JButton(section);
            button.setActionCommand(section);
            button.addActionListener(this);
            navPanel.add(button);
        }

        // Main content area with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        contentPanel.add(createAboutUsPanel(), "About Us");
        contentPanel.add(createMenuPanel(), "Menu");
        contentPanel.add(createReservationPanel(), "Reservation");
        contentPanel.add(createLoginPanel(), "Login");

        add(navPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        cardLayout.show(contentPanel, "About Us"); // Default view
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cardLayout.show(contentPanel, e.getActionCommand());
    }

    private JPanel createAboutUsPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("<html><h2>Welcome to Our Restaurant!</h2><p>We serve fresh, delicious meals daily.</p></html>"));
        return panel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("<html><h2>Menu</h2></html>"));
        panel.add(new JLabel("🍕 Pizza - $10"));
        panel.add(new JLabel("🍔 Burger - $8"));
        panel.add(new JLabel("🥗 Salad - $7"));
        return panel;
    }

    private JPanel createReservationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 5, 5));
        panel.add(new JLabel("Name:"));
        panel.add(new JTextField(10));
        panel.add(new JLabel("Date:"));
        panel.add(new JTextField(10));
        panel.add(new JLabel("Time:"));
        panel.add(new JTextField(10));
        panel.add(new JLabel("People:"));
        panel.add(new JTextField(5));
        return panel;
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("Username:"));
        panel.add(new JTextField(10));
        panel.add(new JLabel("Password:"));
        panel.add(new JPasswordField(10));
        JButton loginButton = new JButton("Login");
        panel.add(new JLabel()); // empty placeholder
        panel.add(loginButton);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Restaurant().setVisible(true);
        });
    }
}
