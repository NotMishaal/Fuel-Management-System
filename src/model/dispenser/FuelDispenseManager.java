package model.dispenser;

import model.customer.Customer;

public interface FuelDispenseManager {
    public void dispenseFuel(double quantity);
    public void suspendDispenser();

}
