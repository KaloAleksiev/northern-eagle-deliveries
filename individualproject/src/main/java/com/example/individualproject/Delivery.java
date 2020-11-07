package com.example.individualproject;

import java.time.LocalDate;
import java.util.Calendar;

public class Delivery {

    private int delivery_id;
    private int sender_id;
    private String address;
    private double weight;
    private Calendar sendDate;
    private boolean paid;
    private String status;

    public Delivery(int sender_id, String address, double weight, int year, int month, int day, boolean paid) {
        this.sender_id = sender_id;
        this.weight = weight;
        this.address = address;

        sendDate = Calendar.getInstance();
        sendDate.set(year, month, day);

        this.paid = paid;
        this.status = "Registered";
    }

    public Delivery(int delivery_id, int sender_id, String address, double weight, int year, int month, int day, boolean paid, String status) {
        this.delivery_id = delivery_id;
        this.sender_id = sender_id;
        this.address = address;
        this.weight = weight;
        sendDate = Calendar.getInstance();
        sendDate.set(year, month, day);
        this.paid = paid;
        this.status = status;
    }

    public Delivery(int delivery_id) {
        this.delivery_id = delivery_id;
        this.sender_id = 1;
        this.paid = false;
        this.weight = 12.5;
        sendDate = Calendar.getInstance();
        sendDate.set(2020, 11, 04);
    }

    public int getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(int delivery_id) {
        this.delivery_id = delivery_id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Calendar getSendDate() {
        return sendDate;
    }

    public void setSendDate(Calendar sendDate) {
        this.sendDate = sendDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
