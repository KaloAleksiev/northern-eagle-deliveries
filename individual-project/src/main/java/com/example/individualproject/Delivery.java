package com.example.individualproject;

import java.time.LocalDate;

public class Delivery {

    private int id;
    private LocalDate sendDate;
    private double weight;
    private Customer sender;

    public Delivery(int id, LocalDate sendDate, double weight, Customer sender) {
        this.id = id;
        this.sendDate = sendDate;
        this.weight = weight;
        this.sender = sender;
    }

    public Delivery(int id) {
        this.id = id;
        this.sendDate = LocalDate.now();
        this.sender = new Customer(1);
        this.weight = 12;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }
}
