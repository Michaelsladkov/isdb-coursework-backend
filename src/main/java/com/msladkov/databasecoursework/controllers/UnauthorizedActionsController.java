package com.msladkov.databasecoursework.controllers;

import com.msladkov.databasecoursework.dao.ArtistRepository;
import com.msladkov.databasecoursework.dao.ComposerRepository;
import com.msladkov.databasecoursework.dao.CompositionRepository;
import com.msladkov.databasecoursework.dao.PlayRepository;
import com.msladkov.databasecoursework.models.Artist;
import com.msladkov.databasecoursework.models.Composer;
import com.msladkov.databasecoursework.models.Composition;
import com.msladkov.databasecoursework.models.Play;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class UnauthorizedActionsController {
    private CompositionRepository compositionRepository;
    private PlayRepository playRepository;
    private ComposerRepository composerRepository;
    private ArtistRepository artistRepository;

    @Autowired
    public void setCompositionRepository(CompositionRepository compositionRepository) {
        this.compositionRepository = compositionRepository;
    }

    @Autowired
    public void setPlayRepository(PlayRepository playRepository) {
        this.playRepository = playRepository;
    }

    @Autowired
    public void setComposerRepository(ComposerRepository composerRepository) {
        this.composerRepository = composerRepository;
    }

    @Autowired
    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @CrossOrigin
    @GetMapping("/compositions")
    public ResponseEntity<List<Composition>> getCompositions(@RequestParam(name = "composer", defaultValue = "") String composer,
                                                             @RequestParam(name = "topic", defaultValue = "") String topic) {
        if (!composer.isEmpty()) {
            return new ResponseEntity<>(compositionRepository.findCompositionByComposerNameContaining(composer), HttpStatus.OK);
        }
        if (!topic.isEmpty()) {
            return new ResponseEntity<>(compositionRepository.findCompositionByTopicsName(topic), HttpStatus.OK);
        }
        return new ResponseEntity<>(compositionRepository.findAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/composition/{id}")
    public ResponseEntity<Composition> getCompositionById(@PathVariable("id") String idStr) {
        Optional<Composition> res;
        Optional<Long> id = parseLong(idStr);
        if (id.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        res = compositionRepository.findById(id.get());
        if (res.isPresent()) {
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @GetMapping("compositions/{genre}")
    public ResponseEntity<List<Composition>> getCompositionsByGenre(@PathVariable("genre") String genre) {
        return new ResponseEntity<>(compositionRepository.findCompositionByGenreName(genre), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("plays")
    public ResponseEntity<List<Play>> getPlays(@RequestParam(name = "composition", defaultValue = "") String composition,
                                               @RequestParam(name = "address", defaultValue = "") String siteAddress,
                                               @RequestParam(name = "composer", defaultValue = "") String composer,
                                               @RequestParam(name = "genre", defaultValue = "") String genre,
                                               @RequestParam(name = "artist", defaultValue = "") String artist) {
        if (composition.isEmpty() && siteAddress.isEmpty() && composer.isEmpty() && genre.isEmpty() && artist.isEmpty()) {
            System.out.println("finding all");
            System.out.println(playRepository.findAll().size());
            return new ResponseEntity<>(playRepository.findAll(), HttpStatus.OK);
        }
        Set<Play> resultByComposition = null;
        Set<Play> resultByAddress = null;
        Set<Play> resultByComposer = null;
        Set<Play> resultByGenre = null;
        Set<Play> resultByArtist = null;
        if (!composition.isEmpty()) {
            resultByComposition = new HashSet<>(playRepository.findPlaysByCompositionNameContaining(composition));
        }
        if (!siteAddress.isEmpty()) {
            resultByAddress = new HashSet<>(playRepository.findPlaysBySiteAddressContaining(siteAddress));
        }
        if (!composer.isEmpty()) {
            resultByComposer = new HashSet<>(playRepository.findPlaysByCompositionComposerNameContaining(composer));
        }
        if (!genre.isEmpty()) {
            resultByGenre = new HashSet<>(playRepository.findPlaysByCompositionGenreNameContaining(genre));
        }
        if (!artist.isEmpty()) {
            resultByArtist = new HashSet<>(playRepository.findPlayByArtistsNameContaining(artist));
        }
        Set<Play> resultOverSets[] = new Set[]{
                resultByAddress,
                resultByComposer,
                resultByComposition,
                resultByGenre,
                resultByArtist
        };
        Set<Play> result = new HashSet<>();
        for (Set s : resultOverSets) {
            if (s != null) {
                result = s;
                break;
            }
        }
        for (int i = 0; i < resultOverSets.length; ++i) {
            if (resultOverSets[i] == null) {
                resultOverSets[i] = result;
            }
            result.retainAll(resultOverSets[i]);
        }
        return new ResponseEntity<>(result.stream().toList(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/play/{id}")
    public ResponseEntity<Play> getPlayById(@PathVariable("id") String idStr) {
        Optional<Play> res;
        Optional<Long> id = parseLong(idStr);
        if (id.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        res = playRepository.findById(id.get());
        if (res.isPresent()) {
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @GetMapping("/composer/{id}")
    public ResponseEntity<Composer> getComposerById(@PathVariable("id") String idStr) {
        Optional<Composer> res;
        Optional<Long> id = parseLong(idStr);
        if (id.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        res = composerRepository.findById(id.get());
        if (res.isPresent()) {
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @GetMapping("/artist/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable("id") String idStr) {
        Optional<Artist> res;
        Optional<Long> id = parseLong(idStr);
        if (id.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        res = artistRepository.findById(id.get());
        if (res.isPresent()) {
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @GetMapping("/artists")
    public ResponseEntity<List<Artist>> getArtists(@RequestParam(name = "skill", defaultValue = "") String skill) {
        if (skill.isEmpty()) {
            return ResponseEntity.ok(artistRepository.findAll());
        }
        return ResponseEntity.ok(artistRepository.findBySkillContaining(skill));
    }

    private static Optional<Long> parseLong(String s) {
        try {
            return Optional.of(Long.parseLong(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
