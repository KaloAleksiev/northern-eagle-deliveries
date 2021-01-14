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
    private String status;
    private User sender;

    public DeliveryResponse(Long id, String address, Double weight, Calendar sendDate, String paid, String status, User sender) {
        this.id = id;
        this.address = address;
        this.weight = weight;
        this.sendDate = sendDate;
        this.paid = paid;
        this.status = status;
        this.sender = sender;
    }
}
