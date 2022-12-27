package model.dispenser;

import model.repository.DieselRepository;

public class DieselDispenser implements FuelDispenseManager{
    private int dispenserID;
    private double fuelDispensed;
    private DieselRepository dieselRepository;
    private double availableDiesel = 10000;

    private boolean isSuspended = false;

    // Main constructor
    public DieselDispenser(int dispenserID, double fuelDispensed) {
        this.dispenserID = dispenserID;
        this.fuelDispensed = fuelDispensed;
    }


    // Getters and setters
    public double getAvailableDiesel() {
        return availableDiesel;
    }

    public void setAvailableDiesel(double availableDiesel) {
        this.availableDiesel = availableDiesel;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

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
    public void dispenseFuel(double quantity) {
        // Method to dispense diesel to customer
        if (this.availableDiesel > 500){
            if((this.availableDiesel-500) > quantity){
                if (!this.isSuspended){
                    this.availableDiesel-=quantity;
                    System.out.println("Fuel dispensed");
                }
                else{
                    System.out.println("Dispenser is suspended. Cannot dispense diesel.");
                }
            } else {
                System.out.println("Fuel capacity insufficient. Available diesel: "+this.availableDiesel+ " requested fuel: "+ quantity);
            }
        } else {
            System.out.println("Fuel capacity reached minimum. The repository is now closed.");
            isSuspended = true;
        }
    }

    @Override
    public void suspendDispenser() {
        // Method to suspend dispenser if remaining diesel in repository is < 500L
        this.isSuspended=true;
    }
}
