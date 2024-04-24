import exception.OrderProcessingException;
import exception.ProductNotAvailableException;
import model.Computer;
import model.Electronics;
import model.PcComponents;
import model.Smartphone;
import repository.ProductManager;
import service.Cart;
import service.CommandLineInterface;
import service.OrderProcessor;

import java.math.BigDecimal;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws OrderProcessingException, ProductNotAvailableException {
        ProductManager productManager = new ProductManager();
        Cart cart = new Cart(productManager);
        OrderProcessor orderProcessor = new OrderProcessor();
        Scanner scanner = new Scanner(System.in);
        PcComponents pcComponents = new PcComponents();
        CommandLineInterface cli = new CommandLineInterface(productManager, cart, orderProcessor, scanner, pcComponents);
        Smartphone smartphone1 = new Smartphone(1, "telefon Samsung Galaxy S22 8/128", new BigDecimal("2399.99"), 7, Smartphone.Color.BLACK, 3700);
        Smartphone smartphone2 = new Smartphone(2, "telefon Apple Iphone 15 128GB", new BigDecimal("3699.99"), 3, Smartphone.Color.SILVER, 4000);
        Smartphone smartphone3 = new Smartphone(3, "telefon Xiaomi Redmi Note 11S 6/128GB", new BigDecimal("99.16"), 16, Smartphone.Color.WHITE, 3500);
        Smartphone smartphone4 = new Smartphone(4, "telefon Nothing Phone (2a) 5G 12/256GB Black 120Hz", new BigDecimal("1679.00"), 2, Smartphone.Color.BLACK, 4000);
        Smartphone smartphone5 = new Smartphone(5, "telefon Xiaomi POCO X6 Pro 5G 12/512GB Black", new BigDecimal("1699.99"), 24, Smartphone.Color.BLUE, 4250);
        Smartphone smartphone6 = new Smartphone(6, "telefon Motorola edge 30 neo 5G 8/128GB", new BigDecimal("895.49"), 6, Smartphone.Color.RED, 5000);
        productManager.addProductWithoutComment(smartphone1);
        productManager.addProductWithoutComment(smartphone2);
        productManager.addProductWithoutComment(smartphone3);
        productManager.addProductWithoutComment(smartphone4);
        productManager.addProductWithoutComment(smartphone5);
        productManager.addProductWithoutComment(smartphone6);
        Computer computer1 = new Computer(7, "komputer G4M3R HERO i5-14400F", new BigDecimal("6300.00"), 4,
                "Intel Core i5-14400F", 32, 1000, 750);
        Computer computer2 = new Computer(8, "komputer Dell Vostro 3710 SFF i5-12400/16GB/512/Win11P",
                new BigDecimal("3099.00"), 6, "Intel Core i5-12400", 16, 512, 180);
        Computer computer3 = new Computer(9, "komputer X-kom H&O 200 MT i5-14400", new BigDecimal("2699.00"), 1,
                "Intel Core i5-14400", 16, 512, 550);
        Computer computer4 = new Computer(10, "komputer Silver Monkey X Battlestation ARGB i5-10400F", new BigDecimal("4350.99"),
                7, "Intel Core i5-10400F", 16, 1000, 550);
        Computer computer5 = new Computer(11, "komputer Blackview MP80 N97", new BigDecimal("1349.00"), 2,
                "Intel Processor N97", 16, 1000, 500);
        Computer computer6 = new Computer(12, "komputer G4M3R HERO PLUS i9-14900KF",
                new BigDecimal("18700.00"), 1, "Intel Core i9-14900KF", 96, 2000, 1000);
        productManager.addProductWithoutComment(computer1);
        productManager.addProductWithoutComment(computer2);
        productManager.addProductWithoutComment(computer3);
        productManager.addProductWithoutComment(computer4);
        productManager.addProductWithoutComment(computer5);
        productManager.addProductWithoutComment(computer6);
        Electronics keyboard1 = new Electronics(13, "klawiatura SteelSeries Apex 9 TKL", new BigDecimal("599.00"), 23);
        Electronics keyboard2 = new Electronics(14, "klawiatura Genesis Thor 401 RGB", new BigDecimal("229.00"), 17);
        productManager.addProductWithoutComment(keyboard1);
        productManager.addProductWithoutComment(keyboard2);
        Electronics headphones = new Electronics(15, "Słuchawki Bluetooth JBL", new BigDecimal("199.00"), 5);
        Electronics powerbank = new Electronics(16, "Powerbank", new BigDecimal("99.90"), 17);
        Electronics protector = new Electronics(17, "Folia ochronna na telefon", new BigDecimal("69.90"), 65);
        productManager.addProductWithoutComment(headphones);
        productManager.addProductWithoutComment(powerbank);
        productManager.addProductWithoutComment(protector);
        cli.start();
    }
}
