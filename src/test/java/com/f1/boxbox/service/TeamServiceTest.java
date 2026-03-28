package com.f1.boxbox.service;

import com.f1.boxbox.dto.request.TeamRequest;
import com.f1.boxbox.dto.response.TeamResponse;
import com.f1.boxbox.exception.ResourceNotFoundException;
import com.f1.boxbox.model.Team;
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
class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    @Test
    void createTeamWorks() {
        // ✅ Usamos TeamRequest, no Team
        TeamRequest request = new TeamRequest();
        request.setTeamName("Mercedes");
        request.setPoints(200);
        request.setActive(true);
        request.setLogo("mercedes.png");

        when(teamRepository.save(any(Team.class))).thenAnswer(invocation -> {
            Team saved = invocation.getArgument(0);
            saved.setTeamId(99L);
            return saved;
        });

        TeamResponse response = teamService.create(request);

        assertThat(response.getTeamId()).isEqualTo(99L);
        assertThat(response.getTeamName()).isEqualTo("Mercedes");
        assertThat(response.isActive()).isTrue();
    }

    @Test
    void findByIdThrowsWhenMissing() {
        when(teamRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> teamService.findById(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("999");
    }

    @Test
    void findAllReturnsTeams() {
        Team t1 = new Team();
        t1.setTeamId(1L);
        t1.setTeamName("Red Bull");

        Team t2 = new Team();
        t2.setTeamId(2L);
        t2.setTeamName("Ferrari");

        when(teamRepository.findAll()).thenReturn(List.of(t1, t2));

        List<TeamResponse> teams = teamService.findAll();

        assertThat(teams).hasSize(2);
        assertThat(teams.get(0).getTeamName()).isEqualTo("Red Bull");
    }
}