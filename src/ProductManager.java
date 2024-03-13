import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> products;

    public ProductManager() {
        this.products = new ArrayList<>();
    }

    //Metoda do dodawania produktu
    public void addProduct(Product product) {
        if (product instanceof Computer || product instanceof Smartphone || product instanceof Electronics) {
            products.add(product);
            System.out.println("Produkt dodany: " + product.getProductName());
        } else {
            System.out.println("Nieobsługiwany typ produktu.");
        }
    }


    //Metoda do usuwania produktu
    public void removeProduct(Product product) {
        if (products.contains(product)) {
            products.remove(product);
            System.out.println("Usunięto " + product.getProductName());
        } else {
            System.out.println("Taki produkt nie został dodany");
        }
    }

    //Metoda do aktualizacji koszyka
    public void updateProduct(Product oldProduct, Product newProduct) {
        int index = products.indexOf(oldProduct);
        if (index != -1) {
            products.set(index, newProduct);
        } else {
            System.out.println("Produkt nie został znaleziony.");
        }
    }

    //Metoda do przeglądania wszystkich produktów
    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Brak produktów do wyświetlenia.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
}
