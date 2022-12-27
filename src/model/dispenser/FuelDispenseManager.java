package model.dispenser;

public interface FuelDispenseManager {
    public void getStats();

    public void dispenseFuel(double quantity);

    public void suspendDispenser();

}
