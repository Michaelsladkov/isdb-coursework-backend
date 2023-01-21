package com.msladkov.databasecoursework.controllers;

import com.msladkov.databasecoursework.dao.CompositionRepository;
import com.msladkov.databasecoursework.dao.PlayRepository;
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

    @Autowired
    public void setCompositionRepository(CompositionRepository compositionRepository) {
        this.compositionRepository = compositionRepository;
    }

    @Autowired
    public void setPlayRepository(PlayRepository playRepository) {
        this.playRepository = playRepository;
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
        try {
            long id = Long.parseLong(idStr);
            res = compositionRepository.findById(id);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
        try {
            long id = Long.parseLong(idStr);
            res = playRepository.findById(id);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (res.isPresent()) {
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
