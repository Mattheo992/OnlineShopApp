package model;

import model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PcComponents {
    private List<Component> processors;
    private List<Component> ssds;
    private List<Component> chargers;

    public PcComponents() {
        this.processors = new ArrayList<>();
        this.ssds = new ArrayList<>();
        this.chargers = new ArrayList<>();
        initializeComponents();
    }

    private void initializeComponents() {
        // Dodaj przykładowe komponenty do list
        processors.add(new Component(1, "Intel Core i5", BigDecimal.valueOf(499.99)));
        processors.add(new Component(2, "Intel Core i7", BigDecimal.valueOf(799.99)));
        processors.add(new Component(3, "AMD Ryzen 5", BigDecimal.valueOf(399.99)));

        ssds.add(new Component(1, "256GB", BigDecimal.valueOf(325.00))); // Pojemność dysku 256GB
        ssds.add(new Component(2, "512GB", BigDecimal.valueOf(559.99))); // Pojemność dysku 512GB
        ssds.add(new Component(3, "1TB", BigDecimal.valueOf(919.00))); // Pojemność dysku 1TB

        chargers.add(new Component(1, "650W", BigDecimal.valueOf(99.99))); // Moc ładowarki 650W
        chargers.add(new Component(2, "750W", BigDecimal.valueOf(129.99))); // Moc ładowarki 750W
        chargers.add(new Component(3, "850W", BigDecimal.valueOf(159.99))); // Moc ładowarki 850W
    }

    public List<Component> getProcessors() {
        return processors;
    }

    public void setProcessors(List<Component> processors) {
        this.processors = processors;
    }

    public List<Component> getSsds() {
        return ssds;
    }

    public void setSsds(List<Component> ssds) {
        this.ssds = ssds;
    }

    public List<Component> getChargers() {
        return chargers;
    }

    public void setChargers(List<Component> chargers) {
        this.chargers = chargers;
    }

    /**
     * Klasa reprezentująca komponent.
     */
    public static class Component extends Product {
        public Component(int id, String productName, BigDecimal productPrice) {
            super(id, productName, productPrice, 0);
        }
    }
}
