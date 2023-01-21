package com.msladkov.databasecoursework.dao;

import com.msladkov.databasecoursework.models.Site;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SiteRepository extends JpaRepository<Site, Long> {

    Optional<Site> findById(long id);

    List<Site> findByAddressContaining(String addressPart);

    List<Site> findAll();

    @Transactional
    void deleteById(Long id);
}
