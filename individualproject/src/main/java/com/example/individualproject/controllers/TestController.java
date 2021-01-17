package com.example.individualproject.controllers;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/board")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Northern Eagle Deliveries";
    }
}