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

    @Override
    public String toString() {
        return "Smartphone{" +
                "kolor =" + color +
                ", pojemność baterii =" + batteryCapacity +
                ", akcesoria =" + accessories +
                '}';
    }

    private Color color;
    private int batteryCapacity;
    private List<String> accessories;

    public Smartphone(int id, String name, double price, int quantityAvailable, Color color, int batteryCapacity) {
        super(id, name, price, quantityAvailable);
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.accessories = new ArrayList<>();
    }


    public Smartphone() {
        super(888, "Smartphone", 0.00, 1);
        this.color = Color.BLACK;
        this.batteryCapacity = 4000;
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


    public static void displayAvailableColors() {
        System.out.println("Dostępne kolory:");
        Color[] colors = Color.values();
        for (int i = 0; i < colors.length; i++) {
            System.out.println((i + 1) + ". " + colors[i]);
        }
    }
}
