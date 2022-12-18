package model.staff;

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
            return false;
        }else {
            listOfAccounts.remove(indexOfTheAccount);
            return true;
        }
    }

    public boolean addAccount(Account account){
        int indexOfTheAccount = listOfAccounts.indexOf(account);
        if (indexOfTheAccount == -1){
            //If the object does not exist in the list
            this.listOfAccounts.add(account);
            return true;
        }else {
            return false;
        }
    }
}
