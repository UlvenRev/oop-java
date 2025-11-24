import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Trip {

    private int tripID;
    private String startingLocation;
    private String destination;

    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;

    private double fare; // Price of the trip
    private ArrayList<Boolean> availableSeats = new ArrayList<>();

    public Trip(String startingLocation, String destination, String departureDateStr, String departureTimeStr,
            String arrivalDateStr, String arrivalTimeStr, double fare, int seatsAmount) {
        tripID = generateTripID();

        this.startingLocation = startingLocation;
        this.destination = destination;

        departureDate = LocalDate.parse(departureDateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        arrivalDate = LocalDate.parse(arrivalDateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        departureTime = LocalTime.parse(departureTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
        arrivalTime = LocalTime.parse(arrivalTimeStr, DateTimeFormatter.ofPattern("HH:mm"));

        this.fare = fare;
        for (int i = 0; i < seatsAmount; i++) {
            availableSeats.add(true); // When the Trip is first created, all the seats should be available by
                                      // default, so each seat is "true". Then when they get booked, they change to
                                      // "false"
        }
    }

    private int generateTripID() {
        tripID = (int) ((Math.random() * (9999 - 1000 + 1)) + 1000);
        return tripID;
    }

    @Override
    public String toString() {
        String output = "Trip ID: " + Integer.toString(tripID) + "\n" + "Travelling: " + startingLocation + " -> "
                + destination + "\nTravel Time: " + departureTime.toString() + " - " + arrivalTime.toString()
                + "\nTravel Dates: " + departureDate.toString() + " - " + arrivalDate.toString() + "\nPrice: "
                + String.format("%.2f", fare) + "€\n" + getAvailableSeats() + "\n";
        return output;
    }

    public String getAvailableSeats() {
        String output = "Free seats:\n";
        Boolean found = false;
        for (int i = 0; i < availableSeats.size(); i++) {
            if (availableSeats.get(i) == true) {
                found = true;
                output += i + 1 + "\n";
            }
        }
        if (!found)
            return "Everything for this trip is booked, our apologies!";
        return output;
    }

    public boolean getBoolAvailableSeats() {
        Boolean found = false;
        for (int i = 0; i < availableSeats.size(); i++) {
            if (availableSeats.get(i) == true) {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Trip))
            return false;

        Trip other = (Trip) obj;
        return this.tripID == other.tripID;
    }

    // Getters and setters
    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureDate(String departureDateStr) {
        departureDate = LocalDate.parse(departureDateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public void setDepartureTime(String departureTimeStr) {
        departureTime = LocalTime.parse(departureTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
    }

    public void setArrivalDate(String arrivalDateStr) {
        arrivalDate = LocalDate.parse(arrivalDateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public void setArrivalTime(String arrivalTimeStr) {
        arrivalTime = LocalTime.parse(arrivalTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public void setAvailableSeats(int seatsAmount) {
        availableSeats.clear(); // Clearing the old array to repopulate it with the new amount of available
                                // seats
        for (int i = 0; i < seatsAmount; i++) {
            availableSeats.add(true);
        }
    }

    public int getTripID() {
        return tripID;
    }

    public String getStartingLocation() {
        return startingLocation;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public double getTripFare() {
        return fare;
    }

    public ArrayList<Boolean> getAvailableSeatsArray() {
        return availableSeats;
    }
}
