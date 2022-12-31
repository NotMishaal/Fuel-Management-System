package model.customer;

import model.queue.Queue;

public class Customer {
    private int vehicleType;
    /* Vehicle Type:
        1- Cars & Vans
        2- Three Wheelers
        3- Motorbikes
        4- Public Transport
        5- Other
    */
    private int fuelType;
    // Fuel Type: 0 -> Octane, 1 -> Diesel
    private String licensePlate;
    private Ticket ticket;

    private Queue queue;

    public boolean makePayment(double payment){
        // Amount due is calculated from ticket and fuel price.
        double amountDue = 0;
        if (fuelType == 0){
            amountDue = ticket.getFuelDispensed() * 450;
        } else if (fuelType == 1){
            amountDue = ticket.getFuelDispensed() * 430;
        }

        // Validate if payment is sufficient.
        if (payment < amountDue){
            System.out.println("Amount entered invalid. Please try again.");
            return false;
        } else if (payment > amountDue) {
            System.out.println("Thank you for your payment!");
            System.out.println("Balance: $"+(payment-amountDue));
            return true;
        } else {
            System.out.println("Thank you for your payment!");
            return true;
        }
    }

//    Getters, Setters & Constructors:

    public Customer(int vehicleType, int fuelType, String licensePlate) {
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
        this.licensePlate = licensePlate;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(int vehicleType) {
        //TODO: Add validation logic
        this.vehicleType = vehicleType;
    }

    public int getFuelType() {
        return fuelType;
    }

    public void setFuelType(int fuelType) {
        //TODO: Add validation logic
        this.fuelType = fuelType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
