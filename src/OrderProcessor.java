import java.time.LocalDateTime;

public class OrderProcessor {
    public void processOrder(Order order) {
        System.out.println("Twoje zamówienie jest przetwarzane...");
        System.out.println("Pomyślnie przetworzono zamówienie dla " + order.getCustomer().getCustomerName()
                + " " + order.getCustomer().getCustomerLastName());
        System.out.println("Kwota do zapłaty za zamówienie " + order.getOrderId() + " wynosi "
                + order.getTotalAmount() + " zł.");
    }

    private void generateInvoice(Order order) {
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
        System.out.println("Łączna suma zamówienia: " + order.getTotalAmount());
        System.out.println("Pomyślnie wygenerowano fakturę");
    }
}
