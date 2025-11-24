import java.util.ArrayList;
import java.util.Arrays;

public class TravelIreland {
    public static void main(String args[]) {

        // Creating trips
        Trip trip1 = new Trip("Galway", "Dublin", "12-11-2025", "12:30", "12-11-2025", "14:45", 25, 3);
        Trip trip2 = new Trip("Zurich", "Spiez", "25-12-2025", "11:00", "25-12-2025", "12:45", 30, 5);
        Trip trip3 = new Trip("Constanta", "Bucharest", "02-03-2026", "18:03", "02-03-2026", "20:45", 32, 12);
        // Trip 4 seems similar to trip 1, but trip 4 has its *own* ID and is gonna be
        // in different vendor's trips set
        Trip trip4 = new Trip("Galway", "Dublin", "12-11-2025", "12:30", "12-11-2025", "14:45", 32, 7);
        Trip trip5 = new Trip("Kyiv", "Lviv", "23-01-2026", "08:10", "23-01-2026", "17:45", 4, 1);
        Trip trip6 = new Trip("Oslo", "Larvik", "03-01-2026", "06:00", "03-01-2026", "15:30", 11, 3);

        // Giving each vendor their trips
        BusVendor goBus = new GoBus();
        BusVendor busEireann = new BusEireann();
        BusVendor cityLink = new CityLink();

        BusVendor[] vendors = { goBus, busEireann, cityLink };

        goBus.addTrip(trip1);
        goBus.addTrip(trip2);
        busEireann.addTrip(trip3);
        cityLink.addTrip(trip4);
        cityLink.addTrip(trip5);
        cityLink.addTrip(trip6);

        // Displaying trips from each vendor
        System.out.println(goBus.printAvailableTrips());
        System.out.println(busEireann.printAvailableTrips());
        System.out.println(cityLink.printAvailableTrips());

        // Make bookings and update available seats
        Booking booking1 = new Booking(trip1, new ArrayList<>(Arrays.asList(1, 2))); // Passing in which trip I want to
                                                                                     // book and seat number(s)

        boolean result = goBus.makeBooking(booking1);
        if (result)
            System.out.println("Booking for GoBus successful! Have a nice trip!");
        else
            System.out.println("Invalid booking, try again.");
        // I'll check the actual bookings later, since there's a separate method for it
        // to now crowd the test class

        Booking booking2 = new Booking(trip2, new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5))); // ALL seats SOLD OUT -
                                                                                              // next time when you look
                                                                                              // through the available
                                                                                              // trips for GoBus, only
                                                                                              // trip 1 will appear
                                                                                              // since it still has
                                                                                              // seats
        result = goBus.makeBooking(booking2);
        if (result)
            System.out.println("\nBooking for GoBus successful! Have a nice trip!\n");
        else
            System.out.println("Invalid booking, try again.");

        System.out.println(goBus.getBookings());
        System.out.println(goBus.printAvailableTrips()); // Showing that now we don't have trip 1 and trip 2 displaying
                                                         // for GoBus, but only trip 1, since trip 2 is sold out

        // Attempt invalid bookings
        Booking booking3 = new Booking(trip3, new ArrayList<>(Arrays.asList())); // Havent passed in the number for
                                                                                 // seats - INVALID booking
        result = busEireann.makeBooking(booking3);
        if (result)
            System.out.println("\nBooking for GoBus successful! Have a nice trip!");
        else
            System.out.println("Invalid booking, try again.");

        // Therefore, since the booking was invalid, it's NOT in the list of current
        // bookings for Bus Eireann
        System.out.println(busEireann.getBookings());

    }
}
