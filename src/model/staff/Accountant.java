package model.staff;

import model.customer.Customer;

import java.util.ArrayList;

public class Accountant extends Staff{
    private ArrayList<Account> listOfAccounts;

    public Accountant(String staffID, String name, ArrayList<Account> listOfAccounts) {
        super(staffID, name);
        this.listOfAccounts = listOfAccounts;
    }

    public boolean removeAccount(Account account){
        int indexOfTheAccount = listOfAccounts.indexOf(account); // This is to check if the object parsed exist in the list
        if (indexOfTheAccount == -1){
            //If the object does not exist in the list
            System.out.println("Account not found");
            return false;
        }else {
            listOfAccounts.remove(indexOfTheAccount);
            System.out.println("Account removed successfully");
            return true;
        }
    }

    public boolean addAccount(Account account){
        int indexOfTheAccount = listOfAccounts.indexOf(account);
        if (indexOfTheAccount == -1){
            //If the object does not exist in the list
            this.listOfAccounts.add(account);
            System.out.println("Account added successfully");
            return true;
        }else {
            System.out.println("Account already exists");
            return false;
        }
    }

    public void removeCustomerFromAnAccount(Account account,String customerName){
        account.removeCustomer(customerName);
    }
    public void addCustomerToAnAccount(Account account, Customer customer, Double amount){
        account.addCustomer(customer,amount);
    }

    public void displayAccountSummery(Account account){
        account.displayAccountSummery();
    }
}
