public class TransactionTest {
    public TransactionTest() {

    }

    public static void main(String[] args) {
        TransactionTest test = new TransactionTest();
        // test.transaction1();
        test.transaction2();
    }

    public void transaction1() {
        // 1. Create customer object
        Customer testPerson = new Customer();

        testPerson.getClassEmail();

        // (5.) Add a delivery address for the order - also through the console
        Address userAddress = testPerson.getClassAddress();

        // 2. Create Shopping Cart object for the Customer
        ShoppingCart cart = testPerson.getCart(); // The cart is INSIDE the customer, but I'm saving it to a sep
                                                  // variable so it's more convinient to use

        // Adding the items
        Item laptop = new Item("Laptop", 9999.99, 1);
        Item book = new Item("Book", 45.99, 2);
        Item shampoo = new Item("Shampoo", 7.60, 3);

        // 3. Add 3 items with known cost to cart
        System.out.println(cart.addItem(laptop)); // Using System.out.println because .addItem() returns a STRING that
                                                  // I'm instantly printing into the console to indicate a change
        System.out.println(cart.addItem(book));
        System.out.println(cart.addItem(shampoo));

        System.out.println("Showing your shopping cart of size " + cart.getAllItems().size() + ":");
        cart.showShoppingCart();

        // (6.) Enter a payment type
        Payment payment = new Payment(); // This payment class consist *only* of the information customer has about
                                         // their card. The order confirmation and checks happen in the Order class

        // An order is created
        Order order = new Order(cart.getAllItems(), cart.getTotalPrice(), userAddress, testPerson.getClassEmail(),
                payment);

        // 7. Validate the payment
        if (cart.getAllItems().size() > 0) {
            System.out.println("Processing your order...");
            boolean opRes = order.confirmOrder();

            // 8. Email the customer
            if (opRes) {
                System.out.println("Processed successfully!");
                int trackNum = order.getTrackingNumber();
                String orderStatus = order.getOrderStatus();

                // All the parameters passed in here are only for creating a nicely looking
                // email and inserting their values into the paragraph
                testPerson.getClassEmail().positiveEmail(testPerson.getClassEmail().getStringEmail(),
                        cart.getTotalPrice(),
                        userAddress.getAddressLine1(), userAddress.getAddressLine2(),
                        userAddress.getCity(), userAddress.getCountry(), userAddress.getEirCode(), trackNum,
                        orderStatus);
            } else {
                testPerson.getClassEmail().negativeEmail(payment.getBalance(),
                        cart.getTotalPrice(), payment.getExpiryDate(), payment.getCurrentDate());
            }
        } else {
            System.out.println("In order to process with the order, select at least one item to buy.");
        }
    }

    public void transaction2() {
        Customer testPerson = new Customer();

        testPerson.getClassEmail();

        // Add a delivery address for the order
        Address userAddress = testPerson.getClassAddress();

        // 1. A user adds three items
        ShoppingCart cart = testPerson.getCart();

        Item laptop = new Item("Laptop", 9999.99, 1);
        Item book = new Item("Book", 45.99, 2);
        Item shampoo = new Item("Shampoo", 7.60, 3);

        // // 3. Add 3 items with known cost to cart
        System.out.println(cart.addItem(laptop));
        System.out.println(cart.addItem(book));
        System.out.println(cart.addItem(shampoo));

        // 2. The user displays cart items and total
        System.out.println("Your items:");
        cart.showShoppingCart();
        System.out.println("Total price of the cart: €" + cart.getTotalPrice());

        // 3. The user removes one item
        cart.removeItem();
        System.out.println("Your items:");
        cart.showShoppingCart();
        System.out.println("Total price of the cart: €" + cart.getTotalPrice());

        // 4. The user confirms the cart and places an order
        Payment payment = new Payment();

        Order order = new Order(cart.getAllItems(), cart.getTotalPrice(), userAddress, testPerson.getClassEmail(),
                payment);

        // 6. Add a payment type + 7. Validate the payment
        if (cart.getAllItems().size() > 0) {
            System.out.println("Processing your order...");
            boolean opRes = order.confirmOrder();

            // 8. Email the customer
            if (opRes) {
                System.out.println("Processed successfully!");
                int trackNum = order.getTrackingNumber();
                String orderStatus = order.getOrderStatus();

                testPerson.getClassEmail().positiveEmail(testPerson.getClassEmail().getStringEmail(),
                        cart.getTotalPrice(),
                        userAddress.getAddressLine1(), userAddress.getAddressLine2(),
                        userAddress.getCity(), userAddress.getCountry(), userAddress.getEirCode(), trackNum,
                        orderStatus);
            } else {
                testPerson.getClassEmail().negativeEmail(payment.getBalance(),
                        cart.getTotalPrice(), payment.getExpiryDate(), payment.getCurrentDate());
            }
        } else {
            System.out.println("In order to process with the order, select at least one item to buy.");
        }
    }
}