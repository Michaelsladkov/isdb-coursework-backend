package com.msladkov.databasecoursework.dao;

import com.msladkov.databasecoursework.models.Artist;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<Artist> findBySkillContaining(String skillPart);

    Optional<Artist> findById(long id);

    List<Artist> findAll();

    @Transactional
    void deleteById(Long id);
}
