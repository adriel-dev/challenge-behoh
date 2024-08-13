package com.behoh.challenge.domain.event.port.in.usecases.impl;

import com.behoh.challenge.domain.event.exception.InvalidEventDatesException;
import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.domain.event.port.out.SaveEventPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateEventImplTest {

    @Mock
    private SaveEventPort saveEventPort;

    @InjectMocks
    private CreateEventImpl createEvent;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateEvent() {
        //given
        var event = new Event(
                null, "Test", 5,
                LocalDateTime.of(2024, 8, 13, 10, 0),
                LocalDateTime.of(2024, 8, 13, 12, 0)
        );

        event.setId(1L);
        when(saveEventPort.saveEvent(any(Event.class))).thenReturn(event);

        //when
        var result = createEvent.create(event);

        //then
        assertThat(result).isNotNull();
        verify(saveEventPort, times(1)).saveEvent(event);
    }

    @Test
    public void shouldGetInvalidEventDatesException() {
        //given
        var event = new Event(
                null, "Test", 5,
                LocalDateTime.of(2024, 8, 13, 13, 0),
                LocalDateTime.of(2024, 8, 13, 12, 0)
        );

        //when-then
        assertThatThrownBy(() -> createEvent.create(event))
                .isInstanceOf(InvalidEventDatesException.class);

        verify(saveEventPort, never()).saveEvent(any(Event.class));
    }

}