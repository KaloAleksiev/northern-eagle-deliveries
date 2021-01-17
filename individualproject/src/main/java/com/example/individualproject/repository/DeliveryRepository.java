package com.example.individualproject.repository;

import java.util.List;

import com.example.individualproject.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findAllBySenderId(Long id);

}
