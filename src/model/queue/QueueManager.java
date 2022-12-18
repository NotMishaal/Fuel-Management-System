package model.queue;

import model.customer.Customer;
import model.customer.Ticket;

import java.util.ArrayList;

public class QueueManager {
    private ArrayList<Ticket> tickets;
    private ArrayList<Queue> queues;


    // Getters and setters
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public ArrayList<Queue> getQueues() {
        return queues;
    }

    public void setQueues(ArrayList<Queue> queues) {
        this.queues = queues;
    }

    public boolean addToTickets(Ticket ticket){
        return tickets.add(ticket);
    }

    public boolean removeFromTickets(Ticket ticket){
        return tickets.remove(ticket);
    }

    public boolean addToQueues(Queue queue){
        return queues.add(queue);
    }

    public boolean removeFromQueues(Queue queue){
        return queues.remove(queue);
    }

    public boolean issueTicket(Customer customer){
        // Issues a ticket to a customer
        return false;
    }

    public boolean assignToQueue(Customer customer){
        // Assigns a customer to a queue
        return false;
    }
}
