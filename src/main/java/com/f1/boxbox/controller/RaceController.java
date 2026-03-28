package com.f1.boxbox.controller;

import com.f1.boxbox.dto.request.RaceRequest;
import com.f1.boxbox.dto.response.RaceResponse;
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
    public List<RaceResponse> findAll() {
        return raceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RaceResponse> findById(@PathVariable Long id) {
        RaceResponse race = raceService.findById(id);
        return ResponseEntity.ok(race);
    }

    @PostMapping
    public ResponseEntity<RaceResponse> save(@Valid @RequestBody RaceRequest raceRequest) {
        RaceResponse r = raceService.create(raceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RaceResponse> update(@PathVariable Long id,
                                               @Valid @RequestBody RaceRequest raceRequest) {
        try {
            RaceResponse r = raceService.update(id, raceRequest);
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