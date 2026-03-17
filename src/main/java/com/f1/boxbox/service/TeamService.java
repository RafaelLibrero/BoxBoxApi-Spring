package com.f1.boxbox.service;

import com.f1.boxbox.model.Team;
import com.f1.boxbox.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> findAll() { return teamRepository.findAll(); }

    public Optional<Team> findById(Long id) { return teamRepository.findById(id); }

    @Transactional
    public Team create(Team team) {
        return teamRepository.save(team);
    }

    @Transactional
    public Team update(Long id, Team team) {
        Team t = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        t.setTeamName(team.getTeamName());
        t.setLogo(team.getLogo());
        t.setPoints(team.getPoints());

        return teamRepository.save(t);
    }

    @Transactional
    public void delete(Long id) {
        if  (teamRepository.existsById(id)) {
            throw new RuntimeException("Team not found");
        }
        teamRepository.deleteById(id);
    }
}
