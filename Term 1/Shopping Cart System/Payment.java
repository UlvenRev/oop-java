
// Both of these imports are needed to get and format the LocalDate variable
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Payment {
    private double orderPrice;
    private double balance;
    private String cardType;
    private double cardNumber;
    private short cvv;

    // Two variables of type LocalDate to get the current date and the card's expiry
    // date and be able to compare them
    private LocalDate expiryDate;
    private LocalDate currentDate;

    public Payment() {
        currentDate = LocalDate.now(); // Just getting the current date

        // The user now sets all the details for the payment through the console
        setBalance();
        setCardType();
        setCardNumber();
        setCVV();
        setExpiryDate();
    }

    private static final Scanner inputScanner = new Scanner(System.in);

    public void setBalance() {
        System.out.println("Enter your card balance:");
        String userInput = inputScanner.nextLine();
        balance = Double.parseDouble(userInput);
    }

    public void setCardType() {
        System.out.println("Enter your card type:");
        String userInput = inputScanner.nextLine();
        cardType = userInput;
    }

    public void setCardNumber() {
        System.out.println("Enter your card number:");
        String userInput = inputScanner.nextLine();
        cardNumber = Double.parseDouble(userInput);
    }

    public void setExpiryDate() {
        do {
            try {
                System.out.println("Enter your expiry date (yyyy-MM-dd):");
                String userInput = inputScanner.nextLine();
                expiryDate = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Parsing the
                                                                                                    // String date
                // into LocalDate type so
                // that we can compare them
                break;
            } catch (Exception e) {
                System.out.println("The format you used seems to be wrong, try again.");
            }
        } while (true);
    }

    public void setCVV() {
        System.out.println("Enter your card's CVV:");
        String userInput = inputScanner.nextLine();
        cvv = Short.parseShort(userInput);
    }

    // Accessors
    public String getCardType() {
        return cardType;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setBalance(double expences) {
        balance -= expences;
    }

    // A method that will check if the payment was successful
    public boolean checkPayment(double orderPrice) {
        return balance >= orderPrice && (expiryDate.isAfter(currentDate) || expiryDate.isEqual(currentDate));
    }
}