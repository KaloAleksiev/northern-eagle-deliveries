package com.example.individualproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.HashSet;
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
    private String paid;

    @NotBlank
    @Size(max = 20)
    private String status;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "delivery_users",
            joinColumns = @JoinColumn(name = "delivery_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> sender = new HashSet<>();

    public Delivery(String address, double weight, int year, int month, int day, String paid) {
        this.weight = weight;
        this.address = address;

        sendDate = Calendar.getInstance();
        sendDate.set(year, month, day);

        this.paid = paid;
        this.status = "Registered";
    }

    public Delivery(Long id, String address, double weight, int year, int month, int day, String paid, String status) {
        this.id = id;
        this.address = address;
        this.weight = weight;
        sendDate = Calendar.getInstance();
        sendDate.set(year, month, day);
        this.paid = paid;
        this.status = status;
    }

    public Delivery() {
    }
}
