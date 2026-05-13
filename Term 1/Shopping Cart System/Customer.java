import java.util.Random; // To create a random long number
import java.util.Scanner;

public class Customer {
    private String firstName;
    private String lastName;
    private Address address;
    private Email email;
    private final long customerId; // "final" means this variable will never change

    private ShoppingCart customerCart;

    public Customer() {
        setFirstName(); // User enters their first and last name in the console
        setLastName();

        email = new Email(); // Same as for the address below - the constructor is empty, user sets the email
                             // themselves
        address = new Address(); // This is empty for now because the customer will enter the details themselves.
        customerId = makeCustomerId(); // Creating a random customer ID
        customerCart = new ShoppingCart(); // Creating an empty shopping cart for the customer
    }

    public Email getClassEmail() // If we need to use some EMAIL CLASS functions, do .getEmail().setEmail()
    {
        return email;
    }

    public Address getClassAddress() // Same for address class: .getAddress().setAddressLine1()
    {
        return address;
    }

    public ShoppingCart getCart() {
        return customerCart;
    }

    public long getId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private long makeCustomerId() {
        long id = new Random().nextLong(Long.MAX_VALUE); // From 0 to long's max value
        return id;
    }

    private static final Scanner inputScanner = new Scanner(System.in); // Scanner object initialization

    // Getting input and saving it to first and last names
    public void setFirstName() {
        System.out.println("Enter your first name:");
        String userInput = inputScanner.nextLine();
        this.firstName = userInput;
    }

    public void setLastName() {
        System.out.println("Enter your last name:");
        String userInput = inputScanner.nextLine();
        this.lastName = userInput;
    }
}