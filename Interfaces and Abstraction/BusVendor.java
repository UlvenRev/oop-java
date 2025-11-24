import java.util.ArrayList;

/*
This is the ABSTRACT CLASS BusVendor which IMPLEMENTS the INTERFACE BusVendorInterface with two methods: getAllAvailableTrips() and makeBooking()

It also defines the common variables and methods that GoBus, CityLink and BusEireann have in COMMON - which is why they are placed in the abstract class.
Therefore, since the assignment doesn't require any other unique implementations for companies' classes, I have everything in this abstract class. 
The bus vendors classes only define their unique name, which gets placed into vendorName variable defined here. 
*/

public abstract class BusVendor implements BusVendorInterface {
    protected ArrayList<Trip> allTrips = new ArrayList<>(); // Will hold all the trips for GoBus which you can book
    protected ArrayList<Booking> bookings = new ArrayList<>();
    protected String vendorName;

    @Override
    public ArrayList<Trip> getAllAvailableTrips() {
        ArrayList<Trip> availableTrips = new ArrayList<>();
        for (Trip trip : allTrips) {
            if (trip.getBoolAvailableSeats()) {
                availableTrips.add(trip);
            }
        }
        return availableTrips;
    }

    public String printAvailableTrips() {
        String output = "----------------------------------------\nAvailable trips for " + vendorName + " are:\n";
        for (Trip trip : getAllAvailableTrips()) {
            output += trip.toString();
        }
        return output;
    }

    @Override
    public boolean makeBooking(Booking booking) {
        if (booking instanceof Booking) {
            boolean successful = false;
            for (Trip trip : allTrips) {
                if (booking.getTrip().equals(trip)) { // This .equals is a CUSTOM method from Trip class
                    ArrayList<Boolean> currentTripSeats = trip.getAvailableSeatsArray();
                    for (int j = 0; j < booking.getBookedSeats().size(); j++) {
                        if (currentTripSeats.get(booking.getBookedSeats().get(j) - 1)) {
                            currentTripSeats.set(booking.getBookedSeats().get(j) - 1, false); // This seat is now booked
                            successful = true;
                        }
                    }
                }
            }
            if (successful) {
                bookings.add(booking);
                return true;
            }
        }
        return false; // Failed to book the trip
    }

    public void addTrip(Trip trip) {
        if (trip instanceof Trip) {
            allTrips.add(trip);
        } else {
            System.out.println("Failed to add the trip, check if you're passing in the correct instance.");
        }
    }

    public String getBookings() {
        if (bookings.size() != 0) {
            String output = "Bookings for " + vendorName + " are the following ------>\n";
            for (int i = 0; i < bookings.size(); i++) {
                output += "Booking " + Integer.toString(i + 1) + ":\n";
                output += bookings.get(i).toString(); // Custom toString() method inside Booking class to return all the
                                                      // info as one string
            }
            return output;
        } else {
            return "No bookings for " + vendorName + " yet.";
        }
    }
}
