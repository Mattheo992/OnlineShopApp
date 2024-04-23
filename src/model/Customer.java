package model;

import model.Order;

import java.math.BigDecimal;

/**
 * Klasa repezentująca klienta
 */
public class Customer {
    private String customerName; //Imię klienta
    private String customerLastName; //Nazwisko klienta
    private String email; //Email klienta
    private String phoneNumber; //Numer telefonu klienta
    private String address; //Adres klienta
    private int loyaltyPoints; //Punkty lojalnościowe klienta
    private Order currentOrder; //Bierzące zamówienie

    /**
     * Zwraca bieżące zamówienie klienta
     * @return bieżące zamówienie klienta
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }
    /**
     * Ustawia bieżące zamówienie klienta
     * @return bieżące zamówienie klienta
     */
    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    /**
     * Konstruktor klasy model.Customer
     * @param customerName imię klienta
     * @param customerLastName nazwisko klienta.
     * @param email email klienta
     * @param phoneNumber numer telefonu klienta
     * @param address adres klienta
     * @param loyaltyPoints punkty lojalnościowe klienta
     */
    public Customer(String customerName, String customerLastName, String email, String phoneNumber, String address, int loyaltyPoints) {
        this.customerName = customerName;
        this.customerLastName = customerLastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.loyaltyPoints = loyaltyPoints;
    }

    /**
     * Zwraca tekstową reprezentacja obiektu klasy Cunstomer
     * @return ekstowa reprezentacja obiektu klasy Cunstomer
     */
    @Override
    public String toString() {
        return "model.Customer{" +
                "customerName='" + customerName + '\'' +
                ", customerLastName='" + customerLastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
    /**
     * Zwraca ilość punktów lojalnościowych
     * @return ilość punktów lojalnościowych
     */
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
    /**
     * Ustawia ilość punktów lojalnościowych
     * @return ilość punktów lojalnościowych
     */
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
    /**
     * Zwraca imię klienta
     * @return imię klienta
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * Ustawia imię klienta
     * @return imię klienta
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**
     * Zwraca nazwisko klienta
     * @return nazwisko klienta
     */
    public String getCustomerLastName() {
        return customerLastName;
    }
    /**
     * Ustawia nazwisko klienta
     * @return nazwisko klienta
     */
    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }
    /**
     * Zwraca email klienta
     * @return email klienta
     */
    public String getEmail() {
        return email;
    }
    /**
     * Ustawia email klienta
     * @return email klienta
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Zwraca numer telefonu klienta
     * @return numer telefonu klienta
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Ustawia numer telefonu klienta
     * @return numer telefonu klienta
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * Zwraca adres klienta
     * @return adres klienta
     */
    public String getAddress() {
        return address;
    }
    /**
     * Ustawia adres klienta
     * @return adres klienta
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Wykorzystuje punkty lojalnościowe klienta do wymiany na rabat na bieżące zamówienie
     * @param pointsToRedeem Liczba punktów lojalnościowych na wymianę
     */
    public void redeemLoyaltyPoints(int pointsToRedeem) {
        int redemptionThreshold = 100; // Minimalna liczba punktów wymagana do wymiany na nagrodę
        double discountRate = 0.05; // Stopa zniżki dla każdego wykorzystanego punktu lojalnościowego

        // Sprawdzamy, czy klient ma wystarczającą liczbę punktów do wymiany
        if (loyaltyPoints >= pointsToRedeem && pointsToRedeem >= redemptionThreshold) {
            // Obliczamy wartość zniżki na podstawie liczby wykorzystanych punktów
            double discount = pointsToRedeem * discountRate;

            // Dodajemy zniżkę do bieżącego zamówienia klienta
            if (currentOrder != null) {
                currentOrder.applyDiscount(BigDecimal.valueOf(discount));
                System.out.println("Pomyślnie dodano zniżkę w wysokości " + discount + " do bieżącego zamówienia.");
            } else {
                System.out.println("Nie można dodać zniżki, brak bieżącego zamówienia.");
            }

            // Odejmujemy wykorzystane punkty z konta klienta
            loyaltyPoints -= pointsToRedeem;

            System.out.println("Pomyślnie wymieniono " + pointsToRedeem + " punktów lojalnościowych na zniżkę w wysokości " + discount + ".");
        } else {
            System.out.println("Nie masz wystarczającej liczby punktów lojalnościowych do wymiany na nagrodę.");
        }
    }

}
