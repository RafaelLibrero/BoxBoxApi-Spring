package com.f1.boxbox.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "races")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long raceId;

    @NotBlank(message = "El nombre de la carrera es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(nullable = false)
    private String raceName;

    private String image;

    private String location;

    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "winner_driver_id")
    private Driver winnerDriver;
}
