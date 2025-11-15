## Shopping Cart System Assignment

#### Purpose

The following code demonstrates the use of composition in everyday life by implementing the Shopping Cart System. 
Note: Some of the values are being hard coded, since making everything user-inputted wasn't in the requirements for this task. However, I left the personal details to be a user input to make it feel more like an real life system.

#### Classes Implemented
-	**TransactionTest.** This is where a Customer instance is being created and I call for all the methods, that are prompting the user to enter their details. This is also where I add all the hard-coded items into the shopping cart, place an order and call the appropriate function to show the email (either a successful one or negative one).

-	**ShoppingCart.** This is where I create an empty Linked List that will hold the customer’s future items. This class has methods like addItem(), removeItem(), close() to lock the shopping cart, getAllItems(), showShoppingCart(), getTotalPrice() and clearShoppingCart() to remove all items.

-	**Order.** This class is used for placing an order. I pass the list of ordered items from the shopping cart, the total price and all the details of the user needed for the order (email, address, payment details etc.) The main method in this class is confirmOrder(), as it returns a Boolean value which helps me to decide which email to send to the user (positive or negative) and if I need to create a tracking number for the order and the status. 

-	**Address.** Lets the user set all their address details. Each mutator checks in place if the address that’s being entered is valid, and if it’s not, the do-while loop will not allow the user to proceed until they enter a correct address.

-	**Payment.** This class is similar to Address class. It allows the user to set all their payment details, like card balance, card number etc. All the values that need to be numbers (card number e.g.) are being parsed from the String type into the correct numeric one. The most important method here is checkPayment() as it checks that the user has sufficient budget to pay for the good and that their card hasn’t expired yet. The method setBalance() is also called when the order is placed to decrease user’s card balance by the sum they’ve spent.

-	**Email.** This class lets the user enter email (also checking if it’s a valid one), and has methods that are responsible for the positive and negative emails to send after the order.

-	**Customer.** The customer class was given, but the changes I made are the following: the user can set first/last name themselves by typing it into the console, each user gets a unique ID and the Customer instance holds inside itself the email, address and a shopping cart.

-	**Item.** This class was given and I haven’t changed anything. The instance of this class creates an item with a name, price and ID.

