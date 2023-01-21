package com.msladkov.databasecoursework.dao;

import com.msladkov.databasecoursework.models.Season;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeasonRepository extends JpaRepository<Season, Long> {

    List<Season> findAll();

    List<Season> findById(long id);

    @Transactional
    void deleteById(Long id);
}
