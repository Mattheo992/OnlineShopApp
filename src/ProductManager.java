import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Klasa zarządzająca produktami w magazynie.
 */
public class ProductManager {

    /** Lista przechowująca produkty w magazynie. */
    private List<Product> products;

    /** Konstruktor tworzący nowy obiekt ProductManager. */
    public ProductManager() {
        this.products = new ArrayList<>();
    }

    /**
     * Metoda dodająca produkt do magazynu.
     *
     * @param product Produkt do dodania.
     */
    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Produkt został dodany.");
    }
    /**
     * Metoda dodająca produkt do magazynu bez komentarza o dodanym produkcie.
     *
     * @param product Produkt do dodania.
     */
    public void addProductWithoutComment (Product product){
        products.add(product);
    }


    /**
     * Metoda usuwająca produkt z magazynu.
     *
     * @param product Produkt do usunięcia.
     */
    public void removeProduct(Product product) {
        if (products.contains(product)) {
            products.remove(product);
            System.out.println("Produkt został usunięty.");
        } else {
            System.out.println("Produkt nie istnieje w bazie.");
        }
    }

    /** Metoda wyświetlająca wszystkie produkty w magazynie. */
    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Brak produktów w magazynie.");
        } else {
            System.out.println("Stan magazynowy:");
            for (Product product : products) {
                System.out.println("ID: " + product.getId() + ", Nazwa: " + product.getProductName()
                        + ", Cena: " + product.getPrice() + ", Dostępna ilość: " + product.getQuantityAvailable());
            }
        }
    }

    /**
     * Metoda aktualizująca produkt w magazynie.
     *
     * @param productId      Identyfikator produktu do aktualizacji.
     * @param updatedProduct Zaktualizowany produkt.
     */
    public void updateProduct(int productId, Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getId() == productId) {
                products.set(i, updatedProduct);
                System.out.println("Produkt został zaktualizowany.");
                return;
            }
        }
        System.out.println("Produkt o podanym Id nie został znaleziony.");
    }

    /**
     * Metoda wyszukująca produkt po jego Id.
     *
     * @param productId Id produktu do znalezienia.
     * @return Obiekt Optional zawierający znaleziony produkt lub pusty, jeśli produkt nie został znaleziony.
     */
    public Optional<Product> findById(long productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
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
