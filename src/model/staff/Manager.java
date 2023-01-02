package model.staff;

import model.dispenser.DieselDispenser;
import model.dispenser.OctaneDispenser;
import model.repository.DieselRepository;
import model.repository.OctaneRepository;
import model.repository.Repository;

import java.util.ArrayList;

public class Manager extends Staff{
    private ArrayList<Repository> listOfRepository;
    private ArrayList<DieselDispenser> listOfDieselDispenser;
    private ArrayList<OctaneDispenser> listOfOctaneDispenser;

    public Manager(String staffID, String name) {
        super(staffID, name);
    }

    public void installDispenser(int ID, DieselRepository dieselRepository){
        // When installing a new dispenser,
        // Function will create a new object of DieselDispenser
        // And add it into the DieselDispenser arrayList

        // Check if that dieselDispenser already exists
        for (DieselDispenser dispenser : dieselRepository.getListOfDieselDispenser()) {
            if (dispenser.getDispenserID() == ID) {
                System.out.println("Diesel dispenser already exists, add a different one");
                return;
            }
        }
        dieselRepository.createDispenser(ID);
        System.out.println("Diesel dispenser added successfully");
    }

    public void installDispenser(int ID, OctaneRepository octaneRepository){
        // When installing a new dispenser,
        // Function will create a new object of octaneDispenser
        // And add it into the OctaneDispenser arrayList

        //Check if that octaneDispenser already exists

        for (OctaneDispenser dispenser : octaneRepository.getListOfOctaneDispenser()) {
            if (dispenser.getDispenserID() == ID) {
                System.out.println("Octane dispenser already exists, add a different one");
                return;
            }
        }
        octaneRepository.createDispenser(ID);
        System.out.println("Octane dispenser added successfully");
    }

    public boolean verifyFuelCapacity(Repository r){
        //This method is to check if there are available fuel to dispense
        //And returns true if there is
        //Else return false
        return r.getAvailableFuel() > 500;
    }

    public void refillRepository(Repository r, double quantity){
        //This function will refill the fuel amount if the fuel capacity is less than 500L
        if (!verifyFuelCapacity(r)){
            if (r.getAvailableFuel()+quantity < r.getCapacity()){
                r.setAvailableFuel(r.getAvailableFuel()+quantity);
                System.out.println("Repository refilled. Available octane: " + r.getAvailableFuel());
            } else {
                System.out.println("Volume entered exceeds repository capacity!");
            }
        } else {
            System.out.println("Fuel capacity is above 500L");
        }
    }
}
