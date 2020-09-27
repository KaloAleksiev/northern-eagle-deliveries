package com.example.individualproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CustomerController
{
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/customer")
    public Customer customer(@RequestParam(value = "id", defaultValue = "1") int id)
    {
        return new Customer(id);
    }
}
