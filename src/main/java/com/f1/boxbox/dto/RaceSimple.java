package com.f1.boxbox.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RaceSimple {

    private Long raceId;
    private String raceName;
    private String image;
    private String location;
    private Date endDate;
    private String status;
}
