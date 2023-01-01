package model.dispenser;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

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

    public double calculateFuelDispensed(String date){
        // This method calculates the total fuel dispensed on a given date
        try {
            Statement stat = makeConnection().createStatement();
            ResultSet rs = stat.executeQuery("select * from account");
            double totalFuelDispensed = 0;
            while (rs.next())
                if (Objects.equals(rs.getString(6), "2020-11-19")){
                    totalFuelDispensed += rs.getDouble(5);
                }
            System.out.println("Total fuel dispensed on "+ date + " is " + totalFuelDispensed);
            makeConnection().close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        // Make connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/account?autoReconnect=true&useSSL=false", "root", ""
        );
        return con;
    }

    public DateTime(){}
}
