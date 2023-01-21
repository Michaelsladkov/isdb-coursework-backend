package com.msladkov.databasecoursework.dao;

import com.msladkov.databasecoursework.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findById(long id);

    Optional<User> findByEmail(String email);

    @Transactional
    void deleteById(Long id);
}
