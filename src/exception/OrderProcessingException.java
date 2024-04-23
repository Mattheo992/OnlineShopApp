package exception;

/**
 * Wyjątek rzucany podczas przetwarzania zamówienia.
 */
public class OrderProcessingException extends Exception {

    /**
     * Konstruktor klasy exception.OrderProcessingException.
     *
     * @param message Komunikat błędu wyjątku
     */
    public OrderProcessingException(String message) {
        super(message);
    }
}