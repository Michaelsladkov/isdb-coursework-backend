package com.msladkov.databasecoursework.dao;

import com.msladkov.databasecoursework.models.Composition;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface CompositionRepository extends JpaRepository<Composition, Long> {
    List<Composition> findCompositionByComposerName(String composerName);

    Optional<Composition> findById(long id);

    List<Composition> findAll();

    List<Composition> findCompositionByTopicsName(String topic);

    List<Composition> findCompositionByGenreName(String genreName);

    @Transactional
    void deleteById(Long id);
}
