package model.repository;
import model.staff.Manager;

public abstract class Repository {
    private double availableFuel;
    private double capacity;
    private String FuelType;
    private double price;
    private Manager manager;

    public double getAvailableFuel() {
        return availableFuel;
    }

    public void setAvailableFuel(double availableFuel) {
        this.availableFuel = availableFuel;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getFuelType() {
        return FuelType;
    }

    public void setFuelType(String fuelType) {
        FuelType = fuelType;
    }

    public double getPrice() {
        return price;
    }

    public double setPrice(double price) {
        this.price = price;
        return price;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

}
