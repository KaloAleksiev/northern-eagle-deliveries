package com.example.individualproject;

import com.example.individualproject.logic.UserLogic;
import com.example.individualproject.models.ERole;
import com.example.individualproject.models.Role;
import com.example.individualproject.models.User;
import com.example.individualproject.repository.RoleRepository;
import com.example.individualproject.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.when;

import java.util.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserLogicTest {
    @Mock
    RoleRepository roleRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserLogic userLogic;

    List<User> users = new ArrayList();

    @BeforeAll
    public void init() {
        Role customerRole = new Role(ERole.ROLE_Customer);
        Role employeeRole = new Role(ERole.ROLE_Employee);
        Role administratorRole = new Role(ERole.ROLE_Administrator);

        Set<Role> roles1 = new HashSet<>();
        roles1.add(customerRole);
        User user1 = new User(1L, roles1);
        users.add(user1);

        Set<Role> roles2 = new HashSet<>();
        roles2.add(employeeRole);
        User user2 = new User(2L, roles2);
        users.add(user2);

        Set<Role> roles3 = new HashSet<>();
        roles3.add(administratorRole);
        User user3 = new User(3L, roles3);
        users.add(user3);
    }

    @Test
    public void makeEmployeeTest() {
        Long id = 1L;
        Role employeeRole = new Role(ERole.ROLE_Employee);
        Set<Role> roles = new HashSet<>();
        roles.add(employeeRole);
        User user = new User(id, roles);
        when(userRepository.findById(id)).thenReturn(Optional.of(users.get(0)));
        when(roleRepository.findByName(ERole.ROLE_Employee)).thenReturn(Optional.of(employeeRole));
        when(userRepository.save(user)).thenReturn(user);
        boolean success = userLogic.makeEmployee(id);
        Assert.assertTrue(success);
    }

    @Test
    public void makeCustomerTest() {
        Long id = 2L;
        Role customerRole = new Role(ERole.ROLE_Customer);
        Set<Role> roles = new HashSet<>();
        roles.add(customerRole);
        User user = new User(id, roles);
        when(userRepository.findById(id)).thenReturn(Optional.of(users.get(1)));
        when(roleRepository.findByName(ERole.ROLE_Customer)).thenReturn(Optional.of(customerRole));
        when(userRepository.save(user)).thenReturn(user);
        boolean success = userLogic.makeCustomer(id);
        Assert.assertTrue(success);
    }

    @Test
    public void makeAdminTest() {
        Long id = 3L;
        Role adminRole = new Role(ERole.ROLE_Administrator);
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        User user = new User(id, roles);
        when(userRepository.findById(id)).thenReturn(Optional.of(users.get(2)));
        when(roleRepository.findByName(ERole.ROLE_Administrator)).thenReturn(Optional.of(adminRole));
        when(userRepository.save(user)).thenReturn(user);
        boolean success = userLogic.makeAdmin( id);
        Assert.assertTrue(success);
    }

    @Test
    public void getAllUsersTest() {
        when(userRepository.findAll()).thenReturn(users);
        List<User> success = userLogic.getAllUsers();
        Assert.assertTrue(success.size() == 3);
    }
}
