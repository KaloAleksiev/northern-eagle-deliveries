package com.example.individualproject.models.requestmodels;

import com.example.individualproject.models.User;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class DeliveryRequest {
    private String address;
    private Double weight;
    private Calendar sendDate;
    private boolean paid;
    private Long senderId;

    public DeliveryRequest(String address, Double weight, Calendar sendDate, boolean paid, Long senderId) {
        this.address = address;
        this.weight = weight;
        this.sendDate = sendDate;
        this.paid = paid;
        this.senderId = senderId;
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

    public boolean isPaid() {
        return paid;
    }

    public Long getSenderId() {
        return senderId;
    }
}
