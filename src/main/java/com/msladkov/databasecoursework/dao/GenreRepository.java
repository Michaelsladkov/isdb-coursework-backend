package com.msladkov.databasecoursework.dao;

import com.msladkov.databasecoursework.models.Genre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findById(long id);

    Optional<Genre> findByName(String name);

    List<Genre> findAll();

    @Transactional
    void deleteById(Long id);
}
