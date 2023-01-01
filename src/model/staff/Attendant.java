package model.staff;

import model.customer.Customer;
import model.customer.Ticket;
import model.dispenser.DieselDispenser;
import model.dispenser.FuelDispenseManager;
import model.dispenser.OctaneDispenser;
import model.queue.Queue;

import java.util.ArrayList;
import java.util.Objects;
import java.util.spi.AbstractResourceBundleProvider;

public class Attendant extends Staff implements Runnable{
    private ArrayList<Customer> listOfCustomers = new ArrayList<>();
    private ArrayList<Queue> queues = new ArrayList<>();
    private FuelDispenseManager dispenser;

    public Attendant(String staffID, String name) {
        super(staffID, name);
    }

    public void dispense(FuelDispenseManager d,double quantity,Customer customer){
        //Dispense fuel
        d.dispenseFuel(quantity); // takes fuel out of a repository
        this.listOfCustomers.add(customer); //Once the vehicle got pumped, customer details will get recorded
    }

    public boolean addQueue(Queue queue){
        return queues.add(queue);
    }

    @Override
    public void run() {
        try {
            for (Queue currQueue : queues) {
                if (Objects.equals(currQueue.getQueueType(), "Petrol")){
                    dispenser = new OctaneDispenser(1);
                } else {
                    dispenser = new DieselDispenser(1);
                }
                for (int i = currQueue.getFront(); i <= currQueue.getRear(); i++){ // loop through all customers in the current queue
                    Customer currCustomer = currQueue.dequeue();
                    System.out.println("Processing customer: "+currCustomer.getLicensePlate());
                    Ticket currTicket = currCustomer.getTicket();
                    currTicket.setFuelDispensed(45);
                    dispense(dispenser, 45, currCustomer);
                    System.out.println("Successfully dispensed fuel for "+currCustomer.getLicensePlate());
                }
            }
        } catch (NullPointerException e) {
            System.out.println("No customers in queue");
            System.exit(0);
        }
    }
}
