package com.f1.boxbox.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResultRequest {

    @Min(value = 1, message = "La posición mínima es 1")
    @Max(value = 22, message = "La posición no puede ser mayor a 22")
    private int position;

    @Min(value = 0, message = "Los puntos no pueden ser negativos")
    @Max(value = 25, message = "Los puntos no pueden superar 25")
    private int points;

    @NotNull(message = "El piloto es obligatorio")
    private Long driverId;

    @NotNull(message = "La carrera es obligatoria")
    private Long raceId;
}
