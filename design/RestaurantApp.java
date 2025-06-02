package design;

public class RestaurantApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new RestaurantFrame().setVisible(true);
        });
    }
}