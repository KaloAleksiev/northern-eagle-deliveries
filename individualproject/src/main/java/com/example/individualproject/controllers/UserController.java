package com.example.individualproject.controllers;

import com.example.individualproject.datasources.DataControl;
import com.example.individualproject.interfaces.DataSource;
import com.example.individualproject.models.User;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    DataSource dc = new DataControl();

    @GetMapping("/user")
    public User customer(@RequestParam(value = "id", defaultValue = "1") int id)
    {
        return new User(id);
    }

    @GetMapping("/users")
    public List<User> GetAllUsers() throws SQLException {
        return dc.GetAllUsersFromDB();
    }

    @PostMapping("/newuser")
    public void AddNewUser(@RequestBody User user) throws SQLException {
        dc.AddUserToDB(user.getName(), user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.getPosition());
    }
}
