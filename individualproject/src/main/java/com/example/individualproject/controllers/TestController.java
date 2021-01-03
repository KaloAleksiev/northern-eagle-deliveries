package com.example.individualproject.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/customer")
    @PreAuthorize("hasRole('Customer') or hasRole('Employee') or hasRole('Administrator')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/employee")
    @PreAuthorize("hasRole('Employee')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/administrator")
    @PreAuthorize("hasRole('Administrator')")
    public String adminAccess() {
        return "Admin Board.";
    }
}