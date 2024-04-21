import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Klasa reprezentująca koszyk zakupowy w sklepie.
 */

public class Cart {
    private List<Product> products; //Lista produktów w koszyku
    private ProductManager productManager; //Manager produktów

    /**
     * Konstruktor domyślny tworzący pusty koszyk.
     */
    public Cart() {
        this.products = new ArrayList<>();
    }
    /**
     * Konstruktor inicjalizujący koszyk i ustawiający manager produktów.
     *
     * @param productManager Manager produktów
     */
    public Cart(ProductManager productManager) {
        this.products = new ArrayList<>();
        this.productManager = productManager;
    }

    /**
     * Dodaje produkt do koszyka zakupowego
     *
     * @param product Produkt do dodania
     * @param quantity ilość produktu do dodania.
     * @throws ProductNotAvailableException Rzucany wyjątek, w przypadku braku produktu
     */
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

    /**
     * Usuwa produkt z koszyka w konkretnej ilości
     * @param product produkt do usunięcia z koszyka
     * @param quantity ilość produktu do usunięcia z koszyka
     */
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

    /**
     * Wyświetla zawartość koszyka.
     */
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

    /**
     * Zwraca listę produktów w koszyku.
     * @return Lista produktów w koszyku.
     */
    public List<Product> getCartItems() {
        return products;
    }

    /**
     * Oblicza całkowitą kwotę za produkty w koszyku
     * @return całkowita kwota za produkty w koszyku
     */
    public BigDecimal calculateTotalAmount() {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Product product : products) {
            BigDecimal productPrice = product.getPrice();
            BigDecimal quantity = BigDecimal.valueOf(getProductQuantityInCart(product));
            totalAmount = totalAmount.add(productPrice.multiply(quantity));
        }
        return totalAmount;
    }

    /**
     * Zwraca ilość konkretnego produktu w koszyku
     * @param product Produkt, którego ilość produktu zostanie zwrócona
     * @return ilość produktu w koszyku
     */
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