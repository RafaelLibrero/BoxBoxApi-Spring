package com.f1.boxbox.controller;

import com.f1.boxbox.dto.request.DriverRequest;
import com.f1.boxbox.dto.response.DriverResponse;
import com.f1.boxbox.service.DriverService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@Tag(name = "Drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public List<DriverResponse> findAll() {
        return driverService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> findById(@PathVariable Long id) {
        DriverResponse driver = driverService.findById(id);
        return ResponseEntity.ok(driver);
    }

    @PostMapping
    public ResponseEntity<DriverResponse> save(@Valid @RequestBody DriverRequest driverRequest) {
        DriverResponse d = driverService.create(driverRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(d);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverResponse> update(@PathVariable Long id,
                                                 @Valid @RequestBody DriverRequest driverRequest) {
        try {
            DriverResponse d = driverService.update(id, driverRequest);
            return ResponseEntity.ok(d);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            driverService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<DriverResponse> searchDrivers(@RequestParam String name) {
        return driverService.searchByName(name);
    }

    @GetMapping("/ranking")
    public List<DriverResponse> getRanking() {
        return driverService.getDriversByPointsDesc();
    }
}