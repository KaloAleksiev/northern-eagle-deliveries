package com.example.individualproject.controllers;

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

    public DeliveryResponse convertToResponse(Delivery d) {
        DeliveryResponse response = new DeliveryResponse(d.getId(), d.getAddress(), d.getWeight(), d.getSendDate(), d.getPaid(), d.getStatus(), d.getSender());
        return response;
    }

    public User convertToUser(Optional<User> u) {
        User user = u.get();
        return user;
    }

    @PostMapping("/mod/newdelivery")
    public void createNewDelivery(@RequestBody DeliveryRequest deliveryRequest) {
        Delivery delivery = new Delivery(deliveryRequest.getAddress(), deliveryRequest.getWeight(), deliveryRequest.getPaid(), convertToUser(userRepository.findById(deliveryRequest.getSenderId())));
        deliveryRepository.save(delivery);
    }

    @GetMapping("/user/deliveriesbysender/{id}")
    public List<DeliveryResponse> getDeliveriesByUserId(@PathVariable Long id) {
        List<Delivery> dels = deliveryRepository.findAllBySender(id);
        List<DeliveryResponse> deliveries = new ArrayList<>();
        for (Delivery d : dels) {
            deliveries.add(convertToResponse(d));
        }
        return deliveries;
    }

    @GetMapping("/mod/alldeliveries")
    public List<DeliveryResponse> getAllDeliveries() {
        List<Delivery> dels = deliveryRepository.findAll();
        List<DeliveryResponse> deliveries = new ArrayList<>();
        for (Delivery d : dels) {
            deliveries.add(convertToResponse(d));
        }
        return deliveries;
    }

    @GetMapping("/mod/tracker/{id}")
    public DeliveryResponse getDeliveryById(@PathVariable Long id) {
        Optional<Delivery> del = deliveryRepository.findById(id);
        Delivery delivery = new Delivery();
        if (!del.isEmpty()) {
            delivery = del.get();
        }
        return convertToResponse(delivery);
    }

    @PatchMapping("/mod/setSent/{id}")
    public void setStatusSent(@PathVariable Long id) {
        Delivery delivery = deliveryRepository.findById(id).get();
        delivery.setStatus("Sent");
        deliveryRepository.save(delivery);
    }

    @PatchMapping("/mod/setDelivered/{id}")
    public void setStatusDelivered(@PathVariable Long id) {
        Delivery delivery = deliveryRepository.findById(id).get();
        delivery.setStatus("Delivered");
        deliveryRepository.save(delivery);
    }

    @DeleteMapping("/admin/deletedelivery/{id}")
    public void deleteDeliveryById(@PathVariable Long id) {
        deliveryRepository.deleteById(id);
    }
}
