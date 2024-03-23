/**
 * Wyjątek rzucany, gdy produkt nie jest dostępny.
 */
public class ProductNotAvailableException extends Exception {

    /**
     * Konstruktor klasy ProductNotAvailableException.
     * @param message Komunikat błędu wyjątku
     */
    public ProductNotAvailableException(String message) {
        super(message);
    }
}
