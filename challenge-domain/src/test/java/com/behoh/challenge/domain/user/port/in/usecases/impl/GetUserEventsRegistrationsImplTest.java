package com.behoh.challenge.domain.user.port.in.usecases.impl;

import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class GetUserEventsRegistrationsImplTest {

    @Mock
    private FindParticipationPort findParticipationPort;

    @InjectMocks
    private GetUserEventsRegistrationsImpl getUserEventsRegistrations;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCallFindAllUsersEventsRegistrationsWithCorrectUserId() {
        // given
        final var userId = 1L;

        // when
        getUserEventsRegistrations.get(userId);

        // then
        verify(findParticipationPort, times(1)).findAllUsersEventsRegistrations(userId);
    }

}