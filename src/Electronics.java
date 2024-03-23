/**
 * Klasa reprezentująca elektronikę.
 * Klasa dziedzicząca po klasie Product
 */
public class Electronics extends Product{
    /**
     * Konstruktor dla klasy Electronics
     * @param id Id produktu
     * @param productName nazwa produktu
     * @param price cena produktu
     * @param quantityAvailable dostępna ilość produktu na magazynie
     */
    public Electronics(int id, String productName, double price, int quantityAvailable) {
        super(id, productName, price, quantityAvailable);
    }

}
