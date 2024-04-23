package model;

import model.Product;

import java.math.BigDecimal;

/**
 * Klasa reprezentująca elektronikę.
 * Klasa dziedzicząca po klasie model.Product
 */
public class Electronics extends Product {
    /**
     * Konstruktor dla klasy model.Electronics
     * @param id Id produktu
     * @param productName nazwa produktu
     * @param price cena produktu
     * @param quantityAvailable dostępna ilość produktu na magazynie
     */
    public Electronics(int id, String productName, BigDecimal price, int quantityAvailable) {
        super(id, productName, price, quantityAvailable);
    }

}
