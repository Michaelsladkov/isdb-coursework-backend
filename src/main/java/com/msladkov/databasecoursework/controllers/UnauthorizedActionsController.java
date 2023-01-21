package com.msladkov.databasecoursework.controllers;

import com.msladkov.databasecoursework.dao.CompositionRepository;
import com.msladkov.databasecoursework.models.Composition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UnauthorizedActionsController {
    private CompositionRepository compositionRepository;

    @Autowired
    public void setCompositionRepository(CompositionRepository compositionRepository) {
        this.compositionRepository = compositionRepository;
    }

    @CrossOrigin
    @GetMapping("/compositions")
    public ResponseEntity<List<Composition>> getCompositions(@RequestParam(name = "composer", defaultValue = "") String composer,
                                                             @RequestParam(name = "topic", defaultValue = "") String topic) {
        if (!composer.isEmpty()) {
            return new ResponseEntity<>(compositionRepository.findCompositionByComposerName(composer), HttpStatus.OK);
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
            res = Optional.empty();
        }
        if (res.isPresent()) {
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @GetMapping("compositions/{genre}")
    public ResponseEntity<List<Composition>> getCompositionsByGenre(@PathVariable("genre") String genre) {
        return new ResponseEntity<>(compositionRepository.findCompositionByGenreName(genre), HttpStatus.OK);
    }
}
