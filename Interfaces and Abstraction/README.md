## Interfaces and Abstraction Assignment

#### Purpose
In this assignment, the main goal was to learn how to use interfaces and abstraction alongside each other.
Interfaces are more like an _outline_ of what a class that implements it should have, therefore we user @Override on all of the implemented methods.
Abstraction, on the other hand, is a _class_ which holds all the common features for classes that inherit from it. 

#### Solution
For some tasks, of course, it's more logical to use only one of those structures, however this assignment asked to implement both. Therefore, I left both and distributed the functionality in the following way:
- Interface: only method signatures that define the contract all vendors must follow – getAllAvailableTrips() and makeBooking().
- Abstract class: shared implementation methods and details that are the same between all vendors, like addTrip(), getBookings() etc.
- Vendors: vendors themselves thus have all the unique information, which for now is only the name of the vendor.

This way I avoided huge amount of code repetition by using both interfaces and inheritance.
