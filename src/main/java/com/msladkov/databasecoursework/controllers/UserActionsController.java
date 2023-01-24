package com.msladkov.databasecoursework.controllers;

import com.msladkov.databasecoursework.dto.AddPlanRequest;
import com.msladkov.databasecoursework.dto.AddWishRequest;
import com.msladkov.databasecoursework.dto.NotificationRepresentation;
import com.msladkov.databasecoursework.dto.UserLoginData;
import com.msladkov.databasecoursework.models.PlannedPlay;
import com.msladkov.databasecoursework.models.Wishes;
import com.msladkov.databasecoursework.service.AuthenticationService;
import com.msladkov.databasecoursework.service.UserActionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserActionsController {
    private AuthenticationService authenticationService;
    private UserActionsService userActionsService;

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Autowired
    public void setUserActionsService(UserActionsService userActionsService) {
        this.userActionsService = userActionsService;
    }

    @PostMapping("/notifications")
    public ResponseEntity<List<NotificationRepresentation>> showNotifications(@RequestBody UserLoginData loginData) {
        if (!authenticationService.authUser(loginData.getEmail(), loginData.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok().body(userActionsService.showNotifications(loginData));
    }

    @PostMapping("/wish")
    public ResponseEntity<String> addWish(@RequestBody AddWishRequest request) {
        if (!authenticationService.authUser(request.getLoginData())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!userActionsService.addWish(request)) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/my_wishes")
    public ResponseEntity<List<Wishes>> getWishes(@RequestBody UserLoginData loginData) {
        if (!authenticationService.authUser(loginData)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(userActionsService.getWishes(loginData));
    }

    @PostMapping("/plan")
    public ResponseEntity<String> addPlan(@RequestBody AddPlanRequest request) {
        if (!authenticationService.authUser(request.getLoginData())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!userActionsService.addPlan(request)) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok("Plan added");
    }

    @PostMapping("/my_plans")
    public ResponseEntity<List<PlannedPlay>> getPlans(@RequestBody UserLoginData loginData) {
        if (!authenticationService.authUser(loginData)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(userActionsService.getPlans(loginData));
    }
}
