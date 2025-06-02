package design;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel displaying the "About Us" section.
 */
public class AboutUsPanel extends JPanel {
    public AboutUsPanel(Font titleFont, Font bodyFont) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("Welcome to Taste Heaven");
        titleLabel.setFont(titleFont);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(titleLabel);

        add(Box.createVerticalStrut(10));

        JLabel line1 = new JLabel("We offer fresh, handcrafted meals made with love and local ingredients.");
        line1.setFont(bodyFont);
        line1.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(line1);

        JLabel line2 = new JLabel("Enjoy a cozy ambiance and world-class cuisine, right in your neighborhood!");
        line2.setFont(bodyFont);
        line2.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(line2);
    }
}
