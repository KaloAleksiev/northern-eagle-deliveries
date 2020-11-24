package com.example.individualproject.controllers;

import com.example.individualproject.models.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String templateGreeting = "Hello, %s!";
    private final AtomicLong counterId = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        return new Greeting(counterId.incrementAndGet(), String.format(templateGreeting, name));
    }
}
