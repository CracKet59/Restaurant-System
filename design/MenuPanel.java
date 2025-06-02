// MenuPanel.java
package design;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel displaying the restaurant menu with categories highlighted on a side panel.
 */
public class MenuPanel extends JPanel {
    private CardLayout sectionLayout;
    private JPanel sectionPanel;

    public MenuPanel(Font titleFont, Font bodyFont) {
        setLayout(new BorderLayout());

        // Side navigation for categories
        JPanel categoryNav = new JPanel();
        categoryNav.setLayout(new BoxLayout(categoryNav, BoxLayout.Y_AXIS));
        categoryNav.setBackground(new Color(230, 230, 230));
        categoryNav.setBorder(new EmptyBorder(20, 10, 20, 10));

        String[] categories = {"Appetizers", "Main Dishes", "Specials", "Drinks & Desserts"};
        for (String category : categories) {
            JButton btn = new JButton(category);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setFocusPainted(false);
            btn.setBackground(new Color(244, 180, 0));
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.addActionListener(e -> sectionLayout.show(sectionPanel, category));
            categoryNav.add(btn);
            categoryNav.add(Box.createVerticalStrut(10));
        }

        // Right panel with CardLayout to switch between sections
        sectionLayout = new CardLayout();
        sectionPanel = new JPanel(sectionLayout);
        sectionPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        sectionPanel.add(createCategoryPanel("Appetizers", new String[] {
            "🥗 Caesar Salad - $9",
            "🌯 Falafel Wrap - $9",
            "🍤 Shrimp Tempura - $15",
            "🍜 Miso Soup - $5"
        }, titleFont, bodyFont), "Appetizers");

        sectionPanel.add(createCategoryPanel("Main Dishes", new String[] {
            "🍕 Margherita Pizza - $12",
            "🍕 Pepperoni Pizza - $13",
            "🍔 Classic Burger - $10",
            "🍝 Spaghetti Bolognese - $13",
            "🍛 Butter Chicken - $14",
            "🥪 Turkey Club Sandwich - $10",
            "🍲 Ramen Bowl - $12",
            "🥘 Beef Stroganoff - $14"
        }, titleFont, bodyFont), "Main Dishes");

        sectionPanel.add(createCategoryPanel("Specials", new String[] {
            "🍣 Sushi Platter - $18",
            "🌮 Chicken Tacos - $11",
            "🍗 Fried Chicken - $12",
            "🍜 Pad Thai - $12"
        }, titleFont, bodyFont), "Specials");

        sectionPanel.add(createCategoryPanel("Drinks & Desserts", new String[] {
            "🥞 Pancake Stack - $8",
            "🍩 Donut Assortment - $7",
            "🍨 Ice Cream Sundae - $6",
            "🥤 Soft Drinks - $2",
            "☕ Coffee - $3"
        }, titleFont, bodyFont), "Drinks & Desserts");

        add(categoryNav, BorderLayout.WEST);
        add(sectionPanel, BorderLayout.CENTER);

        sectionLayout.show(sectionPanel, "Appetizers");
    }

    private JPanel createCategoryPanel(String title, String[] items, Font titleFont, Font bodyFont) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(0, 10, 0, 10));

        JLabel heading = new JLabel(title);
        heading.setFont(titleFont);
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(heading);
        panel.add(Box.createVerticalStrut(10));

        for (String item : items) {
            JLabel label = new JLabel(item);
            label.setFont(bodyFont);
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(label);
        }

        return panel;
    }
}
