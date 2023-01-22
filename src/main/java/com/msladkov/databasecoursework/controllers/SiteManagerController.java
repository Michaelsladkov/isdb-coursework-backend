package com.msladkov.databasecoursework.controllers;

import com.msladkov.databasecoursework.dto.AddComposerRequest;
import com.msladkov.databasecoursework.dto.AddCompositionRequest;
import com.msladkov.databasecoursework.dto.AddPlayRequest;
import com.msladkov.databasecoursework.dto.RemovePlayRequest;
import com.msladkov.databasecoursework.service.SiteManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class SiteManagerController {
    private SiteManagerService managerService;

    @Autowired
    public void setManagerService(SiteManagerService service) {
        managerService = service;
    }

    @PostMapping("/play")
    public ResponseEntity<String> addPlay(@RequestBody AddPlayRequest request) {
        if(!managerService.checkUserHasRights(request)) {
            return new ResponseEntity<>("Wrong combination of login data and site", HttpStatus.UNAUTHORIZED);
        }
        if (!managerService.addPlay(request.getPlay())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body("Play added");
    }

    @PostMapping("/composer")
    public ResponseEntity<String> addComposer(@RequestBody AddComposerRequest request) {
        if(!managerService.checkUserHasRights(request)) {
            return new ResponseEntity<>("Wrong login data", HttpStatus.UNAUTHORIZED);
        }
        managerService.addComposer(request.getComposerRepresentation());
        return ResponseEntity.ok().body("Composer added");
    }

    @PostMapping("/composition")
    public ResponseEntity<String> addComposition(@RequestBody AddCompositionRequest request) {
        if(!managerService.checkUserHasRights(request)) {
            return new ResponseEntity<>("Wrong login data", HttpStatus.UNAUTHORIZED);
        }
        if (!managerService.addComposition(request.getRepresentation())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body("Composition added");
    }

    @DeleteMapping("/play")
    public ResponseEntity<String> removePlay(@RequestBody RemovePlayRequest request) {
        if(!managerService.checkUserHasRights(request)) {
            return new ResponseEntity<>("Wrong login data", HttpStatus.UNAUTHORIZED);
        }
        managerService.removePlay(request.getPlayId());
        return ResponseEntity.ok("Play removed");
    }
}
