/**
 * Klasa reprezentująca komputer
 */
public class Computer extends Product {
    private String processor; //Procesor
    private int ram; //Pamięć ram
    private int ssdDriveCapacity; //Wielkosć dysku SSD
    private int charger; //Zasilacz

    /**
     * Konstruktor domyślny dla klasy Computer, ustawiajacy domyślne wartości dla komputera
     */
    public Computer() {
        super(999, "Komputer", 0.0, 1);
        this.processor = "Standardowy procesor";
        this.ram = 8;
        this.ssdDriveCapacity = 256;
        this.charger = 65;
    }

    /**
     * Konstruktor klasy Computer z podanymi parametrami
     * @param id Indentyfikator Id
     * @param productName Nazwa produktu
     * @param price Cena produktu
     * @param quantityAvailable Dostępność na magazynie
     */
    public Computer(int id, String productName, double price, int quantityAvailable) {
        super(id, productName, price, quantityAvailable);
    }
    /**
     * Konstruktor klasy Computer z podanymi parametrami.
     *
     * @param id                Identyfikator komputera
     * @param productName       Nazwa produktu
     * @param price             Cena produktu
     * @param quantityAvailable Dostępna ilość produktu
     * @param processor         Procesor komputera
     * @param ram               Pamięć RAM (w GB)
     * @param ssdDriveCapacity  Pojemność dysku SSD (w GB)
     * @param charger           Moc zasilacza (w Watach)
     */
    public Computer(int id, String productName, double price, int quantityAvailable, String processor, int ram, int ssdDriveCapacity, int charger) {
        super(id, productName, price, quantityAvailable);
        this.processor = processor;
        this.ram = ram;
        this.ssdDriveCapacity = ssdDriveCapacity;
        this.charger = charger;
    }

    /**
     * Ustawia procesor komputera.
     * @param processor procesor komputera
     */
    public void setProcessor(String processor) {
        this.processor = processor;
    }

    /**
     * Ustawia pamieć RAM komputera.
     * @param ram pamięć RAM komputera.
     */
    public void setRam(int ram) {
        this.ram = ram;
    }

    /**
     * Ustawia wielkość dysku SSD komputera.
     * @param ssdDriveCapacity wielkość dysku SSD komputera
     */
    public void setSsdDriveCapacity(int ssdDriveCapacity) {
        this.ssdDriveCapacity = ssdDriveCapacity;
    }

    /**
     * Ustawia moc zasilacza.
     * @param charger moc zasilacza
     */
    public void setCharger(int charger) {
        this.charger = charger;
    }

    /**
     * Zwraca tekstową reprezentację obiektu klasy Computer.
     * @return tekstowa reprezentacja obiektu klasy Computer
     */
    @Override
    public String toString() {
        return "Komputer{" +"id = " + getId() +
                ", nazwa produktu = " + getProductName() +
                ", procesor ='" + processor + '\'' +
                ", pamięć ram =" + ram +
                ", pojemność dysku SSD =" + ssdDriveCapacity + " GB " +
                ", zasilacz =" + charger + " W " +
                '}';
    }
}
