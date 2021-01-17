package com.example.individualproject.models.requestmodels;

import com.example.individualproject.models.User;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class DeliveryRequest {
    private String address;
    private Double weight;
    private Calendar sendDate;
    private String paid;
    private Double price;
    private Long senderId;
    private String status;

    public DeliveryRequest(String address, Double weight, String paid, Double price, Long senderId) {
        this.address = address;
        this.weight = weight;
        this.sendDate = Calendar.getInstance();
        this.paid = paid;
        this.price = price;
        this.senderId = senderId;
        this.status = "Registered";
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

    public Long getSenderId() {
        return senderId;
    }

    public Double getPrice() {
        return price;
    }
}
