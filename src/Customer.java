public class Customer {
    private String customerName;
    private String customerLastName;
    private String email;
    private String phoneNumber;
    private String address;

    public Customer(String customerName, String customerLastName, String email, String phoneNumber, String address) {
        this.customerName = customerName;
        this.customerLastName = customerLastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Imię='" + customerName + '\'' +
                ", nazwisko='" + customerLastName + '\'' +
                ", adres e-mail='" + email + '\'' +
                ", numer telefonu='" + phoneNumber + '\'' +
                ", adres wysyłki='" + address + '\'' +
                '}';
    }
}
