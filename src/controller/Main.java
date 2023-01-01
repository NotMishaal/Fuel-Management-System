package controller;

import model.customer.Customer;
import model.queue.Queue;
import model.repository.DieselRepository;
import model.repository.OctaneRepository;
import model.staff.*;

import java.util.ArrayList;
import java.util.InputMismatchException;

import static view.menu.Menu.*;

public class Main {
    // Initialize repositories
    public static DieselRepository dieselRepository = new DieselRepository(430, 1);
    public static OctaneRepository octaneRepository = new OctaneRepository(450, 2);
    public static void main(String[] args) {
        // Initialize queues
        Queue petrolQueue1 = new Queue("Petrol", 1);
        Queue petrolQueue2 = new Queue("Petrol", 2);
        Queue petrolQueue3 = new Queue("Petrol", 3);
        Queue petrolQueue4 = new Queue("Petrol", 4);
        Queue dieselQueue1 = new Queue("Diesel", 5);
        Queue dieselQueue2 = new Queue("Diesel", 6);
        Queue dieselQueue3 = new Queue("Diesel", 7);
        ArrayList<Customer> commonQueue = new ArrayList<>();

        // Add the *compositions* of dispensers to the repositories
        dieselRepository.createDispenser(1);
        dieselRepository.createDispenser(2);
        dieselRepository.createDispenser(3);
        octaneRepository.createDispenser(4);
        octaneRepository.createDispenser(5);
        octaneRepository.createDispenser(6);
        octaneRepository.createDispenser(7);

        // Initialize staff attendants

        Attendant petrolAttendant = new Attendant("ATT0001", "Luke Emia");
        Attendant dieselAttendant = new Attendant("ATT0002", "John Doe");

        petrolAttendant.addQueue(petrolQueue1);
        petrolAttendant.addQueue(petrolQueue2);
        petrolAttendant.addQueue(petrolQueue3);
        petrolAttendant.addQueue(petrolQueue4);

        dieselAttendant.addQueue(dieselQueue1);
        dieselAttendant.addQueue(dieselQueue2);
        dieselAttendant.addQueue(dieselQueue3);

        // user input
        while (true) {
            try {
                switch (userSelection()){
                    case 1: // user is a customer
                        Customer customer = new Customer(1, 0, "CUS0005");
                        while (true) {
                            switch (customerMenu()) {
                                case 1 -> { // view queues
                                    System.out.println("-Queues Available For You-");
                                    if (customer.getFuelType() == 0) { // petrol queues
                                        if (customer.getVehicleType() == 1) {
                                            petrolQueue1.display();
                                            petrolQueue2.display();
                                        } else if (customer.getVehicleType() == 2) {
                                            petrolQueue3.display();
                                        } else if (customer.getVehicleType() == 3) {
                                            petrolQueue4.display();
                                        } else {
                                            petrolQueue2.display();
                                        }
                                    } else { // diesel queues
                                        if (customer.getVehicleType() == 4) {
                                            dieselQueue1.display();
                                        } else {
                                            dieselQueue2.display();
                                            dieselQueue3.display();
                                        }
                                    }
                                }
                                case 2 -> { // join queue
                                    System.out.println("-Assigning to Best Available Queue-");
                                    if (customer.getFuelType() == 0) { // petrol vehicles
                                        if (customer.getVehicleType() == 1) {
                                            if (!petrolQueue1.enqueue(customer)) { // try to add customer to queue 1
                                                if (!petrolQueue2.enqueue(customer)) { // try to add customer to queue 2
                                                    commonQueue.add(customer);
                                                }
                                            }
                                        } else if (customer.getVehicleType() == 2) {
                                            if (!petrolQueue3.enqueue(customer)) {
                                                commonQueue.add(customer);
                                            }
                                        } else if (customer.getVehicleType() == 3) {
                                            if (!petrolQueue4.enqueue(customer)) {
                                                commonQueue.add(customer);
                                            }
                                        } else {
                                            if (!petrolQueue2.enqueue(customer)) {
                                                commonQueue.add(customer);
                                            }
                                        }
                                    } else {
                                        if (customer.getVehicleType() == 4) {
                                            if (!dieselQueue1.enqueue(customer)) {
                                                commonQueue.add(customer);
                                            } else {
                                                if (!dieselQueue2.enqueue(customer)) {
                                                    if (!dieselQueue3.enqueue(customer)) {
                                                        commonQueue.add(customer);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                case 3 -> // quit
                                        System.exit(0);
                            }
                        }
                    case 2: // user is a staff member
                        while (true) {
                            switch (staffMenu()) {
                                case 1 -> { // attendant
                                    switch (attendantMenu()) {
                                        case 1 -> { // dispense fuel
                                            Thread thread1 = new Thread(petrolAttendant);
                                            Thread thread2 = new Thread(dieselAttendant);
                                            thread1.start();
                                            thread2.start();
                                        }
                                        case 2 -> //quit
                                                System.exit(0);
                                    }
                                }
                                case 2 -> { // accountant
                                    Accountant accountant = new Accountant("ACC0001", "Aaron Cho");
                                    switch (accountantMenu()) {
                                        case 1: // view accounts
                                            break;
                                        case 2: // modify accounts
                                            break;
                                        case 3: // quit
                                            System.exit(0);
                                            break;
                                    }
                                }
                                case 3 -> { // manager
                                    Manager manager = new Manager("MGR0001", "Mike Hawk");
                                    switch (managerMenu()) {
                                        case 1: // verify repo capacity
                                            break;
                                        case 2: // install new dispenser
                                            break;
                                        case 3: //quit
                                            System.exit(0);
                                            break;
                                    }
                                }
                            }
                        }
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
