package com.example.individualproject.logic;

import com.example.individualproject.models.Delivery;
import com.example.individualproject.models.User;
import com.example.individualproject.models.requestmodels.DeliveryRequest;
import com.example.individualproject.models.responsemodels.DeliveryResponse;
import com.example.individualproject.repository.DeliveryRepository;
import com.example.individualproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeliveryLogic {
    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    UserRepository userRepository;

    public DeliveryResponse convertToResponse(Delivery d) {
        DeliveryResponse response = new DeliveryResponse(d.getId(), d.getAddress(), d.getWeight(), d.getSendDate(), d.getPaid(), d.getPrice(), d.getStatus(), d.getSender());
        return response;
    }

    public User convertToUser(Optional<User> u) {
        User user = u.get();
        return user;
    }

    public boolean createNewDelivery(DeliveryRequest deliveryRequest) {
        Delivery delivery = new Delivery(deliveryRequest.getAddress(), deliveryRequest.getWeight(), deliveryRequest.getPaid(), deliveryRequest.getPrice(), convertToUser(userRepository.findById(deliveryRequest.getSenderId())));
        try {
            deliveryRepository.save(delivery);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public List<DeliveryResponse> getDeliveriesByUserId(Long id) {
        List<Delivery> dels = deliveryRepository.findAllBySenderId(id);
        List<DeliveryResponse> deliveries = new ArrayList<>();
        for (Delivery d : dels) {
            deliveries.add(convertToResponse(d));
        }
        return deliveries;
    }

    public List<DeliveryResponse> getAllDeliveries() {
        List<Delivery> dels = deliveryRepository.findAll();
        List<DeliveryResponse> deliveries = new ArrayList<>();
        for (Delivery d : dels) {
            deliveries.add(convertToResponse(d));
        }
        return deliveries;
    }

    public boolean setStatusSent(Long id) {
        Delivery delivery = deliveryRepository.findById(id).get();
        delivery.setStatus("Sent");
        try {
            deliveryRepository.save(delivery);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public boolean setStatusDelivered(Long id) {
        Delivery delivery = deliveryRepository.findById(id).get();
        delivery.setStatus("Delivered");
        try {
            deliveryRepository.save(delivery);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public boolean deleteDeliveryById(Long id) {
        try {
            deliveryRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
