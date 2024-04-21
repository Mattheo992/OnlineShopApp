import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Klasa odpowiedzialna za przetwarzanie zamówień.
 */
public class OrderProcessor {
    /** ExecutorService do zarządzania wątkami przetwarzania zamówień. */
    private final ExecutorService EXECUTOR;

    /**
     * Konstruktor tworzący nowy obiekt OrderProcessor.
     */
    public OrderProcessor() {
        this.EXECUTOR = Executors.newFixedThreadPool(5);
    }

    /**
     * Metoda przetwarzająca zamówienie.
     *
     * @param order Zamówienie do przetworzenia.
     */
    public void processOrder(Order order) {
        EXECUTOR.submit(new OrderProcessingTask(order));
    }

    /**
     * Wewnętrzna klasa reprezentująca zadanie przetwarzania zamówienia.
     */
    private static class OrderProcessingTask implements Runnable {
        /** Zamówienie do przetworzenia. */
        private final Order order;

        /**
         * Konstruktor tworzący nowy obiekt OrderProcessingTask.
         *
         * @param order Zamówienie do przetworzenia.
         */
        public OrderProcessingTask(Order order) {
            this.order = order;
        }

        /**
         * Metoda wykonująca zadanie przetwarzania zamówienia.
         */
        @Override
        public void run() {
            List<Product> products = order.getProducts();
            BigDecimal total = calculateOrderTotal(products);
            Customer customer = order.getCustomer();
            System.out.println("Twoje zamówienie jest przetwarzane...");
            System.out.println("Pomyślnie przetworzono zamówienie dla " + customer.getCustomerName()
                    + " " + customer.getCustomerLastName());
            System.out.println("Adres dostawy: " + customer.getAddress());
            System.out.println("Kwota do zapłaty za zamówienie " + order.getOrderId() + " wynosi "
                    + total + " zł.");
            generateInvoice(order, total.doubleValue());

            BigDecimal discountAmount = BigDecimal.valueOf(calculateLoyaltyPoints(total.doubleValue(), customer.getLoyaltyPoints()));

            total = total.subtract(discountAmount);

            System.out.println("Kwota po rabacie wynosi: " + total);

            OrderSavedToTxt.saveOrdersToTxtFile(Collections.singletonList(order));
        }

        /**
         * Metoda obliczająca łączną wartość zamówienia.
         *
         * @param products Lista produktów w zamówieniu.
         * @return Łączna wartość zamówienia.
         */
        private BigDecimal calculateOrderTotal(List<Product> products) {
            BigDecimal total = BigDecimal.ZERO;
            for (Product product : products) {
                total = total.add(product.getPrice());
            }
            return total;
        }

        /**
         * Metoda obliczająca zniżkę na podstawie punktów lojalnościowych klienta.
         *
         * @param totalAmount    Łączna kwota zamówienia.
         * @param loyaltyPoints  Punkty lojalnościowe klienta.
         * @return Wartość zniżki.
         */
        private double calculateLoyaltyPoints(double totalAmount, int loyaltyPoints) {
            double discountRate = 0.1;
            double discount = loyaltyPoints * discountRate;
            if (discount > totalAmount) {
                discount = totalAmount;
            }
            return discount;
        }

        /**
         * Metoda generująca fakturę dla zamówienia.
         *
         * @param order Zamówienie.
         * @param total Łączna kwota zamówienia.
         */
        private void generateInvoice(Order order, double total) {
            System.out.println("Generowanie faktury...");
            System.out.println("Faktura dla zamówienia o numerze: " + order.getOrderId());
            System.out.println("Data zamówienia: " + ZonedDateTime.now());
            System.out.println("Klient: " + order.getCustomer().getCustomerName() + " "
                    + order.getCustomer().getCustomerLastName());
            System.out.println("Adres dostawy: " + order.getCustomer().getAddress());
            System.out.println("Produkty:");
            for (Product product : order.getProducts()) {
                System.out.println("- " + product.getProductName() + " Cena: " + product.getPrice() + " zł");
            }
            System.out.println("Łączna suma zamówienia: " + total);
            System.out.println("Pomyślnie wygenerowano fakturę");
        }
    }
}
