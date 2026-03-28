package com.f1.boxbox.service;

import com.f1.boxbox.dto.DriverSimple;
import com.f1.boxbox.dto.request.RaceRequest;
import com.f1.boxbox.dto.response.RaceResponse;
import com.f1.boxbox.exception.ResourceNotFoundException;
import com.f1.boxbox.model.Driver;
import com.f1.boxbox.model.Race;
import com.f1.boxbox.model.RaceStatus;
import com.f1.boxbox.repository.DriverRepository;
import com.f1.boxbox.repository.RaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceService {

    private final RaceRepository raceRepository;
    private final DriverRepository driverRepository;

    public RaceService(RaceRepository raceRepository, DriverRepository driverRepository) {
        this.raceRepository = raceRepository;
        this.driverRepository = driverRepository;
    }

    public List<RaceResponse> findAll() {
        return raceRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public RaceResponse findById(Long id) {
        Race race = raceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Race not found with id " + id));
        return toResponse(race);
    }

    @Transactional
    public RaceResponse create(RaceRequest request) {
        Race race = toEntity(request);
        Race saved = raceRepository.save(race);
        return toResponse(saved);
    }

    @Transactional
    public RaceResponse update(Long id, RaceRequest request) {
        Race existing = raceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Race not found with id " + id));

        existing.setRaceName(request.getRaceName());
        existing.setImage(request.getImage());
        existing.setLocation(request.getLocation());
        existing.setEndDate(request.getEndDate());
        existing.setWinnerDriver(getDriverByIdNullable(request.getWinnerDriverId()));
        existing.setStatus(parseStatus(request.getStatus()));

        Race updated = raceRepository.save(existing);
        return toResponse(updated);
    }

    @Transactional
    public void delete(Long id) {
        Race race = raceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Race not found with id " + id));
        raceRepository.delete(race);
    }

    private Race toEntity(RaceRequest request) {
        Race race = new Race();
        race.setRaceName(request.getRaceName());
        race.setImage(request.getImage());
        race.setLocation(request.getLocation());
        race.setEndDate(request.getEndDate());
        race.setWinnerDriver(getDriverByIdNullable(request.getWinnerDriverId()));
        race.setStatus(parseStatus(request.getStatus()));
        return race;
    }

    private RaceResponse toResponse(Race race) {
        return RaceResponse.builder()
                .raceId(race.getRaceId())
                .raceName(race.getRaceName())
                .image(race.getImage())
                .location(race.getLocation())
                .endDate(race.getEndDate())
                .winnerDriver(race.getWinnerDriver() != null ? toDriverSimple(race.getWinnerDriver()) : null)
                .status(race.getStatus() != null ? race.getStatus().name() : null)
                .build();
    }

    private Driver getDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id " + id));
    }

    private Driver getDriverByIdNullable(Long id) {
        return id != null ? getDriverById(id) : null;
    }

    private DriverSimple toDriverSimple(Driver driver) {
        return DriverSimple.builder()
                .driverId(driver.getDriverId())
                .driverName(driver.getDriverName())
                .carNumber(driver.getCarNumber())
                .flag(driver.getFlag())
                .imagen(driver.getImagen())
                .points(driver.getPoints())
                .build();
    }

    private RaceStatus parseStatus(String status) {
        if (status == null || status.isEmpty()) return null;
        try {
            return RaceStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid race status: " + status);
        }
    }
}