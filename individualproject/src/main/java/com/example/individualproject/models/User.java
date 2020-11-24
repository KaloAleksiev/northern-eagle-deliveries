package com.example.individualproject.models;

public class User {

    private int user_id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String position;

    public User(String name, String email, String password, String phoneNumber, String position) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.position = position;
    }

    public User(int user_id, String name, String email, String password, String phoneNumber, String position) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.position = position;
    }

    public User(int user_id) {
        this.user_id = user_id;
        this.name = "John Doe";
        this.email = "john.doe@hotmail.com";
        this.phoneNumber = "+316736286";
        this.position = "Customer";
        this.password = "pass123";
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
