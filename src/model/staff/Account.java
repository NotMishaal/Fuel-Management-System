package model.staff;

import model.customer.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Account {
    private String accountID;
    private HashMap<Customer,Double> payment;
    private ArrayList<Customer> customersServed;
    private Accountant accountant;

    public Account(String accountID, HashMap<Customer, Double> payment, ArrayList<Customer> customersServed, Accountant accountant) {
        this.accountID = accountID;
        this.payment = payment;
        this.customersServed = customersServed;
        this.accountant = accountant;
    }

    public static void main(String[] args) {
        //This is to create the database
        String url = "jdbc:mysql://localhost:3306/account";
        String userName = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url,userName,password);

            Statement statement = connection.createStatement();

            addDataToTheAccountTable(12,"Yujith", "Inuka", 5000, 20);
            readDataFromTheAccountTable(url,userName,password);

            //Closing the connection
            connection.close();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static void readDataFromTheAccountTable(String url,String userName, String password){
        //This is to retrieve the data from the account table which is in the Account DB
        try {
            Connection connection = DriverManager.getConnection(url,userName,password);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from account");

            //Displaying column names
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            System.out.println(rsMetaData.getColumnName(1) + " " + rsMetaData.getColumnName(2) + " " + rsMetaData.getColumnName(3) + " " + rsMetaData.getColumnName(4) + " " + rsMetaData.getColumnName(5));

            //Displaying the data in the table
            while (resultSet.next()){
                System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2) + "  " + resultSet.getString(3) + "  " + resultSet.getDouble(4) + "  " + resultSet.getDouble(5));
            }

        }catch (Exception e){
            System.out.println(e);
        }

    }

    private static void addDataToTheAccountTable(int accountID,String accountantName,String customerName,double paidAmount,double fuelDispensed){
        //This is to add new data to the account table
        String url = "jdbc:mysql://localhost:3306/account";
        try {
            Connection connection = DriverManager.getConnection(url,"root","");
            Statement statement = connection.createStatement();
            String query = "insert into account (Account_ID,Accountant_Name,Customer,Paid_Amount,Fuel_Dispensed) values(?,?,?,?,?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1,accountID);
            preparedStmt.setString(2,accountantName);
            preparedStmt.setString(3,customerName);
            preparedStmt.setDouble(4,paidAmount);
            preparedStmt.setDouble(5,fuelDispensed);
            preparedStmt.execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void displayAccountSummery(){
        System.out.println("Summery of "+this.accountID+ " account: ");
        System.out.println("Details of customers with payments: ");
        for (Map.Entry<Customer,Double> entry: payment.entrySet()){
            System.out.println(entry.getKey().getName() + ": "+ entry.getValue());
        }
        System.out.println("For more information, please reach "+ this.accountant.getName());
    }

    public void removeCustomer(String customerName){
        removeCustomerFromHashMap(customerName);
        removeCustomerFromArrayList(customerName);
    }

    public void addCustomer(Customer customer,Double amount){
        addCustomerToTheHashMap(customer,amount);
        addCustomerToTheArrayList(customer);
    }

    private boolean removeCustomerFromHashMap(String customerName){
        for (Map.Entry<Customer,Double> entry: payment.entrySet()){
            //Go through every key and checks if the customer's name is equal to the name given
            if (entry.getKey().getName().equals(customerName)){
                payment.remove(entry.getKey());
                System.out.println("Customer removed from the 'payments' hashMap");
                return true;
            }
        }
        System.out.println("Customer cannot be found in hashMap");
        return false;
    }

    private boolean removeCustomerFromArrayList(String customerName){
        for (int i=0;i<customersServed.size();i++){
            //This is to loop through each customer object and check if there is a customer
            // who has a name which is similar to the name given inside the function parameter
            if (customersServed.get(i).getName().equals(customerName)){
                customersServed.remove(i);
                System.out.println("Customer removed from the 'customersServed' arrayList");
                return true;
            }
        }
        System.out.println("Customer cannot be found in the arrayList");
        return false;
    }

    private boolean addCustomerToTheHashMap(Customer customer,Double amount){
        for (Map.Entry<Customer,Double> entry: payment.entrySet()){
            // Go through each key and check if it's equal to the customer object given within
            // the function parameter
            if (entry.getKey().equals(customer)){
                System.out.println("Customer already exists in the 'payments' hashMap");
                return false;
            }
        }
        payment.put(customer,amount);
        System.out.println("Customer added to the 'payments' hashMap successfully");
        return true;
    }

    private boolean addCustomerToTheArrayList(Customer customer){
        for (int i=0;i<customersServed.size();i++){
            if (customersServed.get(i).equals(customer)){
                System.out.println("Customer already exists in the 'customersServed' arrayList");
                return false;
            }
        }
        System.out.println("Customer added to the 'customersServed' arrayList successfully");
        return true;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public HashMap<Customer, Double> getPayment() {
        return payment;
    }

    public ArrayList<Customer> getCustomersServed() {
        return customersServed;
    }

    public Accountant getAccountant() {
        return accountant;
    }

    public void setAccountant(Accountant accountant) {
        this.accountant = accountant;
    }
}
