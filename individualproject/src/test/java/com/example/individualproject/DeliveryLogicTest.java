package com.example.individualproject;

import com.example.individualproject.logic.DeliveryLogic;
import com.example.individualproject.models.Delivery;
import com.example.individualproject.models.User;
import com.example.individualproject.models.requestmodels.DeliveryRequest;
import com.example.individualproject.models.responsemodels.DeliveryResponse;
import com.example.individualproject.repository.DeliveryRepository;
import com.example.individualproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeliveryLogicTest {
    @Mock
    DeliveryRepository deliveryRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    DeliveryLogic deliveryLogic;

    List<Delivery> deliveries = new ArrayList();

    @BeforeAll
    public void init() {
        Delivery delivery1 = new Delivery(1L, "Address 1", 1.0, Calendar.getInstance(), "Yes", 1.0, "Registered", new User(1L, "sender1"));
        Delivery delivery2 = new Delivery(2L, "Address 2", 2.0, Calendar.getInstance(), "Yes", 2.0, "Registered", new User(2L, "sender2"));
        Delivery delivery3 = new Delivery(3L, "Address 3", 3.0, Calendar.getInstance(), "Yes", 3.0, "Registered", new User(3L, "sender3"));
        deliveries.add(delivery1);
        deliveries.add(delivery2);
        deliveries.add(delivery3);
    }

    public DeliveryResponse convertToResponse(Delivery d) {
        DeliveryResponse response = new DeliveryResponse(d.getId(), d.getAddress(), d.getWeight(), d.getSendDate(), d.getPaid(), d.getPrice(), d.getStatus(), d.getSender());
        return response;
    }

    public User convertToUser(Optional<User> u) {
        User user = u.get();
        return user;
    }

    @Test
    public void createNewDeliveryTest() {
        DeliveryRequest deliveryRequest = new DeliveryRequest("New Address", 10.0, "No", 15.0, 1L);
        User u = new User();
        when(userRepository.findById(deliveryRequest.getSenderId())).thenReturn(Optional.of(u));
        Delivery delivery = new Delivery(deliveryRequest.getAddress(), deliveryRequest.getWeight(), deliveryRequest.getPaid(), deliveryRequest.getPrice(), convertToUser(userRepository.findById(deliveryRequest.getSenderId())));
        when(deliveryRepository.save(delivery)).thenReturn(delivery);
        boolean success = deliveryLogic.createNewDelivery(deliveryRequest);
        Assert.assertTrue(success);
    }

    @Test
    public void getDeliveriesByUserIdTest() {
        Long id = 1L;
        List<Delivery> deliveryList = new ArrayList<>();
        deliveryList.add(deliveries.get(0));
        when(deliveryRepository.findAllBySenderId(1L)).thenReturn(deliveryList);
        List<DeliveryResponse> success = deliveryLogic.getDeliveriesByUserId(id);
        Assert.assertTrue(success.get(0).getSender().getId() == deliveryList.get(0).getSender().getId());
    }

    @Test
    public void getAllDeliveriesTest() {
        when(deliveryRepository.findAll()).thenReturn(deliveries);
        List<DeliveryResponse> success = deliveryLogic.getAllDeliveries();
        Assert.assertTrue(success.size() == 3);
    }

    @Test
    public void setStatusSentTest() {
        Long id = 1L;
        when(deliveryRepository.findById(id)).thenReturn(Optional.ofNullable(deliveries.get(0)));
        boolean success = deliveryLogic.setStatusSent(id);
        Assert.assertTrue(deliveries.get(0).getStatus() == "Sent");
    }

    @Test
    public void setStatusDeliveredTest() {
        Long id = 1L;
        when(deliveryRepository.findById(id)).thenReturn(Optional.ofNullable(deliveries.get(0)));
        boolean success = deliveryLogic.setStatusDelivered(id);
        Assert.assertTrue(deliveries.get(0).getStatus() == "Delivered");
    }
}
