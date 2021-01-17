package com.example.individualproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(	name = "deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String address;

    private Double weight;

    private Calendar sendDate;

    @NotBlank
    @Size(max = 100)
    private String paid;

    private Double price;

    @NotBlank
    @Size(max = 20)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    public Delivery(Long id, @NotBlank @Size(max = 100) String address, Double weight, Calendar sendDate, @NotBlank @Size(max = 100) String paid, Double price, @NotBlank @Size(max = 20) String status, User sender) {
        this.id = id;
        this.address = address;
        this.weight = weight;
        this.sendDate = sendDate;
        this.paid = paid;
        this.price = price;
        this.status = status;
        this.sender = sender;
    }

    public Delivery(@NotBlank @Size(max = 100) String address, Double weight, @NotBlank @Size(max = 100) String paid, Double price, User sender) {
        this.address = address;
        this.weight = weight;
        this.paid = paid;
        this.price = price;
        this.sender = sender;
        this.sendDate = Calendar.getInstance();
        this.status = "Registered";
    }

    public Delivery() {
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

    public void setStatus(String status) {
        this.status = status;
    }

    public User getSender() {
        return sender;
    }

    public Double getPrice() {
        return price;
    }
}
