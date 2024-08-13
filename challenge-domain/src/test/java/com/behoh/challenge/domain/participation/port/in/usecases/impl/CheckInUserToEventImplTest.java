package com.behoh.challenge.domain.participation.port.in.usecases.impl;

import com.behoh.challenge.domain.event.exception.CheckInAlreadyPerformedException;
import com.behoh.challenge.domain.event.exception.CheckInNotAllowedException;
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

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;


class CheckInUserToEventImplTest {

    @Mock
    private FindParticipationPort findParticipationPort;

    @Mock
    private SaveParticipationPort saveParticipationPort;

    @InjectMocks
    private CheckInUserToEventImpl checkInUserToEvent;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCheckInUser() {
        // given
        final var userId = 1L;
        final var eventId = 1L;
        var now = LocalDateTime.now();
        var eventStart = now.plusMinutes(5L);
        var eventEnd = now.plusHours(3L);
        var user = new User(userId, "user");
        var event = new Event(eventId, "Test", 5, eventStart, eventEnd);
        var participation = new Participation(event, user, null, null, true);
        when(findParticipationPort.findParticipation(userId, eventId)).thenReturn(participation);

        // when
        checkInUserToEvent.checkIn(userId, eventId);

        // then
        assertThat(participation.getCheckInDateTime()).isNotNull();
        verify(saveParticipationPort, times(1)).updateParticipation(participation);
    }

    @Test
    public void shouldNotCheckInUserIfAlreadyCheckedIn() {
        // given
        final var userId = 1L;
        final var eventId = 1L;
        var now = LocalDateTime.now();
        var eventStart = now.minusHours(1L);
        var eventEnd = now.plusHours(3L);
        var user = new User(userId, "user");
        var event = new Event(eventId, "Test", 5, eventStart, eventEnd);
        var participation = new Participation(event, user, now, null, true);
        when(findParticipationPort.findParticipation(userId, eventId)).thenReturn(participation);

        // when-then
        assertThatThrownBy(() -> checkInUserToEvent.checkIn(userId, eventId))
                .isInstanceOf(CheckInAlreadyPerformedException.class);
        verify(saveParticipationPort, never()).updateParticipation(any(Participation.class));
    }

    @Test
    public void shouldNotCheckInUserIfNotAllowedBeforeEventStart() {
        // given
        final var userId = 1L;
        final var eventId = 1L;
        var now = LocalDateTime.now();
        var eventStart = now.plusHours(3L);
        var eventEnd = now.plusHours(5L);
        var user = new User(userId, "user");
        var event = new Event(eventId, "Test", 5, eventStart, eventEnd);
        var participation = new Participation(event, user, null, null, true);

        when(findParticipationPort.findParticipation(userId, eventId)).thenReturn(participation);

        // when-then
        assertThatThrownBy(() -> checkInUserToEvent.checkIn(userId, eventId))
                .isInstanceOf(CheckInNotAllowedException.class);
        assertThat(participation.getCheckInDateTime()).isNull();
        verify(saveParticipationPort, never()).updateParticipation(any(Participation.class));
    }

    @Test
    public void shouldNotCheckInUserIfNotAllowedAfterEventEnd() {
        // given
        final var userId = 1L;
        final var eventId = 1L;
        var now = LocalDateTime.now();
        var eventStart = now.minusHours(3L);
        var eventEnd = now.minusHours(5L);
        var user = new User(userId, "user");
        var event = new Event(eventId, "Test", 5, eventStart, eventEnd);
        var participation = new Participation(event, user, null, null, true);

        when(findParticipationPort.findParticipation(userId, eventId)).thenReturn(participation);

        // when-then
        assertThatThrownBy(() -> checkInUserToEvent.checkIn(userId, eventId))
                .isInstanceOf(CheckInNotAllowedException.class);
        assertThat(participation.getCheckInDateTime()).isNull();
        verify(saveParticipationPort, never()).updateParticipation(any(Participation.class));
    }

}