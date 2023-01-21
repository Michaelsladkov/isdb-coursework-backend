package com.msladkov.databasecoursework.dao;

import com.msladkov.databasecoursework.models.Play;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.transform.sax.SAXResult;
import java.util.List;
import java.util.Optional;

public interface PlayRepository extends JpaRepository<Play, Long> {

    List<Play> findAll();

    List<Play> findPlaysByCompositionNameContaining(String compositionNamePart);

    List<Play> findPlaysByCompositionComposerNameContaining(String composerNamePart);

    List<Play> findPlaysBySiteAddressContaining(String addressPart);

    List<Play> findPlaysByCompositionGenreNameContaining(String genreNamePart);

    List<Play> findPlayByArtistsNameContaining(String artistNamePart);

    Optional<Play> findById(long id);

    @Transactional
    void deleteById(Long id);
}