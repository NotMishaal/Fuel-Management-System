package model.staff;

import model.customer.Customer;
import model.dispenser.FuelDispenseManager;

import java.util.ArrayList;

public class Attendant extends Staff{
    private ArrayList<Customer> listOfCustomers;

    public Attendant(String staffID, String name, ArrayList<Customer> listOfCustomers) {
        super(staffID, name);
        this.listOfCustomers = listOfCustomers;
    }

    public void dispense(FuelDispenseManager d,double quantity,Customer customer){
        //Dispense fuel
        d.dispenseFuel(quantity);
        this.listOfCustomers.add(customer); //Once the vehicle got pumped, customer details will get recorded
    }
}
