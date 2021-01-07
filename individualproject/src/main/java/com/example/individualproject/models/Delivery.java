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

    @NotBlank
    @Size(max = 100)
    private Double weight;

    @NotBlank
    @Size(max = 20)
    private Calendar sendDate;

    @NotBlank
    @Size(max = 10)
    private boolean paid;

    @NotBlank
    @Size(max = 20)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    public Delivery(Long id, @NotBlank @Size(max = 100) String address, @NotBlank @Size(max = 100) Double weight, @NotBlank @Size(max = 20) Calendar sendDate, @NotBlank @Size(max = 10) boolean paid, @NotBlank @Size(max = 20) String status, User sender) {
        this.id = id;
        this.address = address;
        this.weight = weight;
        this.sendDate = sendDate;
        this.paid = paid;
        this.status = status;
        this.sender = sender;
    }

    public Delivery(@NotBlank @Size(max = 100) String address, @NotBlank @Size(max = 100) Double weight, @NotBlank @Size(max = 20) Calendar sendDate, @NotBlank @Size(max = 10) boolean paid, User sender) {
        this.address = address;
        this.weight = weight;
        this.sendDate = sendDate;
        this.paid = paid;
        this.sender = sender;
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

    public boolean isPaid() {
        return paid;
    }

    public String getStatus() {
        return status;
    }

    public User getSender() {
        return sender;
    }
}
