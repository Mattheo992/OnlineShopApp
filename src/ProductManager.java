import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductManager {

    private List<Product> products;

    public ProductManager() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Produkt został dodany.");
    }

    public void removeProduct(Product product) {
        if (products.contains(product)) {
            products.remove(product);
            System.out.println("Produkt został usunięty.");
        } else {
            System.out.println("Produkt nie istnieje w bazie.");
        }
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Brak produktów w magazynie.");
        } else {
            System.out.println("Stan magazynowy:");
            for (Product product : products) {
                System.out.println(product.getProductName() + " - Dostępne sztuki: " + product.getQuantityAvailable());
            }
        }
    }

    public void updateProduct(int productId, Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getId() == productId) {
                products.set(i, updatedProduct);
                System.out.println("Produkt został zaktualizowany.");
                return;
            }
        }
        System.out.println("Produkt o podanym ID nie został znaleziony.");

    }
    public Optional<Product> findById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }
}
