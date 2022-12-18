package model.dispenser;

import java.util.Date;
import java.util.HashMap;

public class DateTime {
    private String fuelType;
    private HashMap<Date, Double> dieselDispensed;
    private HashMap<Date, Double> octaneDispensed;

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public HashMap<Date, Double> getDieselDispensed() {
        return dieselDispensed;
    }

    public void setDieselDispensed(HashMap<Date, Double> dieselDispensed) {
        this.dieselDispensed = dieselDispensed;
    }

    public HashMap<Date, Double> getOctaneDispensed() {
        return octaneDispensed;
    }

    public void setOctaneDispensed(HashMap<Date, Double> octaneDispensed) {
        this.octaneDispensed = octaneDispensed;
    }

    public double calculateFuelDispensed(Date date){
        // This method calculates the total fuel dispensed on a given date
        return 0;
    }
}
