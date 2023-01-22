package com.msladkov.databasecoursework.dao;

import com.msladkov.databasecoursework.models.Topic;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findById(long id);

    Optional<Topic> findByName(String name);

    List<Topic> findAll();

    @Transactional
    void deleteById(Long id);
}
