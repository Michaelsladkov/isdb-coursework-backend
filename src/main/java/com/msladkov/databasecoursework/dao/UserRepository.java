package com.msladkov.databasecoursework.dao;

import com.msladkov.databasecoursework.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(long id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    @Transactional
    void deleteById(Long id);
}
