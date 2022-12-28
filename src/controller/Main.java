package controller;

import model.customer.Customer;
import model.queue.Queue;

import java.util.InputMismatchException;

import static view.menu.Menu.customerMenu;
import static view.menu.Menu.userSelection;

public class Main {
    public static void main(String[] args) {
        // initialization
        //TODO: init all relevant objects
        Queue petrolQueue1 = new Queue("Petrol", 1);
        Queue petrolQueue2 = new Queue("Petrol", 2);
        Queue petrolQueue3 = new Queue("Petrol", 3);
        Queue petrolQueue4 = new Queue("Petrol", 4);
        Queue dieselQueue1 = new Queue("Diesel", 1);
        Queue dieselQueue2 = new Queue("Diesel", 2);
        Queue dieselQueue3 = new Queue("Diesel", 3);


        // user input
        while (true) {
            try {
                switch (userSelection()){
                    case 1: //user is a customer
                        Customer customer = new Customer(1, 0, "CUS0005");
                        while (true) {
                            switch (customerMenu()){
                                case 1: // view queues
                                    if (customer.getFuelType() == 0){
                                        if (customer.getVehicleType() == 1){
                                            petrolQueue1.display();
                                            petrolQueue2.display();
                                        } else if (customer.getVehicleType() == 2){
                                            petrolQueue3.display();
                                        } else if (customer.getVehicleType() == 3){
                                            petrolQueue4.display();
                                        } else {
                                            petrolQueue2.display();
                                        }
                                    }
                                    break;

                                case 2: // join queue
                                    break;
                                case 3: // quit
                                    System.exit(0);
                                    break;
                            }
                        }
                    case 2: //user is a staff member
                        System.out.println("sta");
                        break;
                    case 3: //quit
                        System.exit(0);
                        break;
                }
            } catch (InputMismatchException | ArithmeticException e) {
                System.out.println("Please Enter a Number Displayed");;
            }
        }
    }
}
