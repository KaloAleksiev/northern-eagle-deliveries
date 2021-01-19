package com.example.individualproject.controllers;

import javax.validation.Valid;

import com.example.individualproject.logic.AuthLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.individualproject.payload.request.LoginRequest;
import com.example.individualproject.payload.request.SignupRequest;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthController {
    @Autowired
    AuthLogic authLogic;

    @PostMapping("/auth/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authLogic.authenticateUser(loginRequest);
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authLogic.registerUser(signUpRequest);
    }
}
