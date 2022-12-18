package model.dispenser;

import model.repository.DieselRepository;

public class DieselDispenser implements FuelDispenseManager{
    private int dispenserID;
    private double fuelDispensed;
    private DieselRepository dieselRepository;


    // Main constructor
    public DieselDispenser(int dispenserID, double fuelDispensed) {
        this.dispenserID = dispenserID;
        this.fuelDispensed = fuelDispensed;
    }


    // Getters and setters
    public DieselRepository getDieselRepository() {
        return dieselRepository;
    }

    public void setDieselRepository(DieselRepository dieselRepository) {
        this.dieselRepository = dieselRepository;
    }

    public int getDispenserID() {
        return dispenserID;
    }

    public void setDispenserID(int dispenserID) {
        this.dispenserID = dispenserID;
    }

    public double getFuelDispensed() {
        return fuelDispensed;
    }

    public void setFuelDispensed(double fuelDispensed) {
        this.fuelDispensed = fuelDispensed;
    }


    // -- Overriding methods from super interface class --
    @Override
    public void getStats() {
        /*
         * Method to print statistics of the following
         * 1) The total fuel dispensed per vehicle category type per fuel type
         * 2) The vehicle that received the largest amount of fuel for the day and the type of
         * fuel received.
         * 3) Print the total number of vehicles served by each dispenser along with the
         * amounts of fuel and the total income per dispenser as well as the total income of
         * the Gas Station per day per fuel type and the remaining stock at close.
         */
    }

    @Override
    public void dispenseFuel() {
        // Method to dispense diesel to customer
    }

    @Override
    public void suspendDispenser() {
        // Method to suspend dispenser if remaining diesel in repository is < 500L
    }
}
