package com.msladkov.databasecoursework.dao;

import com.msladkov.databasecoursework.models.PlannedPlay;
import com.msladkov.databasecoursework.models.UserPlannedKey;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<PlannedPlay, UserPlannedKey> {

    List<PlannedPlay> getAllByUserId(long userId);

    @Transactional
    void deleteById(UserPlannedKey id);
}
