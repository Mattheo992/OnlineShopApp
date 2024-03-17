import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private int orderId;
    private Customer customer;
    private List<Product> products;
    private double totalAmount;
    private LocalDateTime orderTime;


    public Order(int orderId, Customer customer, List<Product> products, double totalAmount, LocalDateTime orderTime) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.totalAmount = totalAmount;
        this.orderTime = orderTime;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "Id zamówienia =" + orderId +
                ", dane klienta =" + customer +
                ", produkty=" + products +
                ", do zapłaty =" + totalAmount +
                '}';
    }
    public void applyDiscount(double discount) {
        totalAmount -= discount;
        System.out.println("Zastosowano zniżkę w wysokości " + discount + " do zamówienia.");
    }
}
