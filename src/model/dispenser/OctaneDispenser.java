package model.dispenser;
import controller.Main;
import model.repository.OctaneRepository;

public class OctaneDispenser implements FuelDispenseManager{
    private int dispenserID;
    private OctaneRepository octaneRepository;
    private boolean isSuspended;


    // Constructor
    public OctaneDispenser(int dispenserID) {
        this.dispenserID = dispenserID;
    }

    // Getters and setters
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

    public OctaneRepository getOctaneRepository() {
        return octaneRepository;
    }

    public void setOctaneRepository(OctaneRepository octaneRepository) {
        this.octaneRepository = octaneRepository;
    }

    @Override
    public void dispenseFuel(double quantity) {
        // Method to dispense octane to customer

        if (Main.octaneRepository.getAvailableFuel() > 500){
            if((Main.octaneRepository.getAvailableFuel()-500) > quantity){
                if (!this.isSuspended){
                    Main.octaneRepository.setAvailableFuel(Main.octaneRepository.getAvailableFuel()-quantity);
                    System.out.println("Octane dispensed");
                }
                else{
                    System.out.println("Dispenser is suspended. Cannot dispense octane.");
                }
            } else {
                System.out.println("Fuel capacity insufficient. Available octane: "+Main.octaneRepository.getAvailableFuel()+ " requested fuel: "+ quantity);
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
