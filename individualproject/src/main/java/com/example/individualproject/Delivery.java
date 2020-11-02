package com.example.individualproject;

import java.time.LocalDate;

public class Delivery {

    private int delivery_id;
    private int sender_id;
    private double weight;
    private LocalDate sendDate;
    private boolean paid;

    public Delivery(int delivery_id, int sender_id, double weight, LocalDate sendDate, boolean paid) {
        this.delivery_id = delivery_id;
        this.sender_id = sender_id;
        this.weight = weight;
        this.sendDate = sendDate;
        this.paid = paid;
    }

    public Delivery(int delivery_id) {
        this.delivery_id = delivery_id;
        this.sender_id = 1;
        this.paid = false;
        this.weight = 12.5;
        this.sendDate = LocalDate.now();
    }
}
