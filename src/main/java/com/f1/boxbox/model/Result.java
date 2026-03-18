package com.f1.boxbox.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Min(value = 1, message = "La posición mínima es 1")
    @Max(value = 22, message = "La posición no puede ser mayor a 22")
    private int position;

    @Min(value = 0, message = "Los puntos no pueden ser negativos")
    @Max(value = 25, message = "Los puntos no pueden superar 25")
    private int points;

    @NotNull(message = "El piloto es obligatorio")
    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @NotNull(message = "La carrera es obligatoria")
    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;
}
