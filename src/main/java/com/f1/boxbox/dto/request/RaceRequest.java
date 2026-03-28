package com.f1.boxbox.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class RaceRequest {

    @NotBlank(message = "El nombre de la carrera es obligatorio")
    @Size(min = 2, max = 100)
    private String raceName;

    private String image;

    private String location;

    private Date endDate;

    private Long winnerDriverId;

    private String status;
}
