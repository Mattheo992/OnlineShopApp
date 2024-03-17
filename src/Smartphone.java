import java.util.ArrayList;
import java.util.List;

public class Smartphone extends Product {
    public enum Color {
        BLACK,
        WHITE,
        GOLD,
        SILVER,
        BLUE,
        RED
    }

    private Color color;
    private int batteryCapacity; // Pojemność baterii w mAh
    private List<String> accessories; // Lista dodatkowych akcesoriów

    public Smartphone(long id, String name, double price, int quantityAvailable, Color color, int batteryCapacity) {
        super(id, name, price, quantityAvailable);
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.accessories = new ArrayList<>();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public List<String> getAccessories() {
        return accessories;
    }

    public void addAccessory(String accessory) {
        accessories.add(accessory);
    }

    // Przydatna metoda do wyświetlania dostępnych kolorów
    public static void displayAvailableColors() {
        System.out.println("Dostępne kolory:");
        for (Color color : Color.values()) {
            System.out.println(color);
        }
    }
}
