package com.example.individualproject;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin
@RestController
public class REST_Controller {
    DataControl dc = new DataControl();


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





    @GetMapping("/getallusers")
    public List<User> GetAllUsers() throws SQLException {
        return dc.GetAllUsersFromDB();
    }




    @GetMapping("/tracker")
    public List<Delivery> GetAllDeliveries() throws SQLException {
        return dc.GetAllDeliveriesFromDB();
    }




    @PostMapping("/newdelivery")
    public void NewDelivery() {
    }





    private static final String templateGreeting = "Hello, %s!";
    private final AtomicLong counterId = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        return new Greeting(counterId.incrementAndGet(), String.format(templateGreeting, name));
    }
}
