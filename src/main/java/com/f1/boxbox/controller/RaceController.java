package com.f1.boxbox.controller;

import com.f1.boxbox.model.Race;
import com.f1.boxbox.service.RaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/races")
@Tag(name = "Races")
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public List<Race> findAll() {
        return raceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Race> findById(@PathVariable Long id) {
        return raceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Race> create(@Valid @RequestBody Race race) {
        Race r = raceService.create(race);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Race> update(@PathVariable Long id, @Valid @RequestBody Race race) {
        try {
            Race r = raceService.update(id, race);
            return ResponseEntity.ok(r);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            raceService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}