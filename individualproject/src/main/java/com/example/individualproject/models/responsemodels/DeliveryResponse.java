package com.example.individualproject.models.responsemodels;

import com.example.individualproject.models.User;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class DeliveryResponse {
    private Long id;
    private String address;
    private Double weight;
    private Calendar sendDate;
    private String paid;
    private Double price;
    private String status;
    private User sender;

    public DeliveryResponse(Long id, String address, Double weight, Calendar sendDate, String paid, Double price, String status, User sender) {
        this.id = id;
        this.address = address;
        this.weight = weight;
        this.sendDate = sendDate;
        this.paid = paid;
        this.price = price;
        this.status = status;
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public Double getWeight() {
        return weight;
    }

    public Calendar getSendDate() {
        return sendDate;
    }

    public String getPaid() {
        return paid;
    }

    public String getStatus() {
        return status;
    }

    public User getSender() {
        return sender;
    }

    public Double getPrice() {
        return price;
    }
}
