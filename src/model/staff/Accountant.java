package model.staff;

import model.customer.Customer;

import java.sql.*;
import java.util.ArrayList;

public class Accountant extends Staff{
    private ArrayList<Account> listOfAccounts;

    public Accountant(String staffID, String name, ArrayList<Account> listOfAccounts) {
        super(staffID, name);
        this.listOfAccounts = listOfAccounts;
    }
    public boolean addAccount(String nameOfTheAccountTable){
        //Making a connection
        String url = "jdbc:mysql://localhost:3306/account";

        // SQL statement for creating a new table
        String createTable = "CREATE TABLE IF NOT EXISTS "+nameOfTheAccountTable+ " (\n"
                + "	Account_ID integer NOT NULL,\n"
                + "	Accountant_Name varchar NOT NULL,\n"
                + "	Customer VARCHAR NOT NULL \n"
                + "	Paid_Amount double NOT NULL \n"
                + "	Fuel_Dispensed double NOT NULL \n"
                + "	Pumped_Date date NOT NULL \n"
                + "	Vehicle_Category_Type varchar NOT NULL \n"
                + "	Fuel_Type varchar NOT NULL \n"
                + "	Dispenser_ID int NOT NULL \n"
                + ");";

        try {
            Connection connection = DriverManager.getConnection(url,"root","");
            Statement statement = connection.createStatement();

            //Creating a new table
            statement.executeQuery(createTable);
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
            PreparedStatement preparedStmt = connection.prepareStatement(dropTable);
            preparedStmt.setString(1,nameOfTheAccountTable);

            //Removing the table
            preparedStmt.execute();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

//    public boolean removeAccount(Account account){
//        int indexOfTheAccount = listOfAccounts.indexOf(account); // This is to check if the object parsed exist in the list
//        if (indexOfTheAccount == -1){
//            //If the object does not exist in the list
//            System.out.println("Account not found");
//            return false;
//        }else {
//            listOfAccounts.remove(indexOfTheAccount);
//            System.out.println("Account removed successfully");
//            return true;
//        }
//    }

//    public boolean addAccount(Account account){
//        int indexOfTheAccount = listOfAccounts.indexOf(account);
//        if (indexOfTheAccount == -1){
//            //If the object does not exist in the list
//            this.listOfAccounts.add(account);
//            System.out.println("Account added successfully");
//            return true;
//        }else {
//            System.out.println("Account already exists");
//            return false;
//        }
//    }
    public static void displayAccountSummery(String nameOfTheAccountTable,Account account){
        account.readDataFromTheAccountTable(nameOfTheAccountTable);
    }
}
