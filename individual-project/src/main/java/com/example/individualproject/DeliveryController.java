package com.example.individualproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryController
{
    @GetMapping("/delivery")
    public Delivery delivery(@RequestParam(value = "id", defaultValue = "1") int id)
    {
        return new Delivery(id);
    }
}
