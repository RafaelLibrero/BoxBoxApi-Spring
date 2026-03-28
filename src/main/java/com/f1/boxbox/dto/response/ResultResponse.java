package com.f1.boxbox.dto.response;

import com.f1.boxbox.dto.DriverSimple;
import com.f1.boxbox.dto.RaceSimple;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultResponse {

    private Long resultId;

    private int position;
    private int points;

    private DriverSimple driver;

    private RaceSimple race;
}
