package com.behoh.challenge.domain.participation.port.in.usecases.impl;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import com.behoh.challenge.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GetEventRegisteredUsersImplTest {

    @Mock
    private FindParticipationPort findParticipationPort;

    @InjectMocks
    private GetEventRegisteredUsersImpl getEventRegisteredUsers;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnUsersWhenRegistered() {
        // given
        final var eventId = 1L;
        var user1 = new User(1L, "user1");
        var user2 = new User(2L, "user2");
        var registeredUsers = List.of(user1, user2);

        when(findParticipationPort.findAllUsersWithConfirmedRegistration(eventId)).thenReturn(registeredUsers);

        // when
        var result = getEventRegisteredUsers.get(eventId);

        // then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(user1, user2);
    }

    @Test
    public void shouldReturnEmptyListWhenNoUsersRegistered() {
        // given
        final var eventId = 1L;
        when(findParticipationPort.findAllUsersWithConfirmedRegistration(eventId)).thenReturn(List.of());

        // when
        var result = getEventRegisteredUsers.get(eventId);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}
