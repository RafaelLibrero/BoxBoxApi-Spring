package com.f1.boxbox.service;

import com.f1.boxbox.model.Race;
import com.f1.boxbox.repository.RaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RaceService {

    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<Race> findAll() {
        return raceRepository.findAll();
    }

    public Optional<Race> findById(Long id) {
        return raceRepository.findById(id);
    }

    @Transactional
    public Race create(Race race) { return raceRepository.save(race); }

    @Transactional
    public Race update(Long id, Race race) {
        Race r = raceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Race Not Found"));

        r.setRaceName(race.getRaceName());
        r.setImage(race.getImage());
        r.setLocation(race.getLocation());
        r.setEndDate(race.getEndDate());
        r.setWinnerDriver(race.getWinnerDriver());

        return raceRepository.save(r);
    }

    @Transactional
    public void delete(Long id) {
        if (!raceRepository.existsById(id)) {
            throw new RuntimeException("Race Not Found");
        }
        raceRepository.deleteById(id);
    }
}
