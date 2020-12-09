package com.example.individualproject.datasources;

import com.example.individualproject.interfaces.DataSource;
import com.example.individualproject.models.Delivery;
import com.example.individualproject.models.UserOriginal;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DataControl implements DataSource {
    private UserOriginal loggedIn = null;

    // init database constants
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/sioux";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String MAX_POOL = "250";

    private Connection connection;
    private Properties properties;

    // create properties
    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }

    // connect database
    public Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // disconnect database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void AddUserToDB(String name, String email, String password, String phone, String position) throws SQLException {
        UserOriginal userOriginal = new UserOriginal(name, email, password, phone, position);
        Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        String sql = "INSERT INTO `user` (`name`, `email`, `password`, `phone_number`, `position`) VALUES ('" + name + "', '" + email + "', '" + password + "', '" + phone + "', '" + position + "');";
        try {
            PreparedStatement statement = this.connect().prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.disconnect();
        }
    }

    public boolean Login(String givenEmail, String givenPassword) throws SQLException {
        Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        String sql = "SELECT user_id, name, email, password, phone_number, position FROM `user` WHERE email IS " + givenEmail + " AND password IS " + givenPassword + ");";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int user_id = rs.getInt("user_id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String phone_number = rs.getString("phone_number");
            String position = rs.getString("position");
            if (rs.wasNull()) {
                return false;
            }
            else {
                UserOriginal userOriginal = new UserOriginal(user_id, name, email, password, phone_number, position);
                loggedIn = userOriginal;
                return true;
            }
        }
        this.disconnect();
        return false;
    }

    public void Logout() {
        loggedIn = null;
    }

    public String GetDeliveryStatusByID(int id) throws SQLException {
        String status = "";
        Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        String sql = "SELECT status FROM `delivery` WHERE delivery_id = " + id + ";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                status = rs.getString("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.disconnect();
            return status;
        }
    }

    public List<Delivery> GetDeliveriesBySenderID(int senderId) throws SQLException {
        List<Delivery> deliveries = new ArrayList<>();
        Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM `delivery` WHERE sender_id IS " + senderId + ";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int delivery_id = rs.getInt("delivery_id");
                int sender_id = rs.getInt("sender_id");
                String address = rs.getString("address");
                double weight = rs.getDouble("weight");
                String date = rs.getString("send_date");
                String dateYear = date.substring(0, 4);
                String dateMonth = date.substring(5, 7);
                String dateDay = date.substring(8);
                String paid = rs.getString("paid");
                String status = rs.getString("status");
                Delivery delivery = new Delivery(delivery_id, sender_id, address, weight, Integer.parseInt(dateYear), Integer.parseInt(dateMonth), Integer.parseInt(dateDay), paid, status);
                deliveries.add(delivery);
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            this.disconnect();
            return deliveries;
        }
    }

    public void AddDeliveryToDB(int sender_id, String address, double weight, LocalDate sendDate, String paid) {
        String sql = "INSERT INTO `delivery` (`sender_id`, `address`, `weight`, `send_date`, `paid`) VALUES ('" + sender_id + "', '" + address + "', '" + weight + "', '" + sendDate + "', '" + paid + "');";
        try {
            PreparedStatement statement = this.connect().prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.disconnect();
        }
    }

    public List<Delivery> GetAllDeliveriesFromDB() throws SQLException {
        List<Delivery> deliveries = new ArrayList<>();
        Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM `delivery`;";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int delivery_id = rs.getInt("delivery_id");
                int sender_id = rs.getInt("sender_id");
                String address = rs.getString("address");
                double weight = rs.getDouble("weight");
                String date = rs.getString("send_date");
                String dateYear = date.substring(0, 4);
                String dateMonth = date.substring(5, 7);
                String dateDay = date.substring(8);
                String paid = rs.getString("paid");
                String status = rs.getString("status");
                Delivery delivery = new Delivery(delivery_id, sender_id, address, weight, Integer.parseInt(dateYear), Integer.parseInt(dateMonth), Integer.parseInt(dateDay), paid, status);
                deliveries.add(delivery);
                }
            } catch(SQLException e){
                e.printStackTrace();
            } finally{
                this.disconnect();
                return deliveries;
            }
        }

    public List<UserOriginal> GetAllUsersFromDB() throws SQLException {
        List<UserOriginal> usersOriginal = new ArrayList<>();
        Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM `user`;";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone_number = rs.getString("phone_number");
                String position = rs.getString("position");
                UserOriginal userOriginal = new UserOriginal(user_id, name, email, password, phone_number, position);
                usersOriginal.add(userOriginal);
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            this.disconnect();
            return usersOriginal;
        }
    }

    public void UpdateDeliveryStatus(int delivery_id, String status) {
        String sql = "UPDATE delivery SET status = '" + status + "' WHERE delivery_id = " + delivery_id;
        try {
            PreparedStatement statement = this.connect().prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.disconnect();
        }
    }

}
