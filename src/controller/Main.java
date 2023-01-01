package controller;

import model.customer.Customer;
import model.customer.Ticket;
import model.dispenser.OctaneDispenser;
import model.queue.Queue;
import model.repository.DieselRepository;
import model.repository.OctaneRepository;
import model.staff.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static view.menu.Menu.*;

public class Main {

    public static void main(String[] args) {
        // initialize all variables
        initialize();

        // Create a scanner object
        Scanner scanner = new Scanner(System.in);

        // user input
        while (true) {
            try {
                mainSelection: switch (userSelection()){
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
                                case 3 -> { // go back
                                    break mainSelection;
                                }
                                case 4 -> // quit
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
                                            Thread.sleep(1000);
                                            thread1.stop();
                                            thread2.stop();
                                        }
                                        case 2 -> { // go back
                                        }
                                        case 3 -> //
                                            System.exit(0);
                                    }
                                }
                                case 2 -> { // accountant
                                    switch (accountantMenu()) {
                                        case 1: // view accounts
                                            Account.readDataFromTheAccountTable("account");
                                            break;
                                        case 2: // Add accounts
                                            System.out.println("Enter the customer's number plate: ");
                                            String plateNum = scanner.nextLine();

                                            System.out.println("Enter the customer name: ");
                                            String cusName = scanner.nextLine();

                                            System.out.println("Enter the paid amount: ");
                                            double payment = Double.parseDouble(scanner.nextLine());

                                            System.out.println("Enter the fuel dispensed: ");
                                            double fuelDispensed = Double.parseDouble(scanner.nextLine());

                                            System.out.println("Enter the date(yyyy-mm-dd): ");
                                            String date = scanner.nextLine();

                                            System.out.println("Enter the vehicle category type: ");
                                            String category = scanner.nextLine();

                                            System.out.println("Enter the fuel type: ");
                                            String fuelType = scanner.nextLine();

                                            System.out.println("Enter the dispenser ID: ");
                                            int dispenserID = Integer.parseInt(scanner.nextLine());

                                            Account.addDataToTheAccountTable(
                                                    "account", plateNum, accountant.getName(), cusName, payment, fuelDispensed, date, category, fuelType, dispenserID
                                            );
                                            break;
                                        case 3: // Delete accounts
                                            System.out.println("Enter the customer's number plate: ");
                                            plateNum = scanner.nextLine();
                                            accountant.deleteDataFromAccount("account", plateNum);
                                            break;
                                        case 4: // go back
                                            break;
                                        case 5: //quit
                                            System.exit(0);
                                    }

                                }
                                case 3 -> { // manager
                                    switch (managerMenu()) {
                                        case 1: // verify repo capacity
                                            System.out.println("Diesel level above 500L: " + manager.verifyFuelCapacity(dieselRepository));
                                            System.out.println("Octane level above 500L: " + manager.verifyFuelCapacity(octaneRepository));
                                            break;
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
                                                System.out.println("Invalid choice of fuel type");
                                            }
                                            break;

                                        case 3: // install new dispenser
                                            System.out.println("Please enter fuel type: "); // get fuel type
                                            fuelType = scanner.nextLine();
                                            if (fuelType.equalsIgnoreCase("diesel")){ // fuel type is diesel
                                                System.out.println("Please enter numeric ID for dispenser: "); // get ID
                                                String ID = scanner.nextLine();
                                                try { // Verify input is a number
                                                    manager.installDispenser(new OctaneDispenser(Integer.parseInt(ID)));
                                                } catch (Exception e){
                                                    System.out.println("Invalid ID.");
                                                }
                                            } else if (fuelType.equalsIgnoreCase("octane")) { // fuel type is octane
                                                System.out.println("Please enter numeric ID for dispenser: "); // get ID
                                                String ID = scanner.nextLine();
                                                try { // Verify input is a number
                                                    manager.installDispenser(new OctaneDispenser(Integer.parseInt(ID)));
                                                } catch (Exception e){
                                                    System.out.println("Invalid ID");
                                                }
                                            } else { // fuel type is invalid
                                                System.out.println("Invalid choice of fuel type");
                                            }
                                            break;
                                        case 4: // go back
                                            break;
                                        case 5: // quit
                                            System.exit(0);
                                    }
                                }
                                case 4 -> { // go back
                                    break mainSelection;
                                }
                                case 5 -> // quit
                                    System.exit(0);
                            }
                        }
                    case 3: //quit
                        System.exit(0);
                        break;
                }
            } catch (InputMismatchException | ArithmeticException e) {
                System.out.println("Please Enter a Number Displayed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // declare variables

    // global repositories
    public static DieselRepository dieselRepository = new DieselRepository(430, 1); //TODO: why public?
    public static OctaneRepository octaneRepository = new OctaneRepository(450, 2);

    private static Customer customer;

    // queues
    private static Queue petrolQueue1;
    private static Queue petrolQueue2;
    private static Queue petrolQueue3;
    private static Queue petrolQueue4;
    private static Queue dieselQueue1;
    private static Queue dieselQueue2;
    private static Queue dieselQueue3;
    private static ArrayList<Customer> commonQueue = new ArrayList<>();

    // staff
    private static Attendant petrolAttendant;
    private static Attendant dieselAttendant;
    private static Accountant accountant;
    private static Manager manager;

    // sets up all the variables to be used
    public static void initialize(){
        // queues
        petrolQueue1 = new Queue("Petrol", 1);
        petrolQueue2 = new Queue("Petrol", 2);
        petrolQueue3 = new Queue("Petrol", 3);
        petrolQueue4 = new Queue("Petrol", 4);
        dieselQueue1 = new Queue("Diesel", 5);
        dieselQueue2 = new Queue("Diesel", 6);
        dieselQueue3 = new Queue("Diesel", 7);

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

        // attendants and their queues
        petrolAttendant = new Attendant("ATT0001", "Luke Emilia");
        dieselAttendant = new Attendant("ATT0002", "John Doe");

        petrolAttendant.addQueue(petrolQueue1);
        petrolAttendant.addQueue(petrolQueue2);
        petrolAttendant.addQueue(petrolQueue3);
        petrolAttendant.addQueue(petrolQueue4);

        dieselAttendant.addQueue(dieselQueue1);
        dieselAttendant.addQueue(dieselQueue2);
        dieselAttendant.addQueue(dieselQueue3);

        // staff
        accountant = new Accountant("ACC0001", "Aaron Cho");
        manager = new Manager("MGR0001", "Mike Hawk");

        // customers
        customer = new Customer(1, 0, "CUS0005");
        // customers
        Customer customer1 = new Customer(1, 0, "INA0124");
        Customer customer2 = new Customer(5, 0, "TAC0327");
        Customer customer3 = new Customer(1, 0, "CAR3468");
        Customer customer4 = new Customer(5, 0, "VJB4815");
        Customer customer5 = new Customer(1, 0, "KMS6346");
        Customer customer6 = new Customer(2, 0, "TUK7676");
        Customer customer7 = new Customer(3, 0, "VRM9417");
        Customer customer8 = new Customer(4, 1, "BUS1234");
        Customer customer9 = new Customer(5, 1, "TRK4891");
        Customer customer10 = new Customer(5, 1, "ALK4849");

        // tickets
        Ticket ticket = new Ticket("TK0");
        Ticket ticket1 = new Ticket("TK1");
        Ticket ticket2 = new Ticket("TK2");
        Ticket ticket3 = new Ticket("TK3");
        Ticket ticket4 = new Ticket("TK4");
        Ticket ticket5 = new Ticket("TK5");
        Ticket ticket6 = new Ticket("TK6");
        Ticket ticket7 = new Ticket("TK7");
        Ticket ticket8 = new Ticket("TK8");
        Ticket ticket9 = new Ticket("TK9");
        Ticket ticket10 = new Ticket("TK10");

        // add tickets to customers
        customer.setTicket(ticket);
        customer1.setTicket(ticket1);
        customer2.setTicket(ticket2);
        customer3.setTicket(ticket3);
        customer4.setTicket(ticket4);
        customer5.setTicket(ticket5);
        customer6.setTicket(ticket6);
        customer7.setTicket(ticket7);
        customer8.setTicket(ticket8);
        customer9.setTicket(ticket9);
        customer10.setTicket(ticket10);

        // add preset customers to queues
        petrolQueue1.enqueue(customer1, true);
        petrolQueue1.enqueue(customer2, true);
        petrolQueue1.enqueue(customer3, true);
        petrolQueue2.enqueue(customer4, true);
        petrolQueue2.enqueue(customer5, true);
        petrolQueue3.enqueue(customer6, true);
        petrolQueue4.enqueue(customer7, true);
        dieselQueue1.enqueue(customer8, true);
        dieselQueue2.enqueue(customer9, true);
        dieselQueue3.enqueue(customer10, true);
    }
}
