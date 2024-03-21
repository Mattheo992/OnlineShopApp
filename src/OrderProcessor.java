import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderProcessor {
    private final ExecutorService EXECUTOR;

    public OrderProcessor() {
        this.EXECUTOR = Executors.newFixedThreadPool(5);
    }

    public void processOrder(Order order) {
        EXECUTOR.submit(new OrderProcessingTask(order));
    }


    private static class OrderProcessingTask implements Runnable {
        private final Order order;

        public OrderProcessingTask(Order order) {
            this.order = order;
        }

        @Override
        public void run() {
            List<Product> products = order.getProducts();
            double total = calculateOrderTotal(products);
            Customer customer = order.getCustomer();
            System.out.println("Twoje zamówienie jest przetwarzane...");
            System.out.println("Pomyślnie przetworzono zamówienie dla " + customer.getCustomerName()
                    + " " + customer.getCustomerLastName());
            System.out.println("Adres dostawy: " + customer.getAddress());
            System.out.println("Kwota do zapłaty za zamówienie " + order.getOrderId() + " wynosi "
                    + total + " zł.");
            generateInvoice(order, total);
            double discountAmount = calculateLoyaltyPoints(total, customer.getLoyaltyPoints());
            total -= discountAmount;
            System.out.println("Kwota po rabacie wynosi: " + total);

            OrderSavedToTxt.saveOrdersToTxtFile(Collections.singletonList(order));
        }

        private double calculateOrderTotal(List<Product> products) {
            double total = 0.0;
            for (Product product : products) {
                total += product.getPrice();
            }
            return total;
        }

        private double calculateLoyaltyPoints(double totalAmount, int loyaltyPoints) {
            double discountRate = 0.1;
            double discount = loyaltyPoints * discountRate;
            if (discount > totalAmount) {
                discount = totalAmount;
            }
            return discount;
        }

        private void generateInvoice(Order order, double total) {
            System.out.println("Generowanie faktury...");
            System.out.println("Faktura dla zamówienia o numerze: " + order.getOrderId());
            System.out.println("Data zamówienia: " + LocalDateTime.now());
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
