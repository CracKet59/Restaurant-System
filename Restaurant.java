import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Restaurant extends JFrame implements ActionListener {

    private CardLayout cardLayout;
    private JPanel contentPanel;

    public Restaurant() {
        setTitle("Taste Heaven");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Fonts and colors
        Font titleFont = new Font("Times New Roman", Font.BOLD, 24);
        Font bodyFont = new Font("Arial", Font.PLAIN, 16);
        Color primary = new Color(60, 63, 65);
        Color accent = new Color(244, 180, 0);

        // Navigation panel
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        navPanel.setBackground(primary);
        String[] sections = {"About Us", "Menu", "Reservation", "Login"};
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

        // Content area
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        contentPanel.add(createAboutUsPanel(titleFont, bodyFont), "About Us");
        contentPanel.add(createMenuPanel(titleFont, bodyFont), "Menu");
        contentPanel.add(createReservationPanel(titleFont, bodyFont), "Reservation");
        contentPanel.add(createLoginPanel(titleFont, bodyFont), "Login");

        // Header and Navigation
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        topPanel.add(navPanel, BorderLayout.SOUTH);

        // Add to frame
        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        cardLayout.show(contentPanel, "About Us");
    }

    // Header with icon and shadow effect
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(new Color(255, 240, 200));
        header.setBorder(new EmptyBorder(20, 10, 10, 10));

        JLabel icon = new JLabel("🍽️");
        icon.setFont(new Font("Serif", Font.PLAIN, 36));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel shadowLabel = new JLabel("Taste Heaven");
        shadowLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        shadowLabel.setForeground(Color.GRAY);
        shadowLabel.setBorder(BorderFactory.createEmptyBorder(0, 2, 2, 0));
        shadowLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("Taste Heaven");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        titleLabel.setForeground(new Color(244, 180, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(icon);
        header.add(shadowLabel);
        header.add(titleLabel);

        return header;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cardLayout.show(contentPanel, e.getActionCommand());
    }

    private JPanel createAboutUsPanel(Font titleFont, Font bodyFont) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(createTitleLabel("Welcome to Taste Heaven", titleFont));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createBodyLabel("We offer fresh, handcrafted meals made with love and local ingredients.", bodyFont));
        panel.add(createBodyLabel("Enjoy a cozy ambiance and world-class cuisine, right in your neighborhood!", bodyFont));
        return panel;
    }

    private JPanel createMenuPanel(Font titleFont, Font bodyFont) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(createTitleLabel("Our Menu", titleFont));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createBodyLabel("🍕 Margherita Pizza - $12", bodyFont));
        panel.add(createBodyLabel("🍔 Classic Burger - $10", bodyFont));
        panel.add(createBodyLabel("🥗 Caesar Salad - $9", bodyFont));
        panel.add(createBodyLabel("🍝 Spaghetti Bolognese - $13", bodyFont));
        return panel;
    }

    private JPanel createReservationPanel(Font titleFont, Font bodyFont) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(createBodyLabel("Name:", bodyFont));
        panel.add(new JTextField());
        panel.add(createBodyLabel("Date:", bodyFont));
        panel.add(new JTextField());
        panel.add(createBodyLabel("Time:", bodyFont));
        panel.add(new JTextField());
        panel.add(createBodyLabel("Guests:", bodyFont));
        panel.add(new JTextField());
        JButton reserveBtn = new JButton("Reserve");
        reserveBtn.setBackground(new Color(244, 180, 0));
        reserveBtn.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(new JLabel()); // Spacer
        panel.add(reserveBtn);
        return panel;
    }

    private JPanel createLoginPanel(Font titleFont, Font bodyFont) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(createBodyLabel("Username:", bodyFont));
        panel.add(new JTextField());
        panel.add(createBodyLabel("Password:", bodyFont));
        panel.add(new JPasswordField());
        panel.add(new JLabel());
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(244, 180, 0));
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(loginBtn);
        return panel;
    }

    private JLabel createTitleLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JLabel createBodyLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Restaurant().setVisible(true);
        });
    }
}
