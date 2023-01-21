package com.msladkov.databasecoursework.controllers;

import com.msladkov.databasecoursework.dao.CompositionRepository;
import com.msladkov.databasecoursework.dao.SeasonRepository;
import com.msladkov.databasecoursework.dao.SiteRepository;
import com.msladkov.databasecoursework.dao.UserRepository;
import com.msladkov.databasecoursework.dto.AddSeasonRequest;
import com.msladkov.databasecoursework.dto.AddSiteRequest;
import com.msladkov.databasecoursework.dto.UpdateUserRequest;
import com.msladkov.databasecoursework.dto.UserLoginData;
import com.msladkov.databasecoursework.models.Season;
import com.msladkov.databasecoursework.models.Site;
import com.msladkov.databasecoursework.models.User;
import com.msladkov.databasecoursework.models.UserRole;
import com.msladkov.databasecoursework.service.AuthenticationService;
import com.msladkov.databasecoursework.util.Convertation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminActionsController {

    private AuthenticationService authenticationService;
    private UserRepository userRepository;
    private SiteRepository siteRepository;
    private SeasonRepository seasonRepository;
    private CompositionRepository compositionRepository;

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setSiteRepository(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Autowired
    public void setSeasonRepository(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    @Autowired
    public void setCompositionRepository(CompositionRepository compositionRepository) {
        this.compositionRepository = compositionRepository;
    }

    @GetMapping("/users_list")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/update_role")
    public ResponseEntity<String> updateUserRole(@RequestBody UpdateUserRequest req) {
        if (!authenticationService.authUser(req.getEmail(), req.getPassword())) {
            return ResponseEntity.badRequest().body("Wrong email or password");
        }
        User admin = userRepository.findByEmail(req.getEmail()).get();
        if (admin.getRole() != UserRole.ADMIN) {
            return new ResponseEntity<String>("only admin can do that", HttpStatus.UNAUTHORIZED);
        }
        Optional<User> toUpdate = userRepository.findByEmail(req.getUserEmailToUpdate());
        if (toUpdate.isEmpty()) {
            return new ResponseEntity<String>("No such user " + req.getUserEmailToUpdate(), HttpStatus.NOT_FOUND);
        }
        toUpdate.get().setRole(req.getNewRole());
        userRepository.save(toUpdate.get());
        return new ResponseEntity<String>("User successfully updated", HttpStatus.OK);
    }

    @PostMapping("/add_site")
    public ResponseEntity<String> addSite(@RequestBody AddSiteRequest req) {
        if (!authenticationService.authUser(req.getLoginData().getEmail(), req.getLoginData().getPassword())) {
            return ResponseEntity.badRequest().body("Wrong email or password");
        }
        User admin = userRepository.findByEmail(req.getLoginData().getEmail()).get();
        if (admin.getRole() != UserRole.ADMIN) {
            return new ResponseEntity<String>("only admin can do that", HttpStatus.UNAUTHORIZED);
        }
        Optional<User> manager = userRepository.findById(req.getSite().getManagerId());
        if (manager.isEmpty()) {
            return new ResponseEntity<String>("No such user", HttpStatus.NOT_FOUND);
        }
        Site newSite = new Site(req.getSite(), manager.get());
        siteRepository.save(newSite);
        if (manager.get().getRole() == UserRole.USER) {
            manager.get().setRole(UserRole.SITE_MANAGER);
            userRepository.save(manager.get());
        }
        return new ResponseEntity<String>("Site added successfully", HttpStatus.OK);
    }

    @PostMapping("/add_season")
    public ResponseEntity<String> addSeason(@RequestBody AddSeasonRequest req) {
        if (!authenticationService.authUser(req.getLoginData().getEmail(), req.getLoginData().getPassword())) {
            return ResponseEntity.badRequest().body("Wrong email or password");
        }
        User admin = userRepository.findByEmail(req.getLoginData().getEmail()).get();
        if (admin.getRole() != UserRole.ADMIN) {
            return new ResponseEntity<String>("only admin can do that", HttpStatus.UNAUTHORIZED);
        }
        Season newSeason = new Season(req.getSeason());
        seasonRepository.save(newSeason);
        return ResponseEntity.ok().body("Season added");
    }

    @DeleteMapping("/delete_user/{id}")
    public ResponseEntity<String> removeUser(@PathVariable("id") String idStr,
                                             @RequestBody UserLoginData adminRepresentation) {
        Optional<Long> id = Convertation.parseLong(idStr);
        if (id.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (!authenticationService.authUser(adminRepresentation.getEmail(), adminRepresentation.getPassword())) {
            return ResponseEntity.badRequest().body("Wrong email or password");
        }
        User admin = userRepository.findByEmail(adminRepresentation.getEmail()).get();
        if (admin.getRole() != UserRole.ADMIN) {
            return new ResponseEntity<String>("only admin can do that", HttpStatus.UNAUTHORIZED);
        }
        userRepository.deleteById(id.get());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/season/{id}")
    public ResponseEntity<String> removeSeason(@PathVariable("id") String idStr,
                                               @RequestBody UserLoginData adminRepresentation) {
        Optional<Long> id = Convertation.parseLong(idStr);
        if (id.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (!authenticationService.authUser(adminRepresentation.getEmail(), adminRepresentation.getPassword())) {
            return ResponseEntity.badRequest().body("Wrong email or password");
        }
        User admin = userRepository.findByEmail(adminRepresentation.getEmail()).get();
        if (admin.getRole() != UserRole.ADMIN) {
            return new ResponseEntity<String>("only admin can do that", HttpStatus.UNAUTHORIZED);
        }
        seasonRepository.deleteById(id.get());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/composition/{id}")
    public ResponseEntity<String> removeComposition(@PathVariable("id") String idStr,
                                               @RequestBody UserLoginData adminRepresentation) {
        Optional<Long> id = Convertation.parseLong(idStr);
        if (id.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (!authenticationService.authUser(adminRepresentation.getEmail(), adminRepresentation.getPassword())) {
            return ResponseEntity.badRequest().body("Wrong email or password");
        }
        User admin = userRepository.findByEmail(adminRepresentation.getEmail()).get();
        if (admin.getRole() != UserRole.ADMIN) {
            return new ResponseEntity<String>("only admin can do that", HttpStatus.UNAUTHORIZED);
        }
        compositionRepository.deleteById(id.get());
        return ResponseEntity.ok().build();
    }
}
