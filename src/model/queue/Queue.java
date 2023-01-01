package model.queue;

import model.customer.Customer;

public class Queue {
    private String queueType;
    private int queueNumber;
    private QueueManager qms;
    private final int size = 10;
    private Customer[] customers = new Customer[size]; // only 10 slots available in a queue
    private int front, rear;

    public boolean enqueue(Customer customer){
        // Method to add customer to queue
        if (isFull()){
            System.out.println("Queue "+this.getQueueNumber()+" is full");
            return false;
        } else {
            if (front == -1){
                front = 0;
            }
            rear++;
            customers[rear] = customer;
            System.out.println(customer.getLicensePlate() + " Has Been Successfully Added to Queue "+this.getQueueNumber());
            return true;
        }
    }

    public void enqueue(Customer customer, boolean silent){
        // Same as previous with optional printing
        if (isFull()){
            System.out.println("Queue "+this.getQueueNumber()+" is full");
        } else {
            if (front == -1){
                front = 0;
            }
            rear++;
            customers[rear] = customer;
            if (!silent) {
                System.out.println(customer.getLicensePlate() + " Has Been Successfully Added to Queue "+this.getQueueNumber());
            }
        }
    }

    public Customer dequeue(){
        // Method to remove customer from queue
        Customer customer;
        if (isEmpty()){
            System.out.println("Queue "+this.getQueueNumber()+" is Empty");
            return null;
        } else {
            customer = customers[front];
            if (front >= rear){ // reset queue after removing last customer
                front = -1;
                rear = -1;
            } else {
                front++;
            }
            System.out.println("Removed "+customer.getLicensePlate()+" From their Queue");
            return customer;
        }
    }

    public boolean isFull(){
        // Method to validate if queue is full
        return front == 0 && rear == size - 1;
    }

    public boolean isEmpty(){
        // Method to validate if queue is empty
        return front == -1;
    }

    public void display(){
        // Method to display queue
        if (isEmpty()){
            System.out.println("Queue "+this.getQueueNumber()+" is Empty\n");
        } else {
            System.out.println("\nQueue "+this.getQueueNumber());
            int count = 0;
            for (int i = front; i <= rear; i++){
                count += 1;
            }
            System.out.println("Current Capacity: "+count+"/"+customers.length);
            System.out.format("ETA: %.2f mins\n", (count * 2.3)); // prints estimated time to reach dispenser
        }
    }

    // Main constructor
    public Queue(String queueType, int queueNumber) {
        this.queueType = queueType;
        this.queueNumber = queueNumber;
        front = -1;
        rear = -1;
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

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }
}
