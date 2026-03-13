package com.f1.boxbox.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    @NotBlank(message = "El nombre del piloto es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(nullable = false)
    private String driverName;

    @Positive
    private int carNumber;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToMany
    @JoinTable(
            name = "previous_teams",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> previous_teams;

    private String flag;

    private String imagen;

    @PositiveOrZero
    private int points;
}
