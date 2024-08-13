package com.behoh.challenge.domain.participation.port.in.usecases.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.behoh.challenge.domain.event.exception.UnregisterNotAllowedException;
import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.domain.participation.model.Participation;
import com.behoh.challenge.domain.participation.port.out.DeleteParticipationPort;
import com.behoh.challenge.domain.participation.port.out.FindParticipationPort;
import com.behoh.challenge.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

public class UnregisterUserFromEventImplTest {

    @Mock
    private FindParticipationPort findParticipationPort;

    @Mock
    private DeleteParticipationPort deleteParticipationPort;

    @InjectMocks
    private UnregisterUserFromEventImpl unregisterUserFromEvent;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldUnregisterUserWhenConditionsAreMet() {
        // given
        final var userId = 1L;
        final var eventId = 1L;
        var now = LocalDateTime.now();
        var eventStart = now.plusHours(2L);
        var eventEnd = now.plusHours(5L);
        var user = new User(userId, "user");
        var event = new Event(eventId, "Test", 5, eventStart, eventEnd);
        var participation = new Participation(event, user);
        participation.setCheckInDateTime(null);

        when(findParticipationPort.findParticipation(userId, eventId)).thenReturn(participation);

        // when
        unregisterUserFromEvent.unregister(userId, eventId);

        // then
        verify(deleteParticipationPort, times(1)).deleteParticipation(userId, eventId);
    }

    @Test
    public void shouldNotUnregisterUserIfCheckInDateTimeIsNotNull() {
        // given
        final var userId = 1L;
        final var eventId = 1L;
        var now = LocalDateTime.now();
        var eventStart = now.minusMinutes(30);
        var eventEnd = now.plusHours(3);
        var user = new User(userId, "user");
        var event = new Event(eventId, "Test", 5, eventStart, eventEnd);
        var participation = new Participation(event, user);
        participation.setCheckInDateTime(now);

        when(findParticipationPort.findParticipation(userId, eventId)).thenReturn(participation);

        // when-then
        assertThatThrownBy(() -> unregisterUserFromEvent.unregister(userId, eventId))
                .isInstanceOf(UnregisterNotAllowedException.class);
        verify(deleteParticipationPort, never()).deleteParticipation(anyLong(), anyLong());
    }
}
