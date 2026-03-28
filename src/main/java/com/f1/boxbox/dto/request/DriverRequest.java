package com.f1.boxbox.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class DriverRequest {

    @NotBlank(message = "El nombre del piloto es obligatorio")
    @Size(min = 2, max = 100)
    private String driverName;

    @Positive
    private int carNumber;

    private Long teamId;

    private List<Long> previousTeamIds;

    private String flag;

    private String imagen;

    @PositiveOrZero
    private int points;
}
