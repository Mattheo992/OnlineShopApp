package model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa reprezentująca komputer.
 */
public class Computer extends Product {
    private String processor; // Procesor
    private int ram; // Pamięć RAM
    private int ssdDriveCapacity; // Wielkość dysku SSD
    private int charger; // Zasilacz

    /**
     * Konstruktor domyślny dla klasy model.Computer, ustawiający domyślne wartości dla komputera.
     */
    public Computer() {
        super(999, "Komputer", BigDecimal.ZERO, 1);
        this.processor = "Standardowy procesor";
        this.ram = 8;
        this.ssdDriveCapacity = 256;
        this.charger = 65;
    }

    /**
     * Konstruktor klasy model.Computer z podanymi parametrami.
     *
     * @param id                Identyfikator komputera
     * @param productName       Nazwa produktu
     * @param price             Cena produktu
     * @param quantityAvailable Dostępna ilość produktu
     */
    public Computer(int id, String productName, BigDecimal price, int quantityAvailable) {
        super(id, productName, price, quantityAvailable);
    }

    /**
     * Konstruktor klasy model.Computer z podanymi parametrami.
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
    public Computer(int id, String productName, BigDecimal price, int quantityAvailable, String processor, int ram, int ssdDriveCapacity, int charger) {
        super(id, productName, price, quantityAvailable);
        this.processor = processor;
        this.ram = ram;
        this.ssdDriveCapacity = ssdDriveCapacity;
        this.charger = charger;
    }

    /**
     * Ustawia procesor komputera.
     *
     * @param processor procesor komputera
     */
    public void setProcessor(String processor) {
        this.processor = processor;
    }

    /**
     * Ustawia pamięć RAM komputera.
     *
     * @param ram pamięć RAM komputera
     */
    public void setRam(int ram) {
        this.ram = ram;
    }

    /**
     * Ustawia wielkość dysku SSD komputera.
     *
     * @param ssdDriveCapacity wielkość dysku SSD komputera
     */
    public void setSsdDriveCapacity(int ssdDriveCapacity) {
        this.ssdDriveCapacity = ssdDriveCapacity;
    }

    /**
     * Ustawia moc zasilacza.
     *
     * @param charger moc zasilacza
     */
    public void setCharger(int charger) {
        this.charger = charger;
    }

    /**
     * Zwraca tekstową reprezentację obiektu klasy model.Computer.
     *
     * @return tekstowa reprezentacja obiektu klasy model.Computer
     */
    @Override
    public String toString() {
        return "Komputer{" +
                "id=" + getId() +
                ", nazwa produktu='" + getProductName() + '\'' +
                ", procesor='" + processor + '\'' +
                ", pamięć ram=" + ram + " GB" +
                ", pojemność dysku SSD=" + ssdDriveCapacity + " GB" +
                ", zasilacz=" + charger + " W" +
                '}';
    }
    }
