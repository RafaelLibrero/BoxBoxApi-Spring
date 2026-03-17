package com.f1.boxbox.service;

import com.f1.boxbox.model.Driver;
import com.f1.boxbox.repository.DriverRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> findAll() {return driverRepository.findAll();}

    @Transactional
    public Optional<Driver> findById (Long id) { return driverRepository.findById(id); }

    @Transactional
    public Driver create(Driver driver) { return driverRepository.save(driver); }

    @Transactional
    public Driver update(Long id, Driver driver) {
        Driver d = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver Not Found"));

        d.setDriverName(driver.getDriverName());
        d.setCarNumber(driver.getCarNumber());
        d.setTeam(driver.getTeam());
        d.setFlag(driver.getFlag());
        d.setImagen(driver.getImagen());
        d.setPoints(driver.getPoints());

        return driverRepository.save(d);
    }

    @Transactional
    public void delete(Long id) {
        if (!driverRepository.existsById(id)) {
            throw new RuntimeException("Driver Not Found");
        }
        driverRepository.deleteById(id);
    }

}
