package com.f1.boxbox.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "teams")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @NotBlank(message = "El nombre del equipo es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(nullable = false)
    private String teamName;

    private String logo;

    @PositiveOrZero
    private int points;

    @Column(nullable = false)
    private boolean active;
}
