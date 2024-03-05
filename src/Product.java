import java.util.Objects;

public class Product {
    private Long id;
    private String productName;
    private double price;
    private int quantityAvailable;

    public Product(Long id, String productName, double price, int quantityAvailable) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && quantityAvailable == product.quantityAvailable && Objects.equals(id, product.id) && Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, price, quantityAvailable);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantityAvailable=" + quantityAvailable +
                '}';
    }
}
