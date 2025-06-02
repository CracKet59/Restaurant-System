package design;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main frame for the Taste Heaven restaurant application.
 * Handles layout and panel switching for About Us, Menu, Reservation, and Location.
 */
public class RestaurantFrame extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public RestaurantFrame() {
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
        String[] sections = {"About Us", "Menu", "Reservation", "Location"};
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
        contentPanel.add(new AboutUsPanel(titleFont, bodyFont), "About Us");
        contentPanel.add(new MenuPanel(titleFont, bodyFont), "Menu");
        contentPanel.add(new ReservationPanel(titleFont, bodyFont), "Reservation");
        contentPanel.add(new LocationPanel(titleFont, bodyFont), "Location");

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        topPanel.add(navPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        cardLayout.show(contentPanel, "About Us");
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(new Color(255, 240, 200));
        header.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JLabel icon = new JLabel("\uD83C\uDF7D\uFE0F");
        icon.setFont(new Font("Serif", Font.PLAIN, 36));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel shadowLabel = new JLabel("Taste Heaven");
        shadowLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        shadowLabel.setForeground(Color.GRAY);
        shadowLabel.setBorder(BorderFactory.createEmptyBorder(0, 2, 2, 0));
        shadowLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(icon);
        header.add(shadowLabel);
  
        return header;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cardLayout.show(contentPanel, e.getActionCommand());
    }
}