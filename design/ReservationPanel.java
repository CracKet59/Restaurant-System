// ReservationPanel.java
package design;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel for making table reservations with a confirmation message.
 */
public class ReservationPanel extends JPanel {
    public ReservationPanel(Font titleFont, Font bodyFont) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel title = new JLabel("Make a Reservation");
        title.setFont(titleFont);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(title);
        add(Box.createVerticalStrut(15));

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(bodyFont);
        JTextField nameField = new JTextField();

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(bodyFont);
        JTextField dateField = new JTextField();

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(bodyFont);
        JTextField phoneField = new JTextField();

        JLabel partyLabel = new JLabel("Party Size:");
        partyLabel.setFont(bodyFont);
        JTextField partyField = new JTextField();

        form.add(nameLabel);
        form.add(nameField);
        form.add(dateLabel);
        form.add(dateField);
        form.add(phoneLabel);
        form.add(phoneField);
        form.add(partyLabel);
        form.add(partyField);

        add(form);
        add(Box.createVerticalStrut(15));

        JButton reserveBtn = new JButton("Reserve");
        reserveBtn.setBackground(new Color(244, 180, 0));
        reserveBtn.setFont(new Font("Arial", Font.BOLD, 14));
        reserveBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(reserveBtn);

        reserveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String date = dateField.getText().trim();
                String phone = phoneField.getText().trim();
                String party = partyField.getText().trim();

                if (!name.isEmpty() && !date.isEmpty() && !phone.isEmpty() && !party.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        ReservationPanel.this,
                        "Thank you, " + name + "! Your reservation for " + party + " on " + date + " is confirmed.",
                        "Reservation Confirmed",
                        JOptionPane.INFORMATION_MESSAGE
                    );

                    nameField.setText("");
                    dateField.setText("");
                    phoneField.setText("");
                    partyField.setText("");
                } else {
                    JOptionPane.showMessageDialog(
                        ReservationPanel.this,
                        "Please fill in all fields.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
    }
}