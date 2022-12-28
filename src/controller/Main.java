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
        Queue dieselQueue1 = new Queue("Diesel", 5);
        Queue dieselQueue2 = new Queue("Diesel", 6);
        Queue dieselQueue3 = new Queue("Diesel", 7);


        // user input
        while (true) {
            try {
                switch (userSelection()){
                    case 1: //user is a customer
                        Customer customer = new Customer(1, 0, "CUS0005");
                        while (true) {
                            switch (customerMenu()){
                                case 1: // view queues
                                    System.out.println("-Queues Available For You-");
                                    if (customer.getFuelType() == 0){ // petrol queues
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
                                    } else { // diesel queues
                                        if (customer.getVehicleType() == 4){
                                            dieselQueue1.display();
                                        } else {
                                            dieselQueue2.display();
                                            dieselQueue3.display();
                                        }
                                    }
                                    break;

                                case 2: // join queue
                                    System.out.println("-Assigning to Best Available Queue-");
                                    if (customer.getFuelType() == 0) { // petrol vehicles
                                        if (customer.getVehicleType() == 1){
                                            if (!petrolQueue1.enqueue(customer)){ // try to add customer to queue 1
                                                if (!petrolQueue2.enqueue(customer)){ // try to add customer to queue 2
                                                    //TODO: add to common waiting queue
                                                }
                                            }
                                        } else if (customer.getVehicleType() == 2){
                                            if (!petrolQueue3.enqueue(customer)){
                                                //TODO: add to common waiting queue
                                            }
                                        } else if (customer.getVehicleType() == 3){
                                            if (!petrolQueue4.enqueue(customer)){
                                                //TODO: add to common waiting queue
                                            }
                                        } else {
                                            if (!petrolQueue2.enqueue(customer)){
                                                //TODO: add to common waiting queue
                                            }
                                        }
                                    } else {
                                        if (customer.getVehicleType() == 4){
                                            if (!dieselQueue1.enqueue(customer)){
                                                //TODO: add to common waiting queue
                                            } else {
                                                if (!dieselQueue2.enqueue(customer)){
                                                    if (!dieselQueue3.enqueue(customer)){
                                                        //TODO: add to common waiting queue
                                                    }
                                                }
                                            }
                                        }
                                    }
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
