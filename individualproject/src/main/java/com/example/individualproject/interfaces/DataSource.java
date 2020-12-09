package com.example.individualproject.interfaces;

import com.example.individualproject.models.Delivery;
import com.example.individualproject.models.UserOriginal;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public interface DataSource {

    void AddUserToDB(String name, String email, String password, String phone, String position) throws SQLException;
    boolean Login(String givenEmail, String givenPassword) throws SQLException;
    void Logout();
    String GetDeliveryStatusByID(int id) throws SQLException;
    List<Delivery> GetDeliveriesBySenderID(int senderId) throws SQLException;
    void AddDeliveryToDB(int sender_id, String address, double weight, LocalDate sendDate, String paid);
    List<Delivery> GetAllDeliveriesFromDB() throws SQLException;
    List<UserOriginal> GetAllUsersFromDB() throws SQLException;
    void UpdateDeliveryStatus(int delivery_id, String status);

}
