
import java.util.LinkedList; // Library for creating Linked Lists
import java.util.Random; // Library for generating random numbers

public class Order {
    private int orderTrackingNumber;
    private String orderStatus;

    // Where we will store all the passed in class variables
    private LinkedList<Item> itemsForOrder;
    private Payment payment;
    private Address address;
    private Email email;

    private double priceToPay;
    private boolean orderChecked; // This is to make sure we've called the Order constructor, i.e. reviewed the
                                  // shopping cart before trying to process the order

    public Order(LinkedList<Item> itemsForOrder, double priceToPay, Address address, Email email, Payment payment) {
        this.itemsForOrder = new LinkedList<>(itemsForOrder); // Making a copy of the cart that basically LOCKS the
                                                              // order. You can modify the cart, but not the order after
                                                              // you create an order instance
        this.priceToPay = priceToPay;
        this.address = address;
        this.email = email;
        this.payment = payment;

        orderChecked = itemsForOrder.size() > 0; // Thia variable is used to make sure the customer tried to open the
                                                 // order, and in case they *have* items to order, the order is placed,
                                                 // so oredrChecked = true
    }

    // A method that will confirm the order and if successful, creates the tracking
    // number and status, and the return value is used to decide what email we're
    // sending
    public boolean confirmOrder() {
        boolean paymentChecked = payment.checkPayment(priceToPay); // Returns true or false from the Payment class

        if (paymentChecked && orderChecked && email.getStringEmail() != null && isAddressComplete()) {
            orderTrackingNumber = new Random().nextInt(Integer.MAX_VALUE);
            orderStatus = "Order Confirmed";
            return true;
        }
        return false;
    }

    // A method used to check if the address that was entered was entered fully -
    // and if something's missing, we can't process the order
    private boolean isAddressComplete() {
        return address.getAddressLine1() != null &&
                address.getCity() != null &&
                address.getCountry() != null &&
                address.getEirCode() != null;
    }

    public int getTrackingNumber() {
        return orderTrackingNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}