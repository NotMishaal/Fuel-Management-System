package model.queue;

import model.customer.Customer;

public class Queue {
    private String queueType;
    private int queueNumber;
    private QueueManager qms;
    private Customer[] customers = new Customer[10]; // only 10 slots available in a queue

    // Main constructor
    public Queue(String queueType, int queueNumber) {
        this.queueType = queueType;
        this.queueNumber = queueNumber;
    }


    // Getters and setters
    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public int getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(int queueNumber) {
        this.queueNumber = queueNumber;
    }

    public QueueManager getQms() {
        return qms;
    }

    public void setQms(QueueManager qms) {
        this.qms = qms;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }


    public boolean enqueue(Customer customer){
        // Method to add customer to list
        return false;
    }

    public Customer dequeue(Customer customer){
        // Method to remove customer from list
        return null;
    }

    public boolean isFull(){
        // Method to validate if queue is full
        return false;
    }

    public boolean isEmpty(){
        // Method to validate if queue is empty
        return false;
    }

    public int display(){
        // Method to display queue
        return 0;
    }
}
