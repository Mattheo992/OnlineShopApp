import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca telefon.
 */
public class Smartphone extends Product {
    /** Kolor telefonu. */
    public enum Color {
        BLACK,
        WHITE,
        GOLD,
        SILVER,
        BLUE,
        RED
    }

    /** Kolor telefonu. */
    private Color color;
    /** Pojemność baterii telefonu. */
    private int batteryCapacity;
    /** Akcesoria telefonu. */
    private List<String> accessories;

    /**
     * Konstruktor tworzący nowy obiekt Smartphone z określonymi parametrami.
     *
     * @param id                Identyfikator telefonu.
     * @param name              Nazwa telefonu.
     * @param price             Cena telefonu.
     * @param quantityAvailable Dostępna ilość telefonu.
     * @param color             Kolor telefonu.
     * @param batteryCapacity   Pojemność baterii telefonu.
     */
    public Smartphone(int id, String name, double price, int quantityAvailable, Color color, int batteryCapacity) {
        super(id, name, price, quantityAvailable);
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.accessories = new ArrayList<>();
    }

    /**
     * Konstruktor tworzący nowy obiekt Smartphone z domyślnymi parametrami.
     */
    public Smartphone() {
        super(888, "Smartphone", 0.00, 1);
        this.color = Color.BLACK;
        this.batteryCapacity = 4000;
        this.accessories = new ArrayList<>();
    }

    /**
     * Metoda zwracająca kolor telefonu.
     *
     * @return Kolor telefonu.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Metoda ustawiająca kolor telefonu.
     *
     * @param color Kolor telefonu.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Metoda zwracająca pojemność baterii telefonu.
     *
     * @return Pojemność baterii telefonu.
     */
    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    /**
     * Metoda ustawiająca pojemność baterii telefonu.
     *
     * @param batteryCapacity Pojemność baterii telefonu.
     */
    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    /**
     * Metoda zwracająca listę akcesoriów telefonu.
     *
     * @return Lista akcesoriów telefonu.
     */
    public List<String> getAccessories() {
        return accessories;
    }

    /**
     * Metoda dodająca akcesorium do listy akcesoriów telefonu.
     *
     * @param accessory Akcesorium do dodania.
     */
    public void addAccessory(String accessory) {
        accessories.add(accessory);
    }

    /**
     * Metoda wyświetlająca dostępne kolory telefonu.
     */
    public static void displayAvailableColors() {
        System.out.println("Dostępne kolory:");
        Color[] colors = Color.values();
        for (int i = 0; i < colors.length; i++) {
            System.out.println((i + 1) + ". " + colors[i]);
        }
    }

    /**
     * Metoda zwracająca reprezentację tekstową obiektu Smartphone.
     *
     * @return Reprezentacja tekstowa obiektu Smartphone.
     */
    @Override
    public String toString() {
        return "Smartphone{" + "id = " + getId() +
                ", nazwa produktu = " + getProductName() +
                ", kolor =" + color +
                ", pojemność baterii =" + batteryCapacity +
                ", akcesoria =" + accessories +
                '}';
    }
}
