package com.f1.boxbox.dto.response;

import com.f1.boxbox.dto.DriverSimple;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TeamResponse {

    private Long teamId;
    private String teamName;
    private String logo;
    private List<DriverSimple> drivers;
    private int points;
    private boolean active;
}
