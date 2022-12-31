package model.staff;

import model.customer.Customer;
import model.customer.Ticket;
import model.dispenser.FuelDispenseManager;
import model.queue.Queue;

import java.util.ArrayList;

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
                for (int i = currQueue.getFront(); i <= currQueue.getRear(); i++){ // loop through all customers in the current queue
                    Customer currCustomer = currQueue.dequeue();
                    System.out.println("Processing customer: "+currCustomer.getLicensePlate());
                    Ticket currTicket = currCustomer.getTicket();
                    currTicket.setFuelDispensed(45);
                    dispense(dispenser, 45, currCustomer);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("No customers in queue");;
        }
    }
}
