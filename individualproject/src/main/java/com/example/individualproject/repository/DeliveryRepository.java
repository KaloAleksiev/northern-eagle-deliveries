package com.example.individualproject.repository;

import java.util.List;
import java.util.Optional;

import com.example.individualproject.models.Delivery;
import com.example.individualproject.models.responsemodels.DeliveryResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findAllBySenderId(Long id);

}
