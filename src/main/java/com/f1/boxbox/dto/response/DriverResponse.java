package com.f1.boxbox.dto.response;

import com.f1.boxbox.dto.TeamSimple;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DriverResponse {

    private Long driverId;

    private String driverName;

    private int carNumber;

    private TeamSimple team;

    private List<TeamSimple> previousTeams;

    private String flag;

    private String imagen;

    private int points;
}