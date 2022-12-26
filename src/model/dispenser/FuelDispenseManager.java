package model.dispenser;

import model.customer.Customer;

public interface FuelDispenseManager {
    public void dispenseFuel(Customer customer, double quantity);
}
