import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private List<Product> products;
    private ProductManager productManager;
    public Cart() {
        this.products = new ArrayList<>();
    }

    public Cart(ProductManager productManager) {
        this.products = new ArrayList<>();
        this.productManager = productManager;
    }

    public void addProduct(Product product, int quantity) throws ProductNotAvailableException {
            Optional<Product> optionalProduct = productManager.findById(product.getId());
            if (optionalProduct.isPresent()) {
                Product foundProduct = optionalProduct.get();
                if (foundProduct.getQuantityAvailable() >= quantity) {
                    for (int i = 0; i < quantity; i++) {
                        products.add(foundProduct);
                    }
                    productManager.decrementQuantity(foundProduct.getId(), quantity);
                    System.out.println("Produkt został dodany do koszyka");
                } else {
                    System.out.println("Nie ma wystarczającej ilości produktów na magazynie");
                }
            } else {
                System.out.println("Produkt o podanym ID nie został znaleziony.");
            }
        }

    public void removeProduct(Product product, int quantity) {
        int removedCount = 0;
        while (removedCount < quantity) {
            if (products.remove(product)) {
                removedCount++;
            } else {
                break;
            }
        }

        if (removedCount > 0) {
            System.out.println("Usunięto " + removedCount + " szt. produktu " + product.getProductName() + " z koszyka");
        } else {
            System.out.println("Nie ma wystarczającej ilości produktu " + product.getProductName() + " w koszyku.");
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

    public List<Product> getCartItems() {
        return products;
    }

    public double calculateTotalAmount() {
        double totalAmount = 0.0;
        for (Product product : products) {
            totalAmount += product.getPrice() * getProductQuantityInCart(product);
        }
        return totalAmount;
    }

    private int getProductQuantityInCart(Product product) {
        int quantityInCart = 0;
        for (Product cartProduct : products) {
            if (cartProduct.equals(product)) {
                quantityInCart++;
            }
        }
        return quantityInCart;
    }
}