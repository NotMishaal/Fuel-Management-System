package model.staff;

import java.sql.*;
import java.util.ArrayList;

public class Accountant extends Staff{
    private ArrayList<Account> listOfAccounts;

    public Accountant(String staffID, String name) {
        super(staffID, name);
    }
    public boolean addAccount(String nameOfTheAccountTable){
        //Making a connection
        String url = "jdbc:mysql://localhost:3306/account";

        // SQL statement for creating a new table
        String createTable = "CREATE TABLE IF NOT EXISTS "+nameOfTheAccountTable+ " (\n"
                + "	Number_Plate varchar(40) PRIMARY KEY,\n"
                + "	Accountant_Name varchar(40) NOT NULL,\n"
                + "	Customer VARCHAR(40) NOT NULL, \n"
                + "	Paid_Amount double NOT NULL, \n"
                + "	Fuel_Dispensed double NOT NULL, \n"
                + "	Pumped_Date date NOT NULL, \n"
                + "	Vehicle_Category_Type varchar(40) NOT NULL, \n"
                + "	Fuel_Type varchar(40) NOT NULL, \n"
                + "	Dispenser_ID int(10) NOT NULL \n"
                + ");";

        try {
            Connection connection = DriverManager.getConnection(url,"root","");
            Statement statement = connection.createStatement();

            //Creating a new table
            statement.execute(createTable);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteDataFromAccount(String tableName,String numberPlate){
        //Making a connection
        String url = "jdbc:mysql://localhost:3306/account";

        //Deleting rows
        String deleteData = "Delete from "+ tableName +" where Number_Plate = ?";

        try {
            Connection connection = DriverManager.getConnection(url,"root","");
            PreparedStatement preparedStatement = connection.prepareStatement(deleteData);

            //Deleting the table
            preparedStatement.setString(1,numberPlate);
            preparedStatement.executeUpdate();
            System.out.println("Account deleted successfully.");
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean removeAccount(String nameOfTheAccountTable){
        //Making a connection
        String url = "jdbc:mysql://localhost:3306/account";

        // SQL statement for removing the table
        String dropTable = "DROP TABLE " + nameOfTheAccountTable;

        try {
            Connection connection = DriverManager.getConnection(url,"root","");
            Statement stmt = connection.createStatement();


            //Removing the table
            stmt.execute(dropTable);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}