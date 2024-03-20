import java.util.Scanner;
public class Testowa {
    public static void main(String[] args) throws OrderProcessingException, ProductNotAvailableException {
        ProductManager productManager = new ProductManager();
        Cart cart = new Cart(productManager);
        OrderProcessor orderProcessor = new OrderProcessor();
        Scanner scanner = new Scanner(System.in);
        CommandLineInterface cli = new CommandLineInterface(productManager, cart, orderProcessor, scanner);
        Smartphone smartphone = new Smartphone(1, "Smartphone model XYZ", 999.99, 10, Smartphone.Color.BLACK, 4000);
        productManager.addProduct(smartphone);
        cli.start();
    }
}
