package com.example.individualproject.logic;

import com.example.individualproject.models.ERole;
import com.example.individualproject.models.Role;
import com.example.individualproject.models.User;
import com.example.individualproject.repository.RoleRepository;
import com.example.individualproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserLogic {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public boolean makeEmployee(Long id) {
        User user = userRepository.findById(id).get();
        Set<Role> roles = new HashSet<>();
        Role modRole = roleRepository.findByName(ERole.ROLE_Employee)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(modRole);
        user.setRoles(roles);
        try {
            userRepository.save(user);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean makeCustomer(Long id) {
        User user = userRepository.findById(id).get();
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_Customer)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        try {
            userRepository.save(user);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean makeAdmin(Long id) {
        User user = userRepository.findById(id).get();
        Set<Role> roles = new HashSet<>();
        Role adminRole = roleRepository.findByName(ERole.ROLE_Administrator)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(adminRole);
        user.setRoles(roles);
        try {
            userRepository.save(user);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public boolean deleteAccountById(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
