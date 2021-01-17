package com.example.individualproject.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    @NotBlank
    @Size(max = 15)
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_deliveries",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "delivery_id"))
    private Set<Delivery> deliveries = new HashSet<>();

    public User() { }

    public User(Long id, Set<Role> roles) {

    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name, String email, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User(Long id, @NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 100) String password, @NotBlank @Size(max = 15) String phoneNumber, Set<Role> roles, Set<Delivery> deliveries) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.deliveries = deliveries;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles() {
        List<Role> lmaoroles = new ArrayList<>();
        for (Role role : roles) {
            lmaoroles.add(role);
        }
        if (lmaoroles.get(0).getName().equals(ERole.ROLE_Administrator)) {
            roles.add(new Role(ERole.ROLE_Employee));
            roles.add(new Role(ERole.ROLE_Customer));
        } else if (lmaoroles.get(0).getName().equals(ERole.ROLE_Employee)) {
            roles.add(new Role(ERole.ROLE_Customer));
        }
    }

    public void resetRoles() {
        List<Role> lmaoroles = new ArrayList<>();
        for (Role role : roles) {
            lmaoroles.add(role);
        }
        if (lmaoroles.get(0).getName().equals(ERole.ROLE_Administrator)) {
            roles.remove(lmaoroles.get(1));
            roles.remove(lmaoroles.get(2));
        } else if (lmaoroles.get(0).getName().equals(ERole.ROLE_Employee)) {
            roles.remove(lmaoroles.get(1));
        }
    }

    public Set<Delivery> getDeliveries() {
        return deliveries;
    }
}
