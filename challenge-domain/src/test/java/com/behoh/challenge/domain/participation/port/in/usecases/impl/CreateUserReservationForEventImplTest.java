package com.behoh.challenge.domain.participation.port.in.usecases.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import com.behoh.challenge.domain.event.exception.EventAlreadyStartedException;
import com.behoh.challenge.domain.event.exception.NoSpotsAvailableException;
import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.domain.event.port.out.FindEventPort;
import com.behoh.challenge.domain.participation.model.Participation;
import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import com.behoh.challenge.domain.participation.port.out.SaveParticipationPort;
import com.behoh.challenge.domain.user.model.User;
import com.behoh.challenge.domain.user.port.out.FindUserPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CreateUserReservationForEventImplTest {

    @Mock
    private SaveParticipationPort saveParticipationPort;

    @Mock
    private FindParticipationPort findParticipationPort;

    @Mock
    private FindEventPort findEventPort;

    @Mock
    private FindUserPort findUserPort;

    @InjectMocks
    private CreateUserReservationForEventImpl createUserReservationForEvent;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReserveUserWhenValid() {
        // given
        final var userId = 1L;
        final var eventId = 1L;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime eventStart = now.plusHours(2);
        LocalDateTime eventEnd = now.plusHours(3);
        var user = new User(userId, "user");
        var event = new Event(eventId, "Test", 5, eventStart, eventEnd);
        var currentlyConfirmedUsers = 3;

        when(findEventPort.findEventById(eventId)).thenReturn(event);
        when(findParticipationPort.countUsersConfirmed(eventId)).thenReturn(currentlyConfirmedUsers);
        when(findUserPort.findUserById(userId)).thenReturn(user);

        // when
        createUserReservationForEvent.reserve(userId, eventId);

        // then
        verify(saveParticipationPort, times(1)).saveParticipation(any(Participation.class));
    }

    @Test
    public void shouldNotReserveUserIfEventAlreadyStarted() {
        // given
        final var userId = 1L;
        final var eventId = 1L;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime eventStart = now.minusHours(1);
        LocalDateTime eventEnd = now.plusHours(1);
        var event = new Event(eventId, "Test", 5, eventStart, eventEnd);
        var currentlyConfirmedUsers = 3;

        when(findEventPort.findEventById(eventId)).thenReturn(event);
        when(findParticipationPort.countUsersConfirmed(eventId)).thenReturn(currentlyConfirmedUsers);

        // when-then
        assertThatThrownBy(() -> createUserReservationForEvent.reserve(userId, eventId))
                .isInstanceOf(EventAlreadyStartedException.class);

        verify(saveParticipationPort, never()).saveParticipation(any(Participation.class));
    }

    @Test
    public void shouldNotReserveUserIfNoAvailableSpots() {
        // given
        final var userId = 1L;
        final var eventId = 1L;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime eventStart = now.plusHours(2);
        LocalDateTime eventEnd = now.plusHours(3);
        var user = new User(userId, "user");
        var event = new Event(eventId, "Test", 5, eventStart, eventEnd);
        var currentlyConfirmedUsers = 5;

        when(findEventPort.findEventById(eventId)).thenReturn(event);
        when(findParticipationPort.countUsersConfirmed(eventId)).thenReturn(currentlyConfirmedUsers);
        when(findUserPort.findUserById(userId)).thenReturn(user);

        // when-then
        assertThatThrownBy(() -> createUserReservationForEvent.reserve(userId, eventId))
                .isInstanceOf(NoSpotsAvailableException.class);

        verify(saveParticipationPort, never()).saveParticipation(any(Participation.class));
    }
}
