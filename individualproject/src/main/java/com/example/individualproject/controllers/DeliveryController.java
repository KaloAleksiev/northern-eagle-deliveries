package com.example.individualproject.controllers;

import com.example.individualproject.logic.DeliveryLogic;
import com.example.individualproject.models.Delivery;
import com.example.individualproject.models.User;
import com.example.individualproject.models.requestmodels.DeliveryRequest;
import com.example.individualproject.models.responsemodels.DeliveryResponse;
import com.example.individualproject.repository.DeliveryRepository;
import com.example.individualproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DeliveryController {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeliveryLogic deliveryLogic;

    public DeliveryResponse convertToResponse(Delivery d) {
        DeliveryResponse response = new DeliveryResponse(d.getId(), d.getAddress(), d.getWeight(), d.getSendDate(), d.getPaid(), d.getPrice(), d.getStatus(), d.getSender());
        return response;
    }

    public User convertToUser(Optional<User> u) {
        User user = u.get();
        return user;
    }

    @PostMapping("/mod/newdelivery")
    public void createNewDelivery(@RequestBody DeliveryRequest deliveryRequest) {
        deliveryLogic.createNewDelivery(deliveryRequest);
    }

    @GetMapping("/user/deliveriesbysender/{id}")
    public List<DeliveryResponse> getDeliveriesByUserId(@PathVariable Long id) {
        List<DeliveryResponse> deliveries = deliveryLogic.getDeliveriesByUserId(id);
        return deliveries;
    }

    @GetMapping("/mod/alldeliveries")
    public List<DeliveryResponse> getAllDeliveries() {
        List<DeliveryResponse> deliveries = deliveryLogic.getAllDeliveries();
        return deliveries;
    }

    @PatchMapping("/mod/setSent/{id}")
    public void setStatusSent(@PathVariable Long id) {
        deliveryLogic.setStatusSent(id);
    }

    @PatchMapping("/mod/setDelivered/{id}")
    public void setStatusDelivered(@PathVariable Long id) {
        deliveryLogic.setStatusDelivered(id);
    }

    @DeleteMapping("/admin/deletedelivery/{id}")
    public void deleteDeliveryById(@PathVariable Long id) {
        deliveryLogic.deleteDeliveryById(id);
    }
}
