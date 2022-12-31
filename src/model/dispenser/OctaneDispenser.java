package model.dispenser;
import model.repository.OctaneRepository;

public class OctaneDispenser implements FuelDispenseManager{
    private int dispenserID;
    private double fuelDispensed;
    private OctaneRepository octaneRepository;
    private double availableOctane;
    private boolean isSuspended;


    // Constructor
    public OctaneDispenser(int dispenserID) {
        this.dispenserID = dispenserID;
    }

    // Getters and setters
    public double getAvailableOctane() {
        return availableOctane;
    }

    public void setAvailableOctane(double availableOctane) {
        this.availableOctane = availableOctane;
    }
    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
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

    public OctaneRepository getOctaneRepository() {
        return octaneRepository;
    }

    public void setOctaneRepository(OctaneRepository octaneRepository) {
        this.octaneRepository = octaneRepository;
    }

    @Override
    public void dispenseFuel(double quantity) {
        // Method to dispense octane to customer
        // Method to dispense diesel to customer
        if (this.availableOctane > 500){
            if((this.availableOctane-500) > quantity){
                if (!this.isSuspended){
                    this.availableOctane-=quantity;
                    System.out.println("Octane dispensed");
                }
                else{
                    System.out.println("Dispenser is suspended. Cannot dispense octane.");
                }
            } else {
                System.out.println("Fuel capacity insufficient. Available octane: "+this.availableOctane+ " requested fuel: "+ quantity);
            }
        } else {
            System.out.println("Fuel capacity reached minimum. The repository is now closed.");
            isSuspended = true;
        }
    }

    @Override
    public void suspendDispenser() {
        // Method to suspend dispenser if remaining octane in repository is < 500L
        this.isSuspended=true;
    }
}
