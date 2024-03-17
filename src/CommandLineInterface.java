import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class CommandLineInterface {
    private ProductManager productManager;
    private Cart cart;
    private OrderProcessor orderProcessor;
    private Scanner scanner;

    public CommandLineInterface(ProductManager productManager, Cart cart, OrderProcessor orderProcessor, Scanner scanner) {
        this.productManager = new ProductManager();
        this.cart = new Cart();
        this.orderProcessor = new OrderProcessor();
        this.scanner = new Scanner(System.in);
    }

    public void start() throws ProductNotAvailableException, OrderProcessingException {
        System.out.println("Witaj w moim sklepie.");
        System.out.println("Wybierz jedną z opcji:");
        System.out.println("1 - Przeglądaj dostępne produkty w sklepie.");
        System.out.println("2 - Dodaj produkt do koszyka");
        System.out.println("3 - Usuń produkt z koszyka");
        System.out.println("4 - Wyświetl zawartość koszyka.");
        System.out.println("5 - Złóż zamówienie.");
        System.out.println("6 - Wyjście");

        int command;
        do {
            System.out.println("Wybierz odpowiednią komendę:");
            command = scanner.nextInt();
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
                        cart.addProduct(productToAdd);
                        System.out.println("Produkt został dodany do koszyka.");
                    } else {
                        System.out.println("Produkt o podanym ID nie został znaleziony.");
                    }
                    break;
                case 3:
                    System.out.println("Podaj ID produktu do usunięcia z koszyka:");
                    int productIdToRemove = scanner.nextInt();
                    Optional<Product> optionalProductToRemove = productManager.findById(productIdToRemove);
                    if (optionalProductToRemove.isPresent()) {
                        Product productToRemove = optionalProductToRemove.get();
                        cart.removeProduct(productToRemove);
                    } else {
                        System.out.println("Nie można znaleźć produktu o podanym ID");
                    }
                    break;

                case 4:
                    cart.viewCart();
                    break;
                case 5:
                    cart.placeOrder();
                    break;
                case 6:
                    System.out.println("Dziękujemy za skorzystanie z naszego sklepu. Do zobaczenia" + "przy następnych zakupach");
                    break;
                default:
                    System.out.println("Nieznana komenda. Spróbuj ponownie");
            }
        } while (command != 6);
    }

    private void addToCart() throws ProductNotAvailableException {
        System.out.println("Podaj Id produktu, który chcesz dodać do koszyka: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Skonsumuj znak nowej linii

        Optional<Product> optionalProduct = productManager.findById(productId);
        if (optionalProduct.isPresent()) {
            Product productToAdd = optionalProduct.get();
            cart.addProduct(productToAdd);
            System.out.println("Produkt został dodany do koszyka");
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
        String customerName = scanner.next();
        System.out.println("Podaj nazwisko:");
        String customerLastName = scanner.next();
        System.out.println("Podaj adres e-mail :");
        String email = scanner.next();
        System.out.println("Podaj numer telefonu :");
        String phoneNumber = scanner.next();
        System.out.println("Podaj adres dostawy zaczynając od kodu pocztowego, miejscowości" + " ulicy, numeru domu, numeru mieszkania (jeśli jest):");
        String address = scanner.next();
        int loyaltyPoints = 0;
        Customer customer = new Customer(customerName, customerLastName, email, phoneNumber, address, loyaltyPoints);

        List<Product> cartItems = cart.getCartItems();

        double totalAmount = cart.calculateTotalAmount();

        int orderId = generateOrderId();

        LocalDateTime orderTime = LocalDateTime.now();

        Order order = new Order(orderId, customer, cartItems, totalAmount, orderTime);

        orderProcessor.processOrder(order);
        System.out.println("Zamówienie zostało złożone. Dziękujemy!");
        scanner.close();
    }

    private int generateOrderId() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    public void displayAvailableProducts() {
        productManager.displayProducts();
    }

}
