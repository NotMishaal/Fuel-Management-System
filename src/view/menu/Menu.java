package view.menu;

import java.util.Scanner;

public class Menu {

    // Get user input, validate in range and return input.
    public static int userInput(int lower, int upper){
        int input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextInt();
        if (input > upper || input < lower){
            throw new ArithmeticException("Please Select a Valid Option");
        }
        return input;
    }

    public static int userSelection() {
        System.out.println("====Main Menu====");
        System.out.println("1. Customer");
        System.out.println("2. Staff");
        System.out.println("3. Quit");

        return userInput(1, 3);
    }

    public static int customerMenu(){
        System.out.println("====Select an Option====");
        System.out.println("1. View Queues");
        System.out.println("2. Join a Queue");
        System.out.println("3. Go Back");
        System.out.println("4. Quit");

        return userInput(1, 4);
    }

    public static int staffMenu(){
        System.out.println("====Select your Role====");
        System.out.println("1. Attendant");
        System.out.println("2. Accountant");
        System.out.println("3. Manager");
        System.out.println("4. Go Back");
        System.out.println("5. Quit");

        return userInput(1, 5);
    }

    public static int attendantMenu(){
        System.out.println("====Select an Option====");
        System.out.println("1. Dispense Fuel");
        System.out.println("2. Go Back");
        System.out.println("3. Quit");

        return userInput(1, 3);
    }

    public static int accountantMenu(){
        System.out.println("====Select an Option====");
        System.out.println("1. View Accounts");
        System.out.println("2. Add Accounts");
        System.out.println("3. Delete Accounts");
        System.out.println("4. View statistics");
        System.out.println("4. Go Back");
        System.out.println("5. Quit");

        return userInput(1,5);
    }

    public static int managerMenu(){
        System.out.println("====Select an Option====");
        System.out.println("1. Verify Repository Capacity");
        System.out.println("2. Refill Repository");
        System.out.println("3. Install Dispenser");
        System.out.println("4. Total fuel dispensed in a day");
        System.out.println("5. Go Back");
        System.out.println("6. Quit");

        return userInput(1, 6);
    }
}
