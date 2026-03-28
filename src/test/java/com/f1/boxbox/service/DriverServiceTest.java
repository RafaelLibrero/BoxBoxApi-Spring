package com.f1.boxbox.service;

import com.f1.boxbox.dto.request.DriverRequest;
import com.f1.boxbox.dto.response.DriverResponse;
import com.f1.boxbox.exception.ResourceNotFoundException;
import com.f1.boxbox.model.Driver;
import com.f1.boxbox.model.Team;
import com.f1.boxbox.repository.DriverRepository;
import com.f1.boxbox.repository.TeamRepository;
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
class DriverServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private TeamRepository teamRepository; // ✅ Agregado

    @InjectMocks
    private DriverService driverService;

    @Test
    void createDriverWorks() {
        DriverRequest request = new DriverRequest();
        request.setDriverName("Lewis Hamilton");
        request.setCarNumber(44);
        request.setTeamId(1L);
        request.setFlag("GB");
        request.setImagen("lewis.png");
        request.setPoints(120);

        Team team = new Team();
        team.setTeamId(1L);
        team.setTeamName("Mercedes");
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        when(driverRepository.save(any(Driver.class))).thenAnswer(invocation -> {
            Driver d = invocation.getArgument(0);
            d.setDriverId(99L);
            return d;
        });

        DriverResponse response = driverService.create(request);

        assertThat(response.getDriverId()).isEqualTo(99L);
        assertThat(response.getDriverName()).isEqualTo("Lewis Hamilton");
        assertThat(response.getTeam().getTeamName()).isEqualTo("Mercedes");
    }

    @Test
    void findByIdThrowsWhenMissing() {
        when(driverRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> driverService.findById(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("999");
    }

    @Test
    void findAllReturnsDrivers() {
        Driver driver1 = new Driver();
        driver1.setDriverId(1L);
        driver1.setDriverName("Max Verstappen");

        Driver driver2 = new Driver();
        driver2.setDriverId(2L);
        driver2.setDriverName("Charles Leclerc");

        when(driverRepository.findAll()).thenReturn(List.of(driver1, driver2));

        List<DriverResponse> drivers = driverService.findAll();

        assertThat(drivers).hasSize(2);
        assertThat(drivers.get(0).getDriverName()).isEqualTo("Max Verstappen");
    }
}