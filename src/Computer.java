public class Computer extends Product {
    private String processor;
    private int ram;
    private int ssdDriveCapacity;
    private int charger;

    public Computer(Long id, String productName, double price, int quantityAvailable) {
        super(id, productName, price, quantityAvailable);
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
        return "Computer{" +
                "processor='" + processor + '\'' +
                ", ram=" + ram +
                ", ssdDriveCapacity=" + ssdDriveCapacity + " GB " +
                ", charger=" + charger + " W " +
                '}';
    }
}
