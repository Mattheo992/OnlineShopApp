public class Customer {
    private String customerName;
    private String customerLastName;
    private String email;
    private String phoneNumber;
    private String address;
    private int loyaltyPoints;
    private Order currentOrder;

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public Customer(String customerName, String customerLastName, String email, String phoneNumber, String address, int loyaltyPoints) {
        this.customerName = customerName;
        this.customerLastName = customerLastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", customerLastName='" + customerLastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void redeemLoyaltyPoints(int pointsToRedeem) {
        int redemptionThreshold = 100; // Minimalna liczba punktów wymagana do wymiany na nagrodę
        double discountRate = 0.05; // Stopa zniżki dla każdego wykorzystanego punktu lojalnościowego

        // Sprawdzamy, czy klient ma wystarczającą liczbę punktów do wymiany
        if (loyaltyPoints >= pointsToRedeem && pointsToRedeem >= redemptionThreshold) {
            // Obliczamy wartość zniżki na podstawie liczby wykorzystanych punktów
            double discount = pointsToRedeem * discountRate;

            // Dodajemy zniżkę do bieżącego zamówienia klienta
            if (currentOrder != null) {
                currentOrder.applyDiscount(discount);
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
