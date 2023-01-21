package com.msladkov.databasecoursework.service;

import com.msladkov.databasecoursework.dao.UserRepository;
import com.msladkov.databasecoursework.dto.NewUserData;
import com.msladkov.databasecoursework.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class AuthenticationService {

    private UserRepository userRepository;
    private MessageDigest digest;

    public AuthenticationService() throws NoSuchAlgorithmException {
        digest = MessageDigest.getInstance("SHA-256");
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(NewUserData newUser) {
        if (checkUserExists(newUser.getEmail())) {
            return false;
        }
        String passwordHash = new String(digest.digest(newUser.getPassword().getBytes(StandardCharsets.UTF_8)));
        userRepository.save(new User(newUser.getName(), newUser.getEmail(), passwordHash));
        return true;
    }

    public boolean checkUserExists(String email) {
        Optional existingUser = userRepository.findByEmail(email);
        return existingUser.isPresent();
    }

    public boolean authUser(String email, String password) {
        String passwordHash = new String(digest.digest(password.getBytes(StandardCharsets.UTF_8)));
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return false;
        }
        return user.get().getPasswordHash().equals(passwordHash);
    }
}
