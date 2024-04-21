import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Klasa reprezentująca zamówienie
 */
public class Order {
    private int orderId;
    private Customer customer;
    private List<Product> products;
    private BigDecimal totalAmount;
    private ZonedDateTime orderTime;

    /**
     * Konstruktor klasy Order.
     * @param orderId Id zamówienia
     * @param customer klient
     * @param products lista produktów w zamówieniu
     * @param totalAmount całkowita kwota do zapłaty
     * @param orderTime czas złożenia zamówienia
     */
    public Order(int orderId, Customer customer, List<Product> products, BigDecimal totalAmount, ZoneId orderTime) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.totalAmount = totalAmount;
        this.orderTime = ZonedDateTime.now(orderTime);
    }

    /**
     * Pobiera czas zamówienia
     * @return czas zamówienia
     */
    public ZonedDateTime getOrderTime() {
        return orderTime;
    }
    /**
     * Ustawia czas zamówienia
     * @return czas zamówienia
     */
    public void setOrderTime(ZonedDateTime orderTime) {
        this.orderTime = orderTime;
    }
    /**
     * Pobiera całkowitą wartość zamówienia zamówienia
     * @return całkowita wartość zamówienia zamówienia
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    /**
     * Ustawia całkowitą wartość zamówienia zamówienia
     * @return całkowita wartość zamówienia zamówienia
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    /**
     * Pobiera Id zamówienia
     * @return Id zamówienia
     */
    public int getOrderId() {
        return orderId;
    }
    /**
     * Ustawia Id zamówienia
     * @return Id zamówienia
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    /**
     * Pobiera klienta składającego zamówienie
     * @return klient składający zamówienie
     */
    public Customer getCustomer() {
        return customer;
    }
    /**
     * Ustawia klienta składającego zamówienie
     * @return klient składający zamówienie
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    /**
     * Pobiera listę zamówionych produktów
     * @return lista zamówionych produktów
     */
    public List<Product> getProducts() {
        return products;
    }
    /**
     * Ustawia listę zamówionych produktów
     * @return lista zamówionych produktów
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Zwraca tekstową reprezentację obiektu klasy Order.
     * @return tekstowa reprezentacja obiektu klasy Order.
     */
    @Override
    public String toString() {
        return "Order{" +
                "Id zamówienia =" + orderId +
                ", dane klienta =" + customer +
                ", produkty=" + products +
                ", do zapłaty =" + totalAmount +
                '}';
    }

    /**
     * Zastosowuje zniżkę do całkowitej kwoty zamówienia.
     * @param discount wartość zniżki do zastosowania w zamówieniu
     */
    public void applyDiscount(BigDecimal discount) {
        totalAmount = totalAmount.subtract(discount);
        System.out.println("Zastosowano zniżkę w wysokości " + discount + " do zamówienia.");
    }
}
