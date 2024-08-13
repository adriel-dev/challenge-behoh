package com.behoh.challenge.domain.participation.port.in.usecases.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import com.behoh.challenge.domain.event.exception.EventAlreadyStartedException;
import com.behoh.challenge.domain.event.exception.NoSpotsAvailableException;
import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.domain.participation.model.Participation;
import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import com.behoh.challenge.domain.participation.port.out.SaveParticipationPort;
import com.behoh.challenge.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ConfirmUserReservationImplTest {

    @Mock
    private SaveParticipationPort saveParticipationPort;

    @Mock
    private FindParticipationPort findParticipationPort;

    @InjectMocks
    private ConfirmUserReservationImpl confirmUserReservation;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldConfirmUserReservationWhenValid() {
        // given
        final var userId = 1L;
        final var eventId = 1L;
        var now = LocalDateTime.now();
        var eventStart = now.plusHours(2);
        var eventEnd = now.plusHours(3);
        var user = new User(userId, "user");
        var event = new Event(eventId, "Test", 5, eventStart, eventEnd);
        var participation = new Participation(event, user);
        var currentlyConfirmedUsers = 3;

        when(findParticipationPort.findParticipation(userId, eventId)).thenReturn(participation);
        when(findParticipationPort.countUsersConfirmed(eventId)).thenReturn(currentlyConfirmedUsers);

        // when
        confirmUserReservation.confirm(userId, eventId);

        // then
        assertThat(participation.isConfirmed()).isTrue();
        verify(saveParticipationPort, times(1)).updateParticipation(participation);
    }

    @Test
    public void shouldNotConfirmUserReservationIfEventAlreadyStarted() {
        // given
        final var userId = 1L;
        final var eventId = 1L;
        var now = LocalDateTime.now();
        var eventStart = now.minusHours(1);
        var eventEnd = now.plusHours(1);
        var user = new User(userId, "user");
        var event = new Event(eventId, "Test", 5, eventStart, eventEnd);
        var participation = new Participation(event, user);
        var currentlyConfirmedUsers = 3;

        when(findParticipationPort.findParticipation(userId, eventId)).thenReturn(participation);
        when(findParticipationPort.countUsersConfirmed(eventId)).thenReturn(currentlyConfirmedUsers);

        // when-then
        assertThatThrownBy(() -> confirmUserReservation.confirm(userId, eventId))
                .isInstanceOf(EventAlreadyStartedException.class);

        verify(saveParticipationPort, never()).updateParticipation(any(Participation.class));
    }

    @Test
    public void shouldNotConfirmUserReservationIfNoAvailableSpots() {
        // given
        final var userId = 1L;
        final var eventId = 1L;
        var now = LocalDateTime.now();
        var eventStart = now.plusHours(2);
        var eventEnd = now.plusHours(3);
        var user = new User(userId, "user");
        var event = new Event(eventId, "Test", 5, eventStart, eventEnd);
        var participation = new Participation(event, user);
        var currentlyConfirmedUsers = 5;

        when(findParticipationPort.findParticipation(userId, eventId)).thenReturn(participation);
        when(findParticipationPort.countUsersConfirmed(eventId)).thenReturn(currentlyConfirmedUsers);

        // when-then
        assertThatThrownBy(() -> confirmUserReservation.confirm(userId, eventId))
                .isInstanceOf(NoSpotsAvailableException.class);

        verify(saveParticipationPort, never()).updateParticipation(any(Participation.class));
    }
}
