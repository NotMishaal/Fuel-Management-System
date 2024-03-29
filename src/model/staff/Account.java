package model.staff;

import model.customer.Customer;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Account {
    private Accountant accountant;

    public Account(Accountant accountant) {
        this.accountant = accountant;
    }

    public static void addDataToTheAccountTable(String tableName,String NumberPlate,String accountantName,String customerName,double paidAmount,double fuelDispensed,String pumpedDate,String vehicleCategoryType,String fuelType, int dispenserID){
        //This is to add new data to the account table
        String url = "jdbc:mysql://localhost:3306/account";
        try {
            Connection connection = DriverManager.getConnection(url,"root","");
            Statement statement = connection.createStatement();
            String query = "insert into " +tableName + "(Number_Plate,Accountant_Name,Customer,Paid_Amount,Fuel_Dispensed,Pumped_Date,Vehicle_Category_Type,Fuel_Type,Dispenser_ID) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1,NumberPlate);
            preparedStmt.setString(2,accountantName);
            preparedStmt.setString(3,customerName);
            preparedStmt.setDouble(4,paidAmount);
            preparedStmt.setDouble(5,fuelDispensed);

            //Creating a date object
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date date = sdf1.parse(pumpedDate);
            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
            preparedStmt.setDate(6, java.sql.Date.valueOf(pumpedDate));

            preparedStmt.setString(7,vehicleCategoryType);
            preparedStmt.setString(8,fuelType);
            preparedStmt.setInt(9,dispenserID);
            preparedStmt.execute();
            System.out.println("Record added Successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void readDataFromTheAccountTable(String tableName){
        //This is to retrieve the data from the account table which is in the Account DB

        //Making a connection
        String url = "jdbc:mysql://localhost:3306/account";

        // SQL statement for display the table
        String displayTable = "select * from " + tableName;
        try {
            Connection connection = DriverManager.getConnection(url,"root","");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(displayTable);
            while (rs.next()){
                System.out.println("Number Plate: "+rs.getString("Number_Plate") +
                        ", accountant name: " + rs.getString("Accountant_Name") +
                        ", customer name: " + rs.getString("Customer") +
                        ", paid amount: " + rs.getDouble("Paid_Amount") +
                        ", dispensed fuel amount: " + rs.getDouble("Fuel_Dispensed") +
                        ", pumped date: " + rs.getString("Pumped_Date") +
                        ", vehicle category type: " + rs.getString("Vehicle_Category_Type") +
                        ", fuel type: "+ rs.getString("Fuel_Type") +
                        ", dispenser id: " + rs.getInt("Dispenser_ID"));
                System.out.println();
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public static void displayStats(String tableName){
        //This function is used to print some statistics
        System.out.println();
        System.out.println("Displaying the vehicle that received the largest amount of fuel:");
        vehicleThatReceivedLargestFuelAmount(tableName);
        System.out.println();
        System.out.println("Displaying the total fuel dispensed per vehicle category type: ");
        fuelDispensedPerVehicleCategory(tableName);
        System.out.println();
        System.out.println("Displaying the total fuel dispensed per fuel type: ");
        fuelDispensedPerFuelCategory(tableName);
        System.out.println();
        System.out.println("Displaying total number of vehicles served by each dispenser along with the amount of fuel: ");
        noOfVehiclesServed(tableName);
        System.out.println();
        calculateIncome(tableName);

    }

    private static void vehicleThatReceivedLargestFuelAmount(String tableName){
        //This is to get the vehicle that received the largest amount of fuel

        //First, get the values of the column 'fuelDispensed'
        //Then compare each value and get the largest value
        //Return that value

        //Establishing the connection
        String url = "jdbc:mysql://localhost:3306/account";
        try {
            Connection connection = DriverManager.getConnection(url,"root","");
            double maxFuel = 0;
            String customer = null;

            //Retrieving the data
            String query = "select Fuel_Dispensed,Customer from " + tableName;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                if (rs.getDouble("Fuel_Dispensed")>maxFuel){
                    maxFuel = rs.getDouble("Fuel_Dispensed");
                    customer = rs.getString("Customer");
                }
            }
            System.out.println("Maximum fuel amount dispensed: " + maxFuel +", customer who received that fuel: " + customer);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static void fuelDispensedPerVehicleCategory(String tableName){
        //This is to display the total fuel dispensed per vehicle category type

        //Establishing the connection
        String url = "jdbc:mysql://localhost:3306/account";

        try {
            Connection connection = DriverManager.getConnection(url,"root","");

            //Retrieving the data
            String query = "select Vehicle_Category_Type,sum(Fuel_Dispensed) from "+tableName + " group by Vehicle_Category_Type";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.println("Vehicle category type: "+rs.getString("Vehicle_Category_Type") + ", total fuel dispensed: " + rs.getString(2));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static void fuelDispensedPerFuelCategory(String tableName){
        //This is to display the total fuel dispensed per fuel type

        //Establishing the connection
        String url = "jdbc:mysql://localhost:3306/account";

        try {
            Connection connection = DriverManager.getConnection(url,"root","");

            //Retrieving the data
            String query = "select Fuel_Type,sum(Fuel_Dispensed) from "+tableName + " group by Fuel_Type";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.println("Fuel type: "+rs.getString("Fuel_Type") + ", total fuel dispensed: " + rs.getString(2));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static void noOfVehiclesServed(String tableName){
        //This is to display total number of vehicles served by each dispenser
        //Along with the amount of fuel

        //Establishing the connection
        String url = "jdbc:mysql://localhost:3306/account";
        try {
            Connection connection = DriverManager.getConnection(url,"root","");
            //Retrieving the data
            String query = "select Dispenser_ID,COUNT(Customer),SUM(Fuel_Dispensed) from "+tableName + " group by Dispenser_ID";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.println("Dispenser ID: "+rs.getString("Dispenser_ID") + ", total number of vehicles served: " + rs.getString(2) + ", total fuel dispensed: " + rs.getString(3));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static void calculateIncome(String tableName){
        //This function is to calculate the total income per dispenser
        //As well as total income of the gas station per fuel type

        //Establishing the connection
        String url = "jdbc:mysql://localhost:3306/account";

        try {
            Connection connection = DriverManager.getConnection(url,"root","");
            //Retrieving the data to calculate the total income per dispenser
            String query1 = "select Dispenser_ID,SUM(Paid_Amount) from "+tableName + " group by Dispenser_ID";

            //Retrieving the data to calculate the total income of the gas station per fuel type
            String query2 = "select Fuel_Type,SUM(Paid_Amount) from "+tableName + " group by Fuel_Type";

            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            ResultSet rs1 = statement1.executeQuery(query1);
            ResultSet rs2 = statement2.executeQuery(query2);

            System.out.println("Displaying the total income per dispenser: ");
            while (rs1.next()){
                System.out.println("Dispenser ID: "+rs1.getString("Dispenser_ID") + ", total income of the dispense: " + rs1.getString(2));
            }
            rs1.close();
            System.out.println();
            System.out.println("Displaying the total income of the gas station per fuel type: ");
            while (rs2.next()){
                System.out.println("Fuel type: "+rs2.getString("Fuel_Type") + ", total income gained from that fuel type: " + rs2.getString(2));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //Getters and Setters
    public Accountant getAccountant() {
        return accountant;
    }

    public void setAccountant(Accountant accountant) {
        this.accountant = accountant;
    }
}
