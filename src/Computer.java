public class Computer extends Product {
    private String processor;
    private int ram;
    private int ssdDriveCapacity;
    private int charger;
    public Computer() {
        super(999, "Komputer", 0.0, 1);
        this.processor = "Standardowy procesor";
        this.ram = 8;
        this.ssdDriveCapacity = 256;
        this.charger = 65;
    }

    public Computer(int id, String productName, double price, int quantityAvailable) {
        super(id, productName, price, quantityAvailable);
    }

    public Computer(int id, String productName, double price, int quantityAvailable, String processor, int ram, int ssdDriveCapacity, int charger) {
        super(id, productName, price, quantityAvailable);
        this.processor = processor;
        this.ram = ram;
        this.ssdDriveCapacity = ssdDriveCapacity;
        this.charger = charger;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setSsdDriveCapacity(int ssdDriveCapacity) {
        this.ssdDriveCapacity = ssdDriveCapacity;
    }

    public void setCharger(int charger) {
        this.charger = charger;
    }

    @Override
    public String toString() {
        return "Komputer{" +
                "procesor ='" + processor + '\'' +
                ", pamięć ram =" + ram +
                ", pojemność dysku SSD =" + ssdDriveCapacity + " GB " +
                ", zasilacz =" + charger + " W " +
                '}';
    }
}
