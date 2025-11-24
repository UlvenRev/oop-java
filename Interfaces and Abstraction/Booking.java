// ONE person makes ONE BOOKING, but this booking can inlude MANY people
// E.g. one person booked a trip for 4 people on GoBus
// Another person booken a trip for 3 people on GoBus
// TWO of these BOOKINGS go into GoBus

import java.util.ArrayList;
import java.util.Locale;

public class Booking {

    private Trip trip; // Get the Trip Class instance
    private ArrayList<Integer> bookedSeats = new ArrayList<>(); // CHANGED THE SOURCE CODE - it was initially just int,
                                                                // I made it into a list of integers (seat numbers to
                                                                // book)
    private double totalCost; // Calculated from numPassengers * trip.getTripFare()

    public Booking(Trip trip, ArrayList<Integer> bookedSeats) {
        this.trip = trip;
        this.bookedSeats = bookedSeats;
        totalCost = bookedSeats.size() * trip.getTripFare();
    }

    @Override
    public String toString() {
        String output = "Trip ID: " + Integer.toString(trip.getTripID()) + "\nNumber of passengers: "
                + bookedSeats.size() + "\nSeats for booking: "
                + getBookedSeatsString() + "\nTotal cost of booking: " + String.format(Locale.US, "%.2f", totalCost)
                + "€" + "\n\n";
        return output;
    }

    public String getBookedSeatsString() {
        String output = "";
        for (int i = 0; i < bookedSeats.size(); i++) {
            output += "\n" + Integer.toString(i + 1);
        }
        return output;
    }

    // Getters and setters
    public Trip getTrip() {
        return trip;
    }

    public ArrayList<Integer> getBookedSeats() {
        return bookedSeats;
    }

    public double getTotalCost() {
        return totalCost;
    }

    // !!!!!!!!!!!!!!!!!!!!!!! ADD AN INSTANCE CHECK
    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public void setBookedSeats(ArrayList<Integer> bookedSeats) {
        if (bookedSeats.size() != 0) {
            this.bookedSeats = bookedSeats;
            totalCost = bookedSeats.size() * trip.getTripFare(); // Automatically updating the total cost of the booking
                                                                 // then
        } else {
            System.out.println("Failed to book, choose at least one seat for booking.");
        }
    }
}
