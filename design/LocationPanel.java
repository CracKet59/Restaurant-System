// LocationPanel.java
package design;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel displaying the restaurant's location information.
 */
public class LocationPanel extends JPanel {
    public LocationPanel(Font titleFont, Font bodyFont) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("Find Us Here");
        titleLabel.setFont(titleFont);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(titleLabel);

        add(Box.createVerticalStrut(10));

        JLabel seattle = new JLabel("📍 Seattle - 123 Pike Street, Seattle, WA 98101");
        seattle.setFont(bodyFont);
        seattle.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(seattle);

        JLabel bellevue = new JLabel("📍 Bellevue - 456 Bellevue Way NE, Bellevue, WA 98004");
        bellevue.setFont(bodyFont);
        bellevue.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(bellevue);

        JLabel federalWay = new JLabel("📍 Federal Way - 789 Pacific Hwy S, Federal Way, WA 98003");
        federalWay.setFont(bodyFont);
        federalWay.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(federalWay);

        add(Box.createVerticalStrut(10));

        JLabel hours = new JLabel("Open Daily: 10 AM - 10 PM");
        hours.setFont(bodyFont);
        hours.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(hours);

        JLabel phone = new JLabel("Phone: (123) 456-7890");
        phone.setFont(bodyFont);
        phone.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(phone);
    }
}
