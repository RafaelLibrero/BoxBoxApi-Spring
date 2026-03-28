package com.f1.boxbox.service;

import com.f1.boxbox.dto.DriverSimple;
import com.f1.boxbox.dto.request.TeamRequest;
import com.f1.boxbox.dto.response.TeamResponse;
import com.f1.boxbox.exception.ResourceNotFoundException;
import com.f1.boxbox.model.Team;
import com.f1.boxbox.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamResponse> findAll() {
        return teamRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public TeamResponse findById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team no encontrado con id: " + id));
        return toResponse(team);
    }

    @Transactional
    public TeamResponse create(TeamRequest request) {
        Team team = toEntity(request);
        Team saved = teamRepository.save(team);
        return toResponse(saved);
    }

    @Transactional
    public TeamResponse update(Long id, TeamRequest request) {
        Team existing = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team no encontrado con id: " + id));

        existing.setTeamName(request.getTeamName());
        existing.setLogo(request.getLogo());
        existing.setPoints(request.getPoints());
        existing.setActive(request.isActive());

        Team updated = teamRepository.save(existing);
        return toResponse(updated);
    }

    @Transactional
    public void delete(Long id) {
        Team existing = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team no encontrado con id: " + id));
        teamRepository.delete(existing);
    }

    private Team toEntity(TeamRequest request) {
        Team team = new Team();
        team.setTeamName(request.getTeamName());
        team.setLogo(request.getLogo());
        team.setPoints(request.getPoints());
        team.setActive(request.isActive());
        return team;
    }

    private TeamResponse toResponse(Team team) {
        return TeamResponse.builder()
                .teamId(team.getTeamId())
                .teamName(team.getTeamName())
                .logo(team.getLogo())
                .points(team.getPoints())
                .active(team.isActive())
                .drivers(team.getDrivers() != null ?
                        team.getDrivers().stream()
                                .map(this::toDriverSimple)
                                .collect(Collectors.toList())
                        : null)
                .build();
    }

    private DriverSimple toDriverSimple(com.f1.boxbox.model.Driver driver) {
        return DriverSimple.builder()
                .driverId(driver.getDriverId())
                .driverName(driver.getDriverName())
                .carNumber(driver.getCarNumber())
                .flag(driver.getFlag())
                .imagen(driver.getImagen())
                .points(driver.getPoints())
                .build();
    }
}