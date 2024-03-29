package model.repository;

import model.dispenser.DieselDispenser;
import model.staff.Manager;

import java.util.ArrayList;

public class DieselRepository extends Repository{
    private double price;
    @Override
    public double getAvailableFuel() {
        return super.getAvailableFuel();
    }

    @Override
    public void setAvailableFuel(double availableFuel) {
        super.setAvailableFuel(availableFuel);
    }

    @Override
    public double getCapacity() {
        return super.getCapacity();
    }

    @Override
    public void setCapacity(double capacity) {
        super.setCapacity(capacity);
    }

    @Override
    public String getFuelType() {
        return super.getFuelType();
    }

    @Override
    public void setFuelType(String fuelType) {
        super.setFuelType(fuelType);
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public double setPrice(double price) {
        super.setPrice(price);
        return price;
    }

    @Override
    public Manager getManager() {
        return super.getManager();
    }

    @Override
    public void setManager(Manager manager) {
        super.setManager(manager);
    }
    private ArrayList<DieselDispenser> listOfDieselDispenser = new ArrayList<>();

    public ArrayList<DieselDispenser> getListOfDieselDispenser() {
        return listOfDieselDispenser;
    }

    public void setListOfDieselDispenser(ArrayList<DieselDispenser> listOfDieselDispenser) {
        this.listOfDieselDispenser = listOfDieselDispenser;
    }

    // Composition with the Diesel dispense manager
    public DieselRepository(double price, int ID) {
        listOfDieselDispenser.add(new DieselDispenser(ID));
        this.price = price;
    }

    // Method to create a dispenser
    public boolean createDispenser(int ID){
        //1. Create a diesel dispenser
        //2. Add the created dispenser to the list
        //3. Return boolean value (T if created dispenser added to list)
        return this.listOfDieselDispenser.add(new DieselDispenser(ID));
    }
}
