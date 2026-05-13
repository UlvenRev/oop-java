import java.util.Scanner; // For scanning user's input from the console 

public class Address {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String eirCode;

    private String allowed = "abcdefghijklmnopqrstuvwxyz1234567890 .,-\"";

    public Address() {
        // Local methods that all set the address (while also checking is the address is
        // valid)
        // They are of type public *just in case I'd need to use them globally*.
        // However, how using them here I could've left them as private. Since they're
        // mutators, I leave them as public
        setAddressLine1();
        setAddressLine2();
        setCity();
        setCountry();
        setEirCode();
    }

    // Accessors
    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getEirCode() {
        return eirCode;
    }

    // A method for checking validity of any address detail entered
    private boolean checkAddressValidity(String address) {
        for (char c : address.toLowerCase().toCharArray()) { // Changing the STRING here to an ARRAY of lowercase
                                                             // characters, so that we're able to loop through it and
                                                             // take a type "char" and not type "String"
            if (allowed.indexOf(c) == -1) // Don't accept the address if there are any forbidden letter in there
            {
                return false;
            }
        }
        return true;
    }

    // Creating a Scanner object to scan the input properly
    private static final Scanner inputScanner = new Scanner(System.in); // Declaring a Scanner obj this way helps us
                                                                        // avoid declating it each time in each method
                                                                        // we want to use it in
    // Because there's also Scanner inputScanner = new Scanner(System.in) way to
    // declare one, but this has to be done in *each* method we're using it in

    private String askInputAndCheck(String addressName) {
        // A do-while loop which will ask user again and again for the valid information
        // until they type one
        do {
            System.out.println("Enter " + addressName + ":");
            String userInput = inputScanner.nextLine(); // Read in what the user types
            boolean result = checkAddressValidity(userInput); // Checks the validity of a typed address using the
                                                              // private function above
            if (result) {
                return userInput;
            }
            System.out.println("The address in invalid.");
        } while (true);
    }

    public void setAddressLine1() {
        addressLine1 = askInputAndCheck("address line 1"); // The parameter here is only so that we can display the
                                                           // right query in the askInputAndCheck() method
    }

    public void setAddressLine2() {
        addressLine2 = askInputAndCheck("address line 2");
    }

    public void setCity() {
        city = askInputAndCheck("city");
    }

    public void setCountry() {
        country = askInputAndCheck("country");
    }

    public void setEirCode() {
        eirCode = askInputAndCheck("eir code");
    }
}