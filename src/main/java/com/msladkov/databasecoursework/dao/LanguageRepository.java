package com.msladkov.databasecoursework.dao;

import com.msladkov.databasecoursework.models.Language;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findById(long id);

    Optional<Language> findByName(String name);

    List<Language> findAll();

    @Transactional
    void deleteById(Long id);
}
