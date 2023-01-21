package com.msladkov.databasecoursework.dao;

import com.msladkov.databasecoursework.models.Composer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComposerRepository extends JpaRepository<Composer, Long> {

    Optional<Composer> findById(long id);

    @Transactional
    void deleteById(Long id);
}
