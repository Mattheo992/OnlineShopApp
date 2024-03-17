import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private List<Product> products;
    private ProductManager productManager;

    public Cart() {
        this.products = new ArrayList<>();
    }
    public Optional<Product> findById(int productId) {
        return productManager.findById(productId);
    }

    public void addProduct(Product product) throws ProductNotAvailableException {
        if (product.getQuantityAvailable() > 0) {
            products.add(product);
            System.out.println("Produkt został dodany do koszyka");
        } else {
           throw new ProductNotAvailableException("Nie można dodać produktu do koszyka," +
                   " jest niedostępny na magazynie") ;
        }
    }

    public void removeProduct(Product product) {
        if (products.remove(product)) {
            System.out.println("Produkt " + product.getProductName() + " został usnuęty z koszyka");
        } else {
            System.out.println("Taki produkt nie znajduje się w koszyku.");
        }
    }

    public void viewCart() {
        if (products.isEmpty()) {
            System.out.println("Koszyk jest pusty");
        } else {
            System.out.println("Aktualna zawartość koszyka: ");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    public void placeOrder() throws OrderProcessingException {
        boolean allAvailable = products.stream().allMatch(product -> product.getQuantityAvailable() > 0);
        if (allAvailable) {
            System.out.println("Zamówienie zostało złożone.");
            products.clear();
        } else {
          throw new OrderProcessingException("Zamówienie nie może zostać złożone. " +
                  "Co najmniej jeden z produktów jest aktualnie niedostępy ");
        }
    }
    public List<Product> getCartItems() {
        return products;
    }
    public double calculateTotalAmount() {
        double totalAmount = 0.0;
        for (Product product : products) {
            totalAmount += product.getPrice();
        }
        return totalAmount;
    }
}

