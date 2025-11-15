import java.util.Scanner; // To scan the imp
import java.time.LocalDate; // To be able to pass in LocalDate type of variables into Email methods

public class Email {
    private String email;
    private String allowed = "abcdefghijklmnopqrstuvwxyz1234567890@.";

    public Email() {
        // A loop that sets the email only if it's valid
        do {
            System.out.println("Enter your email:");
            String userInput = inputScanner.nextLine(); // Read in what the user types
            boolean result = true;
            for (char c : userInput.toLowerCase().toCharArray()) {
                if (allowed.indexOf(c) == -1 || userInput.indexOf('@') == -1) { // If any of the characters in the
                                                                                // allowed ones are NOT in the email or
                                                                                // if @ is absent - invalid email
                    result = false;
                    break;
                }
            }
            if (result) {
                email = userInput;
                break; // Exiting the loop
            }
            System.out.println("The email is invalid.");
        } while (true);
    }

    public String getStringEmail() {
        return email;
    }

    private static final Scanner inputScanner = new Scanner(System.in);

    // A method for sending a positive email of confirmation - the order was
    // successful
    public void positiveEmail(String email, double cartPrice, String add1, String add2, String city, String country,
            String eircode, int trackNum, String orderStatus) {
        System.out.println("");
        System.out.println("Confirmation Email -----");
        System.out.println("To: " + email);
        System.out.println("Thank you for successfully purchasing items from our shop!");
        System.out.println("The total cost of your order is: €" + cartPrice);
        System.out.println("Everything will be shipped to:");
        System.out.println("AD1: " + add1);
        System.out.println("AD2: " + add2);
        System.out.println("City: " + city);
        System.out.println("County: " + country);
        System.out.println("Eir Code: " + eircode);
        System.out.println("Your tracking number is: " + trackNum);
        System.out.println("Order status: " + orderStatus);
    }

    // A method for sending a negative email in case the order is not successful
    public void negativeEmail(double balance, double cartPrice, LocalDate expiryDate, LocalDate currentDate) {
        System.out.println("Failed to process the order TT");
        System.out.println("");
        System.out.println("Regret Email Notification -----");
        System.out.println("To: " + email);
        System.out.println("Dear Customer,");
        System.out.println(
                "Unfortunately, we weren't able to proceed with confirming your order. Here are the details we advice you to review:");
        System.out.println("Card balance: €" + balance + " (Price of the order: €" + cartPrice + ")");
        System.out.println("Card expiry date: " + expiryDate + " (Current day: " + currentDate + ")");
    }
}