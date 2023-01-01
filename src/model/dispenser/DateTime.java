package model.dispenser;

import java.util.Date;
import java.util.HashMap;

public class DateTime {
    private String fuelType;
    private HashMap<Date, Double> fuelQuantityDispensed;

    public DateTime(String fuelType, HashMap<Date, Double> fuelQuantityDispensed) {
        this.fuelType = fuelType;
        this.fuelQuantityDispensed = fuelQuantityDispensed;
    }

    // Getters and setters
    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public HashMap<Date, Double> getFuelQuantityDispensed() {
        return fuelQuantityDispensed;
    }

    public void setFuelQuantityDispensed(HashMap<Date, Double> fuelQuantityDispensed) {
        this.fuelQuantityDispensed = fuelQuantityDispensed;
    }

    public double calculateFuelDispensed(Date date){
        // This method calculates the total fuel dispensed on a given date
        // TODO get data from database
        return 0;
    }
}
