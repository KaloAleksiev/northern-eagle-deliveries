package com.example.individualproject.controllers;

import com.example.individualproject.logic.UserLogic;
import com.example.individualproject.models.User;
import com.example.individualproject.repository.RoleRepository;
import com.example.individualproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserLogic userLogic;

    @PatchMapping("/admin/makeemployee/{id}")
    public void makeEmployee(@PathVariable Long id) {
        userLogic.makeEmployee(id);
    }

    @PatchMapping("/admin/makecustomer/{id}")
    public void makeCustomer(@PathVariable Long id) {
        userLogic.makeCustomer(id);
    }

    @PatchMapping("/admin/makeadmin/{id}")
    public void makeAdmin(@PathVariable Long id) {
        userLogic.makeAdmin(id);
    }

    @GetMapping("/mod/getallusers")
    public List<User> getAllUsers() {
        return userLogic.getAllUsers();
    }

    @DeleteMapping("/user/deleteaccount/{id}")
    public void deleteAccountById(@PathVariable Long id) {
        userLogic.deleteAccountById(id);
    }

}
