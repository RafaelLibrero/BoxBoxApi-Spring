package com.f1.boxbox.controller;

import com.f1.boxbox.model.Team;
import com.f1.boxbox.service.TeamService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@Tag(name = "Teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> findAll() {
        return teamService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> findById(@PathVariable Long id) {
        return teamService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Team> save(@Valid @RequestBody Team team) {
        Team t = teamService.create(team);
        return ResponseEntity.status(HttpStatus.CREATED).body(t);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> update(@PathVariable Long id, @Valid @RequestBody Team team) {
        try {
            Team t = teamService.update(id, team);
            return ResponseEntity.ok(t);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            teamService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
