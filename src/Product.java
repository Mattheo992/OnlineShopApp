import java.util.Objects;

/**
 * Klasa reprezentująca produkt.
 */
public class Product {
    /** Identyfikator produktu. */
    private int id;
    /** Nazwa produktu. */
    private String productName;
    /** Cena produktu. */
    private double price;
    /** Dostępna ilość produktu. */
    private int quantityAvailable;

    /**
     * Konstruktor tworzący nowy obiekt produktu.
     *
     * @param id                Identyfikator produktu.
     * @param productName       Nazwa produktu.
     * @param price             Cena produktu.
     * @param quantityAvailable Dostępna ilość produktu.
     */
    public Product(int id, String productName, double price, int quantityAvailable) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }

    /** Metoda zwracająca identyfikator produktu. */
    public int getId() {
        return id;
    }

    /** Metoda ustawiająca identyfikator produktu. */
    public void setId(int id) {
        this.id = id;
    }

    /** Metoda zwracająca nazwę produktu. */
    public String getProductName() {
        return productName;
    }

    /** Metoda ustawiająca nazwę produktu. */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /** Metoda zwracająca cenę produktu. */
    public double getPrice() {
        return price;
    }

    /** Metoda ustawiająca cenę produktu. */
    public void setPrice(double price) {
        this.price = price;
    }

    /** Metoda zwracająca dostępną ilość produktu. */
    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    /** Metoda ustawiająca dostępną ilość produktu. */
    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    /**
     * Metoda porównująca dwa obiekty produktu.
     *
     * @param o Obiekt do porównania.
     * @return Wartość true, jeśli obiekty są identyczne, w przeciwnym razie false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                quantityAvailable == product.quantityAvailable &&
                id == product.id &&
                Objects.equals(productName, product.productName);
    }

    /**
     * Metoda generująca hashcode dla obiektu.
     *
     * @return Hashcode obiektu.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, productName, price, quantityAvailable);
    }

    /**
     * Metoda generująca tekstową reprezentację obiektu.
     *
     * @return Tekstowa reprezentacja obiektu.
     */
    @Override
    public String toString() {
        return "Product{" +
                "Id=" + id +
                ", Nazwa produktu='" + productName + '\'' +
                ", Cena=" + price +
                ", Dostępna ilość=" + quantityAvailable +
                '}';
    }
}
