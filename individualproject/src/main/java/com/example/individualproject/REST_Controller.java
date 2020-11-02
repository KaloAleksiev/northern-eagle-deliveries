package com.example.individualproject;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class REST_Controller {

    @GetMapping("/delivery")
    public Delivery delivery(@RequestParam(value = "id", defaultValue = "1") int id)
    {
        return new Delivery(id);
    }




    @GetMapping("/customer")
    public User customer(@RequestParam(value = "id", defaultValue = "1") int id)
    {
        return new User(id);
    }




    private static final String templateGreeting = "Hello, %s!";
    private final AtomicLong counterId = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        return new Greeting(counterId.incrementAndGet(), String.format(templateGreeting, name));
    }


}
