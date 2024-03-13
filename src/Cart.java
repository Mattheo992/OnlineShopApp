import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (product.getQuantityAvailable() > 0) {
            products.add(product);
            System.out.println("Produkt został dodany do koszyka");
        } else {
            System.out.println("Nie można dodać produktu do koszyka, jest niedostępny na magazynie");
        }
    }

    public void removeProduct(Product product) {
        if (products.remove(product)) {
            System.out.println("Produkt " + product.getProductName() + " został usnuęty z koszyka");
        } else {
            System.out.println("Taki produkt nie znajduje się w koszyku.");
        }
    }

    public void ViewCart() {
        if (products.isEmpty()) {
            System.out.println("Koszyk jest pusty");
        } else {
            System.out.println("Aktualna zawartość koszyka: ");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    public void placeOrder() {
        boolean allAvailable = products.stream().allMatch(product -> product.getQuantityAvailable() > 0);
        if (allAvailable) {
            System.out.println("Zamówienie zostało złożone.");
            products.clear();
        } else {
            System.out.println("Zamówienie nie może zostać złożone. Któryś z produktów jest aktualnie niedostępy");
        }
    }
}

