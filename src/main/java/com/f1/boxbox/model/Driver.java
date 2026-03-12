package com.f1.boxbox.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    private String driverName;

    private int carNumber;

    @ManyToOne
    private Team team;

    private String flag;

    private String imagen;

    private int points;
}
