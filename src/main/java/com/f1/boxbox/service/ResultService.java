package com.f1.boxbox.service;

import com.f1.boxbox.dto.DriverSimple;
import com.f1.boxbox.dto.RaceSimple;
import com.f1.boxbox.dto.request.ResultRequest;
import com.f1.boxbox.dto.response.ResultResponse;
import com.f1.boxbox.exception.ResourceNotFoundException;
import com.f1.boxbox.model.Driver;
import com.f1.boxbox.model.Race;
import com.f1.boxbox.model.Result;
import com.f1.boxbox.repository.DriverRepository;
import com.f1.boxbox.repository.RaceRepository;
import com.f1.boxbox.repository.ResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final DriverRepository driverRepository;
    private final RaceRepository raceRepository;

    public ResultService(ResultRepository resultRepository,
                         DriverRepository driverRepository,
                         RaceRepository raceRepository) {
        this.resultRepository = resultRepository;
        this.driverRepository = driverRepository;
        this.raceRepository = raceRepository;
    }

    public List<ResultResponse> findAll() {
        return resultRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ResultResponse findById(Long id) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result no encontrado con id: " + id));
        return toResponse(result);
    }

    @Transactional
    public ResultResponse create(ResultRequest request) {
        Result result = toEntity(request);
        Result saved = resultRepository.save(result);
        return toResponse(saved);
    }

    @Transactional
    public ResultResponse update(Long id, ResultRequest request) {
        Result existing = resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result no encontrado con id: " + id));

        existing.setDriver(getDriverById(request.getDriverId()));
        existing.setRace(getRaceById(request.getRaceId()));
        existing.setPoints(request.getPoints());
        existing.setPosition(request.getPosition());

        Result updated = resultRepository.save(existing);
        return toResponse(updated);
    }

    @Transactional
    public void delete(Long id) {
        Result existing = resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result no encontrado con id: " + id));
        resultRepository.delete(existing);
    }

    private Result toEntity(ResultRequest request) {
        Result result = new Result();
        result.setDriver(getDriverById(request.getDriverId()));
        result.setRace(getRaceById(request.getRaceId()));
        result.setPoints(request.getPoints());
        result.setPosition(request.getPosition());
        return result;
    }

    private ResultResponse toResponse(Result result) {
        return ResultResponse.builder()
                .resultId(result.getResultId())
                .position(result.getPosition())
                .points(result.getPoints())
                .driver(result.getDriver() != null ? toDriverSimple(result.getDriver()) : null)
                .race(result.getRace() != null ? toRaceSimple(result.getRace()) : null)
                .build();
    }

    private Driver getDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver no encontrado con id: " + id));
    }

    private Race getRaceById(Long id) {
        return raceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Race no encontrada con id: " + id));
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

    private RaceSimple toRaceSimple(Race race) {
        return RaceSimple.builder()
                .raceId(race.getRaceId())
                .raceName(race.getRaceName())
                .image(race.getImage())
                .location(race.getLocation())
                .endDate(race.getEndDate())
                .status(race.getStatus() != null ? race.getStatus().name() : null)
                .build();
    }
}