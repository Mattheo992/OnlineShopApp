import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OrderSavedToTxt {
    private static final String TXT_FILE_PATH = "orders.txt";
    public static void saveOrdersToTxtFile(List<Order> orders){
        try(FileWriter writer = new FileWriter(TXT_FILE_PATH)) {
            for(Order order : orders){
                String orderText = formatOrder(order);
                writer.write(orderText);
            }
            System.out.println("Zamówienie zostało zapisane do pliku " + TXT_FILE_PATH);
        } catch (IOException e) {
             System.err.println("Wystąpił błąd podczas zapisywania zamówień: " + e.getMessage());
        }
    }
    private static String formatOrder(Order order){
        StringBuilder sb = new StringBuilder();
        sb.append("ID zamówienia: ").append(order.getOrderId()).append("\n");
        sb.append("Zamówione produkty: ").append(order.getProducts()).append("\n");
        sb.append("Wartość zamówienia: ").append(order.getTotalAmount()).append("\n");
        sb.append("Klient: ").append(order.getCustomer().getCustomerName()).append("\n");
        sb.append("Adres klienta: ").append(order.getCustomer().getAddress()).append("\n");
        sb.append("Zamówienie złożono: ").append(order.getOrderTime()).append("\n");
        sb.append("\n");
        return sb.toString();
    }
}
