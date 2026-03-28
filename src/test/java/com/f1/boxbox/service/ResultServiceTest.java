package com.f1.boxbox.service;

import com.f1.boxbox.dto.request.ResultRequest;
import com.f1.boxbox.dto.response.ResultResponse;
import com.f1.boxbox.exception.ResourceNotFoundException;
import com.f1.boxbox.model.Driver;
import com.f1.boxbox.model.Race;
import com.f1.boxbox.model.Result;
import com.f1.boxbox.repository.DriverRepository;
import com.f1.boxbox.repository.RaceRepository;
import com.f1.boxbox.repository.ResultRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResultServiceTest {

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private RaceRepository raceRepository;

    @InjectMocks
    private ResultService resultService;

    @Test
    void createResultWorks() {
        ResultRequest request = new ResultRequest();
        request.setDriverId(1L);
        request.setRaceId(2L);
        request.setPoints(25);
        request.setPosition(1);

        when(driverRepository.findById(1L)).thenReturn(Optional.of(new Driver()));
        when(raceRepository.findById(2L)).thenReturn(Optional.of(new Race()));
        when(resultRepository.save(any(Result.class))).thenAnswer(invocation -> {
            Result r = invocation.getArgument(0);
            r.setResultId(99L);
            return r;
        });

        ResultResponse response = resultService.create(request);

        assertThat(response.getResultId()).isEqualTo(99L);
        assertThat(response.getPoints()).isEqualTo(25);
    }

    @Test
    void findByIdThrowsWhenMissing() {
        when(resultRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> resultService.findById(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("999");
    }

    @Test
    void findAllReturnsResults() {
        Result r1 = new Result();
        r1.setResultId(1L);
        r1.setPoints(25);
        Result r2 = new Result();
        r2.setResultId(2L);
        r2.setPoints(18);

        when(resultRepository.findAll()).thenReturn(List.of(r1, r2));

        List<ResultResponse> results = resultService.findAll();

        assertThat(results).hasSize(2);
        assertThat(results.get(0).getPoints()).isEqualTo(25);
    }
}