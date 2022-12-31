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

    public Customer dequeue(){
        // Method to remove customer from queue
        Customer customer;
        if (isEmpty()){
            System.out.println("Queue is Empty");
            return null;
        } else {
            customer = customers[front];
            if (front >= rear){ // reset queue after removing last customer
                front = -1;
                rear = -1;
            } else {
                front++;
            }
            System.out.println("Removed "+customer+" From their Queue");
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
            System.out.println("Queue "+this.getQueueNumber()+" is Empty");
        } else {
            System.out.println("Current Capacity of Queue "+this.getQueueNumber()+": "+customers.length);
            System.out.println("Customers in Queue "+this.getQueueNumber()+": ");
            for (int i = front; i <= rear; i++){
                System.out.println(customers[i] + " ");
            }
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
}
