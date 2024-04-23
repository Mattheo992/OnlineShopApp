package repository;
import java.util.Scanner;
import exception.ProductNotAvailableException;
import model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Klasa zarządzająca produktami w magazynie.
 */
public class ProductManager {

    /** Lista przechowująca produkty w magazynie. */
    private List<Product> products;

    /** Konstruktor tworzący nowy obiekt repository.ProductManager. */
    public ProductManager() {
        this.products = new ArrayList<>();
    }

    /**
     * Metoda dodająca produkt do magazynu.
     *
     * @param product Produkt do dodania.
     */
    public void addProduct(Product product) throws ProductNotAvailableException {
        if (product.getQuantityAvailable() <= 0) {
            throw new ProductNotAvailableException("Produkt jest niedostępny w magazynie.");
        }
        products.add(product);
        System.out.println("Produkt został dodany.");
    }
    /**
     * Metoda dodająca produkt do magazynu bez komentarza o dodanym produkcie.
     *
     * @param product Produkt do dodania.
     */
    public void addProductWithoutComment (Product product) throws ProductNotAvailableException {
        if (product.getQuantityAvailable() <= 0) {
            throw new ProductNotAvailableException("Produkt jest niedostępny w magazynie.");
        }
        products.add(product);
    }

    /** Metoda wyświetlająca wszystkie produkty w magazynie. */
    public void displayProducts() {
        Scanner scanner = new Scanner(System.in);

        if (products.isEmpty()) {
            System.out.println("Brak produktów w magazynie.");
        } else {
            System.out.println("Stan magazynowy:");
            for (Product product : products) {
                System.out.println("ID: " + product.getId() + ", Nazwa: " + product.getProductName()
                        + ", Cena: " + product.getPrice() + ", Dostępna ilość: " + product.getQuantityAvailable());
            }
        }
        System.out.println("Naciśnij 0, aby wrócić do menu głównego.");
        int input = scanner.nextInt();
        if (input == 0) {
            return;
        } else {
            System.out.println("Nieprawidłowy wybór.");
            displayProducts();
        }
    }


    /**
     * Metoda wyszukująca produkt po jego Id.
     *
     * @param productId Id produktu do znalezienia.
     * @return Obiekt Optional zawierający znaleziony produkt lub pusty, jeśli produkt nie został znaleziony.
     */
    public Optional<Product> findById(long productId) {
        return products.stream()
                .filter(product -> product.getId() == productId)
                .findFirst();
    }

    /**
     * Metoda zmniejszająca ilość dostępnych produktów w magazynie.
     *
     * @param productId Id produktu, którego ilość ma być zmniejszona.
     * @param quantity  Ilość produktów do zmniejszenia.
     */
    public void decrementQuantity(long productId, int quantity) {
        Optional<Product> optionalProduct = findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            int currentQuantity = product.getQuantityAvailable();
            if (currentQuantity >= quantity) {
                product.setQuantityAvailable(currentQuantity - quantity);
            } else {
                System.out.println("Nie ma wystarczającej ilości produktów w magazynie.");
            }
        } else {
            System.out.println("Produkt o podanym Id nie został znaleziony.");
        }
    }
}
