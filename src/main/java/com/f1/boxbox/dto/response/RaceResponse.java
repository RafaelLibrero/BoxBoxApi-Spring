package com.f1.boxbox.dto.response;

import com.f1.boxbox.dto.DriverSimple;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RaceResponse {

    private Long raceId;

    private String raceName;

    private String image;

    private String location;

    private Date endDate;

    private DriverSimple winnerDriver;

    private String status;
}