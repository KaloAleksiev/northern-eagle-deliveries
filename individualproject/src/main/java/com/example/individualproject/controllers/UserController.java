package com.example.individualproject.controllers;

import com.example.individualproject.models.Delivery;
import com.example.individualproject.models.ERole;
import com.example.individualproject.models.Role;
import com.example.individualproject.models.User;
import com.example.individualproject.models.responsemodels.DeliveryResponse;
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

    @PatchMapping("/admin/makeemployee/{id}")
    public void makeEmployee(@PathVariable Long id) {
        User user = userRepository.findById(id).get();

        Set<Role> roles = new HashSet<>();

        Role modRole = roleRepository.findByName(ERole.Employee)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(modRole);

        user.setRoles(roles);
        userRepository.save(user);
    }

    @PatchMapping("/admin/makecustomer/{id}")
    public void makeCustomer(@PathVariable Long id) {
        User user = userRepository.findById(id).get();

        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(ERole.Customer)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);
    }

    @PatchMapping("/admin/makeadmin/{id}")
    public void makeAdmin(@PathVariable Long id) {
        User user = userRepository.findById(id).get();

        Set<Role> roles = new HashSet<>();

        Role adminRole = roleRepository.findByName(ERole.Administrator)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(adminRole);

        user.setRoles(roles);
        userRepository.save(user);
    }

    @GetMapping("/mod/getallusers")
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

}
