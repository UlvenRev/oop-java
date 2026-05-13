import java.util.ArrayList;

/*
This is the INTERFACE the ABSTRACT CLASS BusVendor implements.
It's not the best solution, since technically a separate interface isn't required here and the methods could be implemented straight in the BusVendor class.
However, the requirement of the assignment is to have both - to demostrate use of interfaces and inheritance through abstract classes.

So this interface defines the two methods that are implemented by the abstract class.
*/

public interface BusVendorInterface {

    public ArrayList<Trip> getAllAvailableTrips();

    public boolean makeBooking(Booking booking);

}
