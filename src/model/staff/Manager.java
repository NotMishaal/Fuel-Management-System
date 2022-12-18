package model.staff;

import model.dispenser.OctaneDispenser;
import model.repository.Repository;

import java.util.ArrayList;

public class Manager extends Staff{
    private ArrayList<Repository> listOfRepository;

    public Manager(String staffID, String name, ArrayList<Repository> listOfRepository) {
        super(staffID, name);
        this.listOfRepository = listOfRepository;
    }

    public boolean installDispenser(){
        //Installs new dispensers
        return true;
    }

    public boolean verifyFuelCapacity(Repository r){
        //Verify the fuel capacity
        return true;
    }

    public boolean refillRepository(Repository r, double quantity){
        int indexOfTheRepository = listOfRepository.indexOf(r);
        if (indexOfTheRepository!=-1){
            //Refill the repository
            //r.setCapacity(quantity);
            return true;
        }else {
            return false;
        }
    }
}
