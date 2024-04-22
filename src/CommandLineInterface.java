import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.*;

/**
 * Klasa reprezentująca interface wiersza poleceń do obsługi sklepu.
 */
public class CommandLineInterface {
    private ProductManager productManager; //Manager produktu
    private Cart cart; //Koszyk
    private OrderProcessor orderProcessor; // Przetwarzanie zamówień
    private Scanner scanner; //Skaner do pobierania danych od użytkownika
    private PcComponents pcComponents;  // Komponenty komputera

    public CommandLineInterface(ProductManager productManager, Cart cart, OrderProcessor orderProcessor, Scanner scanner, PcComponents pcComponents) {
        this.productManager = productManager;
        this.cart = cart;
        this.orderProcessor = orderProcessor;
        this.scanner = scanner;
        this.pcComponents = pcComponents;
    }

    /**
     * Metoda rozpoczynająca interakcję z interface wiersza poleceń.
     *
     * @throws ProductNotAvailableException Rzucany wyjątek, gdy produkt jest niedostępny
     * @throws OrderProcessingException     Rzucany wyjatek, gdy wystąpi problem podczas przetwarzania zamówienia
     */
    public void start() throws ProductNotAvailableException, OrderProcessingException {
        System.out.println("Witaj w moim sklepie.");
        int command;
        do {
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
                    addToCart();
                    break;
                case 3:
                    removeProductFromCar();
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

    /**
     * Dodaje produkty do koszyka
     *
     * @throws ProductNotAvailableException rzucany wyjątek gdy produkt jest niedostępny
     */
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

    private void removeProductFromCar() {
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
        BigDecimal totalAmount = cart.calculateTotalAmount();
        int orderId = generateOrderId();
        ZoneId orderTime = ZoneId.systemDefault();
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

    private void displayComponents(List<PcComponents.Component> components) {
        for (int i = 0; i < components.size(); i++) {
            PcComponents.Component component = components.get(i);
            System.out.println((i + 1) + " - " + component.getProductName() + " - " + component.getPrice() + " zł");
        }
    }

    private void displayComponentsWithPrices(List<PcComponents.Component> components) {
        for (int i = 0; i < components.size(); i++) {
            PcComponents.Component component = components.get(i);
            System.out.println((i + 1) + " - " + component.getProductName() + " - " + component.getPrice() + " zł");
        }
    }

    private void setUpComputer() throws ProductNotAvailableException {
        int command;
        do {
            System.out.println("Skonfiguruj swój komputer. Cena komputera to 1500 zł (podstawa) plus" +
                    " cena każdego z komponentów");
            Computer computer = new Computer(999, "Spersonalizowany komputer", new BigDecimal("0.00"),
                    1, null, 8, 0, 0);
            BigDecimal totalCost = BigDecimal.valueOf(1500.00);
            // reszta kodu...
            // Pytanie użytkownika o dodanie komputera do koszyka
            System.out.println("Czy chcesz dodać ten komputer do koszyka? (T/N)");
            String addToCartChoice = scanner.next();
            if (addToCartChoice.equalsIgnoreCase("T")) {
                // Dodanie komponentów komputera do koszyka
                productManager.addProductWithoutComment(computer);
                cart.addProduct(computer, 1);
                System.out.println("Komputer został dodany do koszyka.");
            } else {
                System.out.println("Komputer nie został dodany do koszyka.");
            }
            System.out.println("Naciśnij 0, aby wrócić do menu głównego.");
            command = scanner.nextInt();
            scanner.nextLine();
        } while (command != 0);
    }

    private int extractCapacityFromString(String name) {
        String capacityString = name.split(" ")[0];
        capacityString = capacityString.replaceAll("\\D", "");
        return Integer.parseInt(capacityString);
    }

    private int extractPowerFromString(String name) {
        String powerString = name.split(" ")[0];
        powerString = powerString.replaceAll("\\D", "");
        return Integer.parseInt(powerString);
    }

    private int getUserChoice(List<PcComponents.Component> components) {
        int choice;
        do {
            choice = scanner.nextInt();
            if (choice < 1 || choice > components.size()) {
                System.out.println("Niepoprawny numer komponentu. Spróbuj ponownie.");
            }
        } while (choice < 1 || choice > components.size());
        return choice;
    }

    private void setUpSmartphone() throws ProductNotAvailableException {
        int command;
        do {
            System.out.println("Skonfiguruj swój telefon. Podstawowa cena to 500 zł + koszt poszczególnych komponentów");
            Smartphone smartphone = new Smartphone(888, "Spersonalizowany smartfon", BigDecimal.ZERO, 1, null, 0);
            BigDecimal totalCost = BigDecimal.valueOf(500.00);
            System.out.println("Wybierz kolor telefonu.");
            System.out.println("Dostępne kolory: ");
            Smartphone.displayAvailableColors();
            int colorChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            Smartphone.Color color = Smartphone.Color.values()[colorChoice - 1];
            smartphone.setColor(color);
            System.out.println("Wybierz pojemność baterii: ");
            System.out.println("1 - 3400 mAh - 39,90 zł.");
            System.out.println("2 - 3000 mAh - 31,90 zł.");
            System.out.println("3 - 2850 mAh - 28,99 zł.");
            int batteryChoice = scanner.nextInt();
            switch (batteryChoice) {
                case 1:
                    smartphone.setBatteryCapacity(3400);
                    totalCost = totalCost.add(BigDecimal.valueOf(39.90));
                    break;
                case 2:
                    smartphone.setBatteryCapacity(3000);
                    totalCost = totalCost.add(BigDecimal.valueOf(31.90));
                    break;
                case 3:
                    smartphone.setBatteryCapacity(2850);
                    totalCost = totalCost.add(BigDecimal.valueOf(28.99));
                    break;
                default:
                    System.out.println("Wybrano błędną pojemność baterii");
                    return;
            }
            scanner.nextLine();
            System.out.println("Wybierz akcesoria do telefonu (wprowadź '0' aby zakończyć):");
            while (true) {
                System.out.println("1 - Słuchawki Bluetooth JBL - 199,90 zł.");
                System.out.println("2 - Powerbank - 99,90 zł.");
                System.out.println("3 - Folia ochronna - 69,90 zł.");
                System.out.println("0 - Zakończenie konfiguracji");
                int accessoryChoice = scanner.nextInt();
                scanner.nextLine();
                if (accessoryChoice == 0) {
                    break;
                }
                switch (accessoryChoice) {
                    case 1:
                        smartphone.addAccessory("Słuchawki Bluetooth JBL");
                        totalCost = totalCost.add(BigDecimal.valueOf(199.90));
                        break;
                    case 2:
                        smartphone.addAccessory("Powerbank");
                        totalCost = totalCost.add(BigDecimal.valueOf(90.90));
                        break;
                    case 3:
                        smartphone.addAccessory("Folia ochronna");
                        totalCost = totalCost.add(BigDecimal.valueOf(69.90));
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
                    cart.addProduct(smartphone, 1);
                    System.out.println("Całkowita kwota za telefon i ewentualne akcesoria wynosi " + totalCost + " zł.");
                } catch (ProductNotAvailableException e) {
                    System.out.println("Nie można dodać telefonu do koszyka, jest niedostępny na magazynie");
                }
            } else {
                System.out.println("Telefon nie został dodany do koszyka");
            }
            System.out.println("Naciśnij 0, aby wrócić do menu głównego.");
            command = scanner.nextInt();
            scanner.nextLine();
        } while (command != 0);
    }
}