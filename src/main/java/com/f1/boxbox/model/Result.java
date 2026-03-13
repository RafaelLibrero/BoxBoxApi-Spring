package com.f1.boxbox.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Table(name = "results")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    @Positive(message = "La posición debe ser positiva")
    private int position;

    @PositiveOrZero
    private int points;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;
}
