package model.dispenser;

import controller.Main;
import model.repository.DieselRepository;

public class DieselDispenser implements FuelDispenseManager{
    private int dispenserID;
    private DieselRepository dieselRepository;
    private boolean isSuspended = false;

    // Main constructor
    public DieselDispenser(int dispenserID) {
        this.dispenserID = dispenserID;
    }


    // Getters and setters

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


    // -- Overriding methods from super interface class --

    @Override
    public void dispenseFuel(double quantity) {
        // Method to dispense diesel to customer
        if (Main.dieselRepository.getAvailableFuel() > 500){
            if((Main.dieselRepository.getAvailableFuel()-500) > quantity){
                if (!this.isSuspended){
                    Main.dieselRepository.setAvailableFuel(Main.dieselRepository.getAvailableFuel()-quantity);
                    System.out.println("Diesel dispensed");
                }
                else{
                    System.out.println("Dispenser is suspended. Cannot dispense diesel.");
                }
            } else {
                System.out.println("Fuel capacity insufficient. Available diesel: "+Main.dieselRepository.getAvailableFuel()+ " requested fuel: "+ quantity);
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
