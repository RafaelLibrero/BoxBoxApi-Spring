package com.f1.boxbox.service;

import com.f1.boxbox.dto.request.RaceRequest;
import com.f1.boxbox.dto.response.RaceResponse;
import com.f1.boxbox.exception.ResourceNotFoundException;
import com.f1.boxbox.model.Race;
import com.f1.boxbox.repository.RaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RaceServiceTest {

    @Mock
    private RaceRepository raceRepository;

    @InjectMocks
    private RaceService raceService;

    @Test
    void createRaceWorks() {
        RaceRequest request = new RaceRequest();
        request.setRaceName("GP Monaco");
        request.setLocation("Monte Carlo");
        request.setEndDate(new Date());
        request.setWinnerDriverId(null);
        request.setStatus("SCHEDULED");

        when(raceRepository.save(any(Race.class))).thenAnswer(invocation -> {
            Race saved = invocation.getArgument(0);
            saved.setRaceId(55L);
            return saved;
        });

        RaceResponse response = raceService.create(request);

        assertThat(response.getRaceId()).isEqualTo(55L);
        assertThat(response.getRaceName()).isEqualTo("GP Monaco");
    }

    @Test
    void findByIdThrowsWhenMissing() {
        when(raceRepository.findById(123L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> raceService.findById(123L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("123");
    }

    @Test
    void findAllReturnsRaces() {
        Race race1 = new Race();
        race1.setRaceId(1L);
        race1.setRaceName("GP España");

        Race race2 = new Race();
        race2.setRaceId(2L);
        race2.setRaceName("GP Francia");

        when(raceRepository.findAll()).thenReturn(List.of(race1, race2));

        List<RaceResponse> races = raceService.findAll();

        assertThat(races).hasSize(2);
        assertThat(races.get(0).getRaceName()).isEqualTo("GP España");
    }
}