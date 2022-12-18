package model.customer;

public class Ticket {
    private String ticketID;
    private double fuelDispensed;
    private Customer customer;

// Getters, Setters and Constructors:

    public Ticket(String ticketID) {
        this.ticketID = ticketID;
        this.fuelDispensed = 0;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public double getFuelDispensed() {
        return fuelDispensed;
    }

    public void setFuelDispensed(double fuelDispensed) {
        this.fuelDispensed = fuelDispensed;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
