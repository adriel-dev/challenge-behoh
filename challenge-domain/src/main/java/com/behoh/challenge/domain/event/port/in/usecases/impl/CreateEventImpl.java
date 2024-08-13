package com.behoh.challenge.domain.event.port.in.usecases.impl;

import com.behoh.challenge.domain.event.exception.InvalidEventDatesException;
import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.domain.event.port.in.usecases.CreateEvent;
import com.behoh.challenge.domain.event.port.out.SaveEventPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateEventImpl implements CreateEvent {

    private final SaveEventPort saveEventPort;

    @Override
    public Event create(Event event) {
        var eventStart = event.getStartDateTime();
        var eventEnd = event.getEndDateTime();
        if(!eventEnd.isAfter(eventStart)) {
            throw new InvalidEventDatesException();
        }
        return saveEventPort.saveEvent(event);
    }

}
