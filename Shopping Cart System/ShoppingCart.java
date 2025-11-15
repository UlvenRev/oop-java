import java.util.LinkedList; // For creating LinkedLists
import java.util.Scanner; // For scanning user's input from the console 

public class ShoppingCart {
    private LinkedList<Item> itemsInCart; // Using a LinkedList type of list to store the items (explained why in the
                                          // documentation)
    private double totalPrice;
    private boolean cartLocked;

    public ShoppingCart() {
        itemsInCart = new LinkedList<>(); // Creating a new, empty list whenever we create a new cart
        totalPrice = 0;
        cartLocked = false; // The cart is by default unlocked when first created (since it's empty and we
                            // need to add items)
    }

    // A method to add new item to the cart
    public String addItem(Item item) // Returns a String that I will print out to the console to indicate something
                                     // was changed
    {
        if (!cartLocked) // Only if cart is unlocked
        {
            if (item.getName() != null && item.getPrice() >= 0 && item.getID() > 0) // and all the parameters entered
                                                                                    // aer valid (even though they are
                                                                                    // hardcoded)
            {
                itemsInCart.add(item);
                totalPrice += item.getPrice(); // Icreasing the total price of the cart
                return item.getName() + " was added to cart.";
            } else {
                return "Item was not added, please enter valid parameters.";
            } // Strings that get printed to the console with System.out.println right after
              // we call the method
        } else {
            return "The cart is locked, you can't add items anymore.";
        }
    }

    // A method to get the list of all items in the cart to use in other classes'
    // logic
    public LinkedList<Item> getAllItems() {
        return itemsInCart;
    }

    // A method for closing a cart - elements can't be added once it's closed
    public void close() {
        cartLocked = true;
    }

    private static final Scanner inputScanner = new Scanner(System.in); // Scanner object initialization

    // A Method for removing items - also returns a STRING for printing out the
    // result into the console right away
    public void removeItem() {
        if (!cartLocked) {
            boolean opFinished = false; // To be able to exit the do-while loop
            do {
                System.out.println("Enter the ID of the item you want to remove:");
                long userInput = Long.parseLong(inputScanner.nextLine());
                for (Item item : itemsInCart) {
                    if (item.getID() == userInput) // Using the ID of the item to be sure we remove only one item that's
                                                   // needed (the IDs of each item are displayed to the user when they
                                                   // print the shopping cart)
                    {
                        totalPrice -= item.getPrice();
                        itemsInCart.remove(item);
                        System.out.println(item.getName() + " was removed from the cart.");
                        opFinished = true; // To exit the do while loop
                        break;
                    }
                }
                if (!opFinished)
                    System.out.println("Item not found. Try again.");
            } while (!opFinished);
        } else {
            System.out.println("The cart is locked, you can't remove items anymore.");
        }
    }

    // A method for printing all the items of the shopping cart
    public void showShoppingCart() {
        if (itemsInCart.size() > 0) { // Print only if there *is* something to print
            for (Item item : itemsInCart) {
                System.out.println("(" + item.getID() + ") " + item.getName() + " - €" + item.getPrice());
            }
        } else {
            System.out.println("Your cart is empty. Keep browsing!");
        }
    }

    // A method for getting the total price variable
    public double getTotalPrice() {
        return totalPrice;
    }

    // A method for clearing the shopping cart
    public String clearShoppingCart() {
        itemsInCart.clear(); // A method that simply clears the LinkedList
        return ("Cleared your shopping cart!");
    }
}