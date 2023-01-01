package controller;

import model.customer.Customer;
import model.queue.Queue;
import model.repository.DieselRepository;
import model.repository.OctaneRepository;
import model.staff.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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

        octaneRepository.setAvailableFuel(100000);
        dieselRepository.setAvailableFuel(100000);

        octaneRepository.setCapacity(100000);
        dieselRepository.setCapacity(100000);
        // Initialize staff members

        Attendant petrolAttendant = new Attendant("ATT0001", "Luke Emia");
        Attendant dieselAttendant = new Attendant("ATT0002", "John Doe");

        petrolAttendant.addQueue(petrolQueue1);
        petrolAttendant.addQueue(petrolQueue2);
        petrolAttendant.addQueue(petrolQueue3);
        petrolAttendant.addQueue(petrolQueue4);

        dieselAttendant.addQueue(dieselQueue1);
        dieselAttendant.addQueue(dieselQueue2);
        dieselAttendant.addQueue(dieselQueue3);

        Customer customer = new Customer(1, 0, "CUS0005");

        Accountant accountant = new Accountant("ACC0001", "Aaron Cho");
        Manager manager = new Manager("MGR0001", "Mike Hawk");

        // Create a scanner object
        Scanner scanner = new Scanner(System.in);

        // user input
        while (true) {
            try {
                switch (userSelection()){
                    case 1: // user is a customer
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
                                    switch (accountantMenu()) {
                                        case 1: // view accounts
                                            Account.readDataFromTheAccountTable("account");
                                        case 2: // delete accounts
                                            // TODO Account.deleteData(customer.getLicensePlate());
                                        case 3: // quit
                                            System.exit(0);
                                            break;
                                    }
                                }
                                case 3 -> { // manager
                                    switch (managerMenu()) {
                                        case 1: // verify repo capacity
                                            System.out.println("Diesel level above 500L: " + manager.verifyFuelCapacity(dieselRepository));
                                            System.out.println("Octane level above 500L: " + manager.verifyFuelCapacity(octaneRepository));
                                        case 2: // refill repo
                                            System.out.println("Please enter fuel type: "); // get fuel type
                                            String fuelType = scanner.nextLine();
                                            if (fuelType.equalsIgnoreCase("diesel")){ // fuel type is diesel
                                                System.out.println("Please enter amount in liters: "); // get qnt
                                                String amount = scanner.nextLine();
                                                try { // Verify input is a number
                                                    manager.refillRepository(dieselRepository, Double.parseDouble(amount));
                                                } catch (Exception e){
                                                    System.out.println("Invalid quantity, refill failed.");
                                                }
                                            } else if (fuelType.equalsIgnoreCase("octane")) { // fuel type is octane
                                                System.out.println("Please enter amount in liters: "); // get qnt
                                                String amount = scanner.nextLine();
                                                try { // Verify input is a number
                                                    manager.refillRepository(octaneRepository, Double.parseDouble(amount));
                                                } catch (Exception e){
                                                    System.out.println("Invalid quantity, refill failed.");
                                                }
                                            } else { // fuel type is invalid
                                                System.out.println("Invalid choice of fuel");
                                            }

                                        case 3: // install new dispenser
                                            break;
                                        case 4: //quit
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
