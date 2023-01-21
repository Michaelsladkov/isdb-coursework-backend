package com.msladkov.databasecoursework.controllers;

import com.msladkov.databasecoursework.dto.NewUserData;
import com.msladkov.databasecoursework.dto.UserLoginData;
import com.msladkov.databasecoursework.service.AuthenticationService;
import com.msladkov.databasecoursework.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    AuthenticationService authenticationService;

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<String> registerReq(@RequestBody NewUserData userData) {
        if (!Validation.validateEmail(userData.getEmail())) {
            return ResponseEntity.badRequest().body("Invalid email");
        }
        if (userData.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("Empty name is not allowed");
        }
        if (userData.getPassword().length() < 6) {
            return ResponseEntity.badRequest().body("Password should be 6 characters or more");
        }
        if (authenticationService.createUser(userData)) {
            return ResponseEntity.ok().body("User added");
        }
        return ResponseEntity.badRequest().build();
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<String> loginReq(@RequestBody UserLoginData loginData) {
        if (authenticationService.authUser(loginData.getEmail(), loginData.getPassword())) {
            return ResponseEntity.ok("Login success");
        }
        return ResponseEntity.badRequest().body("Login failed");
    }
}
