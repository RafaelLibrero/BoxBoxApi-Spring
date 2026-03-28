package com.f1.boxbox.service;

import com.f1.boxbox.dto.TeamSimple;
import com.f1.boxbox.dto.request.DriverRequest;
import com.f1.boxbox.dto.response.DriverResponse;
import com.f1.boxbox.exception.ResourceNotFoundException;
import com.f1.boxbox.model.Driver;
import com.f1.boxbox.model.Team;
import com.f1.boxbox.repository.DriverRepository;
import com.f1.boxbox.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final TeamRepository teamRepository;

    public DriverService(DriverRepository driverRepository, TeamRepository teamRepository) {
        this.driverRepository = driverRepository;
        this.teamRepository = teamRepository;
    }

    public List<DriverResponse> findAll() {
        return driverRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<DriverResponse> searchByName(String name) {
        return driverRepository.findByDriverNameContainingIgnoreCase(name)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<DriverResponse> getDriversByPointsDesc() {
        return driverRepository.findAllByOrderByPointsDesc()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public DriverResponse findById(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver no encontrado con id: " + id));
        return toResponse(driver);
    }

    @Transactional
    public DriverResponse create(DriverRequest request) {
        Driver driver = toEntity(request);
        Driver saved = driverRepository.save(driver);
        return toResponse(saved);
    }

    @Transactional
    public DriverResponse update(Long id, DriverRequest request) {
        Driver existing = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver no encontrado con id: " + id));

        existing.setDriverName(request.getDriverName());
        existing.setCarNumber(request.getCarNumber());

        if (request.getTeamId() != null) {
            Team team = teamRepository.findById(request.getTeamId())
                    .orElseThrow(() -> new ResourceNotFoundException("Team no encontrado con id: " + request.getTeamId()));
            existing.setTeam(team);
        } else {
            existing.setTeam(null);
        }

        if (request.getPreviousTeamIds() != null) {
            List<Team> previousTeams = request.getPreviousTeamIds().stream()
                    .map(teamId -> teamRepository.findById(teamId)
                            .orElseThrow(() -> new ResourceNotFoundException("Team no encontrado con id: " + teamId)))
                    .collect(Collectors.toList());
            existing.setPrevious_teams(previousTeams);
        } else {
            existing.setPrevious_teams(null);
        }

        existing.setFlag(request.getFlag());
        existing.setImagen(request.getImagen());
        existing.setPoints(request.getPoints());

        Driver updated = driverRepository.save(existing);
        return toResponse(updated);
    }

    @Transactional
    public void delete(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver no encontrado con id: " + id));
        driverRepository.delete(driver);
    }

    private DriverResponse toResponse(Driver driver) {
        return DriverResponse.builder()
                .driverId(driver.getDriverId())
                .driverName(driver.getDriverName())
                .carNumber(driver.getCarNumber())
                .team(toTeamSimple(driver.getTeam()))
                .previousTeams(driver.getPrevious_teams() != null ?
                        driver.getPrevious_teams().stream()
                                .map(this::toTeamSimple)
                                .collect(Collectors.toList()) : null)
                .flag(driver.getFlag())
                .imagen(driver.getImagen())
                .points(driver.getPoints())
                .build();
    }

    private Driver toEntity(DriverRequest request) {
        Driver driver = new Driver();
        driver.setDriverName(request.getDriverName());
        driver.setCarNumber(request.getCarNumber());

        if (request.getTeamId() != null) {
            Team team = teamRepository.findById(request.getTeamId())
                    .orElseThrow(() -> new ResourceNotFoundException("Team no encontrado con id: " + request.getTeamId()));
            driver.setTeam(team);
        }

        if (request.getPreviousTeamIds() != null) {
            List<Team> previousTeams = request.getPreviousTeamIds().stream()
                    .map(teamId -> teamRepository.findById(teamId)
                            .orElseThrow(() -> new ResourceNotFoundException("Team no encontrado con id: " + teamId)))
                    .collect(Collectors.toList());
            driver.setPrevious_teams(previousTeams);
        }

        driver.setFlag(request.getFlag());
        driver.setImagen(request.getImagen());
        driver.setPoints(request.getPoints());

        return driver;
    }

    private TeamSimple toTeamSimple(Team team) {
        if (team == null) return null;
        return TeamSimple.builder()
                .teamId(team.getTeamId())
                .teamName(team.getTeamName())
                .logo(team.getLogo())
                .points(team.getPoints())
                .build();
    }
}