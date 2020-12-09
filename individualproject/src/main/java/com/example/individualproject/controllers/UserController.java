package com.example.individualproject.controllers;

import com.example.individualproject.datasources.DataControl;
import com.example.individualproject.interfaces.DataSource;
import com.example.individualproject.models.UserOriginal;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    DataSource dc = new DataControl();

    @GetMapping("/user")
    public UserOriginal customer(@RequestParam(value = "id", defaultValue = "1") int id)
    {
        return new UserOriginal(id);
    }

    @GetMapping("/users")
    public List<UserOriginal> GetAllUsers() throws SQLException {
        return dc.GetAllUsersFromDB();
    }

    @PostMapping("/newuser")
    public void AddNewUser(@RequestBody UserOriginal userOriginal) throws SQLException {
        dc.AddUserToDB(userOriginal.getName(), userOriginal.getEmail(), userOriginal.getPassword(), userOriginal.getPhoneNumber(), userOriginal.getPosition());
    }
}
