import java.awt.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;


public class CommandLineInterface {
    private ProductManager productManager;
    private Cart cart;
    private OrderProcessor orderProcessor;
    private Scanner scanner;

    public CommandLineInterface(ProductManager productManager, Cart cart, OrderProcessor orderProcessor, Scanner scanner) {
        this.productManager = productManager;
        this.cart = cart;
        this.orderProcessor = orderProcessor;
        this.scanner = scanner;
    }

    public void start() throws ProductNotAvailableException, OrderProcessingException {
        System.out.println("Witaj w moim sklepie.");
        int command;
        do {
            System.out.println("Wybierz odpowiednią komendę:");
            System.out.println("Wybierz jedną z opcji:");
            System.out.println("1 - Przeglądaj dostępne produkty w sklepie.");
            System.out.println("2 - Dodaj produkt do koszyka");
            System.out.println("3 - Usuń produkt z koszyka");
            System.out.println("4 - Stwórz własny komputer");
            System.out.println("5 - Stwórz własny telefon");
            System.out.println("6 - Wyświetl zawartość koszyka.");
            System.out.println("7 - Złóż zamówienie.");
            System.out.println("8 - Wyjście");
            command = scanner.nextInt();
            scanner.nextLine();
            switch (command) {
                case 1:
                    displayAvailableProducts();
                    break;
                case 2:
                    System.out.println("Podaj ID produktu, który chcesz dodać do koszyka:");
                    int productId = scanner.nextInt();
                    scanner.nextLine();

                    Optional<Product> optionalProductToAdd = productManager.findById(productId);
                    if (optionalProductToAdd.isPresent()) {
                        Product productToAdd = optionalProductToAdd.get();
                        System.out.println("Podaj ilość produktów do dodania:");
                        int quantity = scanner.nextInt();
                        scanner.nextLine(); // Dla oczyszczenia bufora
                        if (productToAdd.getQuantityAvailable() < quantity) {
                            System.out.println("Nie ma wystarczającej ilości produktów na magazynie");
                            return;
                        }
                        try {
                            cart.addProduct(productToAdd, quantity);
                            productManager.decrementQuantity(productToAdd.getId(), 1);
                            System.out.println("Produkt został dodany do koszyka.");
                        } catch (ProductNotAvailableException e) {
                            System.out.println("Nie można dodać produktu do koszyka, jest niedostępny na magazynie");
                        }
                    } else {
                        System.out.println("Produkt o podanym ID nie został znaleziony.");
                    }

                    break;
                case 3:
                    System.out.println("Podaj ID produktu do usunięcia z koszyka:");
                    int productIdToRemove = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Product> optionalProductToRemove = productManager.findById(productIdToRemove);
                    if (optionalProductToRemove.isPresent()) {
                        Product productToRemove = optionalProductToRemove.get();
                        System.out.println("Podaj ilość sztuk do usunięcia:");
                        int quantityToRemove = scanner.nextInt();
                        cart.removeProduct(productToRemove, quantityToRemove);
                    } else {
                        System.out.println("Nie można znaleźć produktu o podanym ID");
                    }
                    break;

                case 4:
                    setUpComputer();
                    break;
                case 5:
                    setUpSmartphone();
                    break;
                case 6:
                    cart.viewCart();
                    break;
                case 7:
                    placeOrder();
                    break;
                case 8:
                    System.out.println("Dziękujemy za skorzystanie z naszego sklepu. Do zobaczenia"
                            + " przy następnych zakupach");
                    break;
                default:
                    System.out.println("Nieznana komenda. Spróbuj ponownie");
            }
        } while (command != 8);
    }

    private void addToCart() throws ProductNotAvailableException {
        System.out.println("Podaj Id produktu, który chcesz dodać do koszyka: ");
        int productId = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        Optional<Product> optionalProduct = productManager.findById(productId);
        if (optionalProduct.isPresent()) {
            Product productToAdd = optionalProduct.get();
            try {
                cart.addProduct(productToAdd, 1);
                System.out.println("Produkt został dodany do koszyka");
            } catch (ProductNotAvailableException e) {
                System.out.println("Nie można dodać produktu do koszyka, jest niedostępny na magazynie");
            }
        } else {
            System.out.println("Produkt o podanym ID nie istnieje");
        }
    }


    private void viewCart() {
        cart.viewCart();
    }

    private void placeOrder() {
        System.out.println("Podaj swoje dane użytkownika");
        System.out.println("Podaj imię:");
        String customerName = scanner.nextLine();
        System.out.println("Podaj nazwisko:");
        String customerLastName = scanner.nextLine();
        System.out.println("Podaj adres e-mail :");
        String email = scanner.nextLine();
        System.out.println("Podaj numer telefonu :");
        String phoneNumber = scanner.nextLine();
        System.out.println("Podaj adres dostawy zaczynając od kodu pocztowego, miejscowości, " + " ulicy, numeru domu, numeru mieszkania (jeśli jest):");
        String address = scanner.nextLine();
        int loyaltyPoints = 0;

        Customer customer = new Customer(customerName, customerLastName, email, phoneNumber, address, loyaltyPoints);

        List<Product> cartItems = cart.getCartItems();

        double totalAmount = cart.calculateTotalAmount();

        int orderId = generateOrderId();

        LocalDateTime orderTime = LocalDateTime.now();

        Order order = new Order(orderId, customer, cartItems, totalAmount, orderTime);

        orderProcessor.processOrder(order);

        for (Product product : cartItems) {
            productManager.decrementQuantity(product.getId(), 1);
        }
        System.out.println("Zamówienie zostało złożone. Dziękujemy!");
    }


    private int generateOrderId() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    public void displayAvailableProducts() {
        productManager.displayProducts();
    }

    private void setUpComputer() throws ProductNotAvailableException {
        System.out.println("Skonfiguruj swój komputer. Cena komputeraz to 1500 zł (podstawa) plus" +
                "cena każdego z komponentów");
        Computer computer = new Computer();
        double totalCost = 1500.00;
        System.out.println("Wybierz procesor: ");
        System.out.println("1 - Intel Core i5-13600KF - 1199.00 zł");
        System.out.println("2 - Intel Core i7-14700KF - 1719.00 zł");
        System.out.println("3 - Intel Core i9-14900K - 2699.00 zł");
        int processorChoice = scanner.nextInt();
        switch (processorChoice) {
            case 1:
                computer.setProcessor("Intel Core i5-13600KF");
                totalCost += 1199.00;
                break;
            case 2:
                computer.setProcessor("Inte Core i7-14700KF");
                totalCost += 1719.00;
                break;
            case 3:
                computer.setProcessor("Intel Core i9-14900K");
                totalCost += 2699.00;
                break;
            default:
                System.out.println("Niepoprawny wybór procesora");
                return;
        }
        System.out.println("Wybierz ilość pamięci RAM: ");
        System.out.println("1 - DDR5 Fury 16GB - 325.00 zł");
        System.out.println("2 - DDR5 Lexar 32GB - 559,99 zł");
        System.out.println("3 - DDR5 Fury 64GB - 919,00 zł");
        int ramChoice = scanner.nextInt();
        switch (ramChoice) {
            case 1:
                computer.setRam(16);
                totalCost += 325.00;
                break;
            case 2:
                computer.setRam(32);
                totalCost += 559.99;
                break;
            case 3:
                computer.setRam(64);
                totalCost += 919.00;
                break;
            default:
                System.out.println("Wybrano nieoprawną ilość RAM");
                return;
        }
        System.out.println("Wybierz pojemność dysku SSD: ");
        System.out.println("1 - SSD GOODRAM 512 GB - 144,00 zł");
        System.out.println("2 - SSD Lexar 1 TB - 299,00 zł");
        System.out.println("3 - SSD Lexar 2 TB - 599,00 zł");
        int ssdChoice = scanner.nextInt();
        switch (ssdChoice) {
            case 1:
                computer.setSsdDriveCapacity(512);
                totalCost += 144.00;
                break;
            case 2:
                computer.setSsdDriveCapacity(1000);
                totalCost += 299.00;
                break;
            case 3:
                computer.setSsdDriveCapacity(2000);
                totalCost += 599.00;
                break;
            default:
                System.out.println("Wybrano niepoprawną wielkość dysku SSD");
                return;
        }
        System.out.println("Wybierz moc zasilacza: ");
        System.out.println("1 - MSI MAG 650 W - 299.00 zł");
        System.out.println("2 - Endorfy Vero 700 W - 319,00 zł");
        System.out.println("3 - Corsair 750 W - 649,00 zł");
        int chargerChoice = scanner.nextInt();
        switch (chargerChoice) {
            case 1:
                computer.setCharger(650);
                totalCost += 299.00;
                break;
            case 2:
                computer.setCharger(700);
                totalCost += 319.00;
                break;
            case 3:
                computer.setCharger(750);
                totalCost += 649.00;
                break;
            default:
                System.out.println("Wybrano niepoprawną moc zasilacza");
                return;
        }
        scanner.nextLine();
        System.out.println("Czy chcesz dodać komputer do koszyka? (T/N)");
        String addComputerToCart = scanner.nextLine();
        if (addComputerToCart.equalsIgnoreCase("T")) {
            computer.setPrice(totalCost);
            try {
                cart.addProduct(computer, 1); // Poprawka na dodanie komputera
                System.out.println("Całkowita kwota za komputer wynosi " + totalCost + " zł.");
            } catch (ProductNotAvailableException e) {
                System.out.println("Nie można dodać komputera do koszyka, jest niedostępny na magazynie");
            }
        } else {
            System.out.println("Nie dodano produktu do koszyka");
        }
    }

    private void setUpSmartphone() throws ProductNotAvailableException {
        Smartphone smartphone = new Smartphone();
        double totalCost = 500.0;
        System.out.println("Skonfiguruj swój telefon. Podstawowa cena to 500 zł + koszt poszczególnych komponentów");
        System.out.println("Wybierz kolor telefonu.");
        System.out.println("Dostępne kolory: ");
        Smartphone.displayAvailableColors();
        int colorChoice = scanner.nextInt();
        Smartphone.Color color = Smartphone.Color.values()[colorChoice - 1];
        smartphone.setColor(color);
        System.out.println("Wybierz pojemność baterii: ");
        System.out.println("1 -3400 mAh - 39,90 zł.");
        System.out.println("2 - 3000 mAh - 31,90 zł.");
        System.out.println("3 - 2850 mAH - 28,99 zł.");
        int batteryChoice = scanner.nextInt();
        switch (batteryChoice) {
            case 1:
                smartphone.setBatteryCapacity(3400);
                totalCost += 39.90;
                break;
            case 2:
                smartphone.setBatteryCapacity(3000);
                totalCost += 31.90;
                break;
            case 3:
                smartphone.setBatteryCapacity(2850);
                totalCost += 28.99;
                break;
            default:
                System.out.println("Wybrano błędną pojemność baterii");
                return;
        }
        scanner.nextLine(); // Konsumuj znak nowej linii
        System.out.println("Wybierz akcesoria do telefonu (wprowadź '0' aby zakończyć):");
        while (true) {
            System.out.println("1 - Słuchawki Bluetooth JBL - 199,90 zł.");
            System.out.println("2 - Powerbank - 99,90 zł.");
            System.out.println("3 - Folia ochronna - 69,90 zł.");
            int accessoryChoice = scanner.nextInt();
            scanner.nextLine();
            if (accessoryChoice == 0) {
                break;
            }
            switch (accessoryChoice) {
                case 1:
                    smartphone.addAccessory("Słuchawki Bluetooth JBL");
                    totalCost += 199.90;
                    break;
                case 2:
                    smartphone.addAccessory("Powerbank");
                    totalCost += 90.90;
                    break;
                case 3:
                    smartphone.addAccessory("Folia ochronna");
                    totalCost += 69.90;
                    break;
                default:
                    System.out.println("Wybrano błędne akcesorium.");
            }
        }

        System.out.println("Konfiguracja telefonu zakończona. Wybrano:");
        System.out.println("Kolor: " + smartphone.getColor());
        System.out.println("Pojemność baterii: " + smartphone.getBatteryCapacity() + " mAh");
        System.out.println("Akcesoria:");
        for (String accessory : smartphone.getAccessories()) {
            System.out.println("- " + accessory);
        }
        System.out.println("Łączna cena: " + totalCost + " zł");


        System.out.println("Wybrane opcje:");
        System.out.println("Kolor: " + smartphone.getColor());
        System.out.println("Pojemność baterii: " + smartphone.getBatteryCapacity() + " mAh");
        if (!smartphone.getAccessories().isEmpty()) {
            System.out.println("Akcesoria: " + smartphone.getAccessories());
        }
        System.out.println("Całkowity koszt: " + totalCost + " zł");
        System.out.println("Czy chcesz dodać produkt do koszyka? (T/N)");
        String addProductToCart = scanner.nextLine();
        if (addProductToCart.equalsIgnoreCase("T")) {
            smartphone.setPrice(totalCost);
            try {
                cart.addProduct(smartphone, 1); // Poprawka na dodanie smartfona
                System.out.println("Całkowita kwota za telefon i ewentualne akcesoria wynosi " + totalCost + " zł.");
            } catch (ProductNotAvailableException e) {
                System.out.println("Nie można dodać telefonu do koszyka, jest niedostępny na magazynie");
            }
        } else {
            System.out.println("Telefon nie został dodany do koszyka");
        }
    }
}

