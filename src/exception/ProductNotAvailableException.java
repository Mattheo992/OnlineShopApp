package exception;

/**
 * Wyjątek rzucany, gdy produkt nie jest dostępny.
 */
public class ProductNotAvailableException extends Exception {

    /**
     * Konstruktor klasy exception.ProductNotAvailableException.
     * @param message Komunikat błędu wyjątku
     */
    public ProductNotAvailableException(String message) {
        super(message);
    }
}
