package com.f1.boxbox.model;

import jakarta.persistence.*;
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

    private String raceName;

    private String image;

    private String location;

    private Date endDate;

    @ManyToOne
    private Driver winnerDriver;
}
