package com.f1.boxbox.repository;

import com.f1.boxbox.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findByDriverNameContainingIgnoreCase(String name);
    List<Driver> findAllByOrderByPointsDesc();

}
