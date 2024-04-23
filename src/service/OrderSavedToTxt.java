package service;

import model.Order;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa służąca do zapisywania zamówień do pliku tekstowego.
 */
public class OrderSavedToTxt {
    /** Ścieżka do pliku tekstowego, do którego zostaną zapisane zamówienia. */
    private static final String TXT_FILE_PATH = "src/orders.txt";

    /**
     * Metoda zapisująca zamówienia do pliku tekstowego.
     *
     * @param orders Lista zamówień do zapisania.
     */
    public static void saveOrdersToTxtFile(List<Order> orders) {
        try (FileWriter writer = new FileWriter(TXT_FILE_PATH)) {
            String ordersText = orders.stream()
                    .map(OrderSavedToTxt::formatOrder)
                    .collect(Collectors.joining("\n"));

            writer.write(ordersText);
            System.out.println("Zamówienia zostały zapisane do pliku " + TXT_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Wystąpił błąd podczas zapisywania zamówień: " + e.getMessage());
        }
    }

    /**
     * Metoda formatująca pojedyncze zamówienie do postaci tekstowej.
     *
     * @param order Zamówienie do sformatowania.
     * @return Sformatowane zamówienie jako tekst.
     */
    private static String formatOrder(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID zamówienia: ").append(order.getOrderId()).append("\n");
        sb.append("Zamówione produkty: ").append(order.getProducts()).append("\n");
        sb.append("Wartość zamówienia: ").append(order.getTotalAmount()).append("\n");
        sb.append("Imię klienta: ").append(order.getCustomer().getCustomerName()).append("\n");
        sb.append("Nazwisko: ").append(order.getCustomer().getCustomerLastName()).append("\n");
        sb.append("Adres klienta: ").append(order.getCustomer().getAddress()).append("\n");
        sb.append("Zamówienie złożono: ").append(order.getOrderTime()).append("\n");
        sb.append("\n");
        return sb.toString();
    }
}
