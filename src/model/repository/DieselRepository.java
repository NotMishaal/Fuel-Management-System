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

    public DieselRepository(ArrayList<DieselDispenser> listOfDieselDispenser,double price) {
        this.listOfDieselDispenser = listOfDieselDispenser;
        this.price = price;
    }
}
