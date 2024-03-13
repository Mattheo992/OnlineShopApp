import java.util.List;

public class Smartphone extends Product {
    private Colours colour;
    private int batteryCapacity;
    private List<String> accessories;

    public Smartphone(Long id, String productName, double price, int quantityAvailable) {
        super(id, productName, price, quantityAvailable);
    }

    public void setPhoneColour(Colours colour) {
        this.colour = colour;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public void addAccessory(List<String> accessories) {
        this.accessories = accessories;
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "colour=" + colour +
                ", batteryCapacity=" + batteryCapacity +
                ", accessories=" + accessories +
                '}';
    }
}
