package model.staff;

import model.customer.Customer;

import java.util.ArrayList;
import java.util.HashMap;

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

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public HashMap<Customer, Double> getPayment() {
        return payment;
    }

    public void setPayment(HashMap<Customer, Double> payment) {
        this.payment = payment;
    }

    public ArrayList<Customer> getCustomersServed() {
        return customersServed;
    }

    public void setCustomersServed(ArrayList<Customer> customersServed) {
        this.customersServed = customersServed;
    }

    public Accountant getAccountant() {
        return accountant;
    }

    public void setAccountant(Accountant accountant) {
        this.accountant = accountant;
    }
}
