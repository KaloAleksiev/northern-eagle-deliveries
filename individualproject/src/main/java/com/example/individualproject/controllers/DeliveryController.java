package com.example.individualproject.controllers;

import com.example.individualproject.datasources.DataControl;
import com.example.individualproject.interfaces.DataSource;
import com.example.individualproject.models.Delivery;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DeliveryController {
    DataSource dc = new DataControl();

    @GetMapping("/delivery")
    public Delivery delivery(@RequestParam(value = "id", defaultValue = "1") int id)
    {
        return new Delivery(id);
    }

    @GetMapping("/deliveries")
    public List<Delivery> GetAllDeliveries() throws SQLException {
        return dc.GetAllDeliveriesFromDB();
    }

    @GetMapping("/tracker/{id}")
    public String GetDeliveryStatusByID(@PathVariable(value = "id") int id) throws SQLException {
        System.out.println(dc.GetDeliveryStatusByID(id));
        return dc.GetDeliveryStatusByID(id);
    }
}
