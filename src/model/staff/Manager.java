package model.staff;

import model.dispenser.DieselDispenser;
import model.dispenser.OctaneDispenser;
import model.repository.Repository;

import java.util.ArrayList;

public class Manager extends Staff{
    private ArrayList<Repository> listOfRepository;
    private ArrayList<DieselDispenser> listOfDieselDispenser;
    private ArrayList<OctaneDispenser> listOfOctaneDispenser;

    public Manager(String staffID, String name, ArrayList<Repository> listOfRepository, ArrayList<DieselDispenser> listOfDieselDispenser, ArrayList<OctaneDispenser> listOfOctaneDispenser) {
        super(staffID, name);
        this.listOfRepository = listOfRepository;
        this.listOfDieselDispenser = listOfDieselDispenser;
        this.listOfOctaneDispenser = listOfOctaneDispenser;
    }

    public boolean installDispenser(DieselDispenser dieselDispenser){
        //When installing a new dispenser,
        // Function will create a new object of DieselDispenser
        // And add it into the DieselDispenser arrayList

        //Check if that dieselDispenser already exists
        for (int i=0;i<listOfDieselDispenser.size();i++){
            if (listOfDieselDispenser.get(i).equals(dieselDispenser)){
                System.out.println("Diesel dispenser already exists, add a different one");
                return false;
            }
        }
        listOfDieselDispenser.add(dieselDispenser);
        System.out.println("Diesel dispenser added successfully");
        return true;
    }

    public boolean installDispenser(OctaneDispenser octaneDispenser){
        //When installing a new dispenser,
        // Function will create a new object of octaneDispenser
        // And add it into the OctaneDispenser arrayList

        //Check if that octaneDispenser already exists
        for (int i=0;i<listOfOctaneDispenser.size();i++){
            if (listOfOctaneDispenser.get(i).equals(octaneDispenser)){
                System.out.println("Octane dispenser already exists, add a different one");
                return false;
            }
        }
        listOfOctaneDispenser.add(octaneDispenser);
        System.out.println("Octane dispenser added successfully");
        return true;
    }

    public boolean verifyFuelCapacity(Repository r){
        //This method is to check if there are available fuel to dispense
        //And returns true if there is
        //Else return false
        if (r.getAvailableFuel()>500){
            return true;
        }else {
            return false;
        }
    }

    public boolean refillRepository(Repository r, double quantity){
        //This function will refill the fuel amount if the fuel capacity is less than 500L
        if (!verifyFuelCapacity(r)){
            r.setCapacity(quantity);
            return true;
        } else {
            return false;
        }
    }
}
