package com.f1.boxbox.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamSimple {

    private Long teamId;
    private String teamName;
    private String logo;
    private int points;
    private boolean active;
}
