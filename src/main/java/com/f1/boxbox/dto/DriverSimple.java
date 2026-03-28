package com.f1.boxbox.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DriverSimple {

    private Long driverId;
    private String driverName;
    private int carNumber;
    private String flag;
    private String imagen;
    private int points;
}