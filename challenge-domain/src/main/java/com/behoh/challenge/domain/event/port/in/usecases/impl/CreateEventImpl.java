package com.behoh.challenge.domain.event.port.in.usecases.impl;

import com.behoh.challenge.domain.event.exception.InvalidEventDatesException;
import com.behoh.challenge.domain.event.model.Event;
import com.behoh.challenge.domain.event.port.in.usecases.CreateEvent;
import com.behoh.challenge.domain.event.port.out.SaveEventPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateEventImpl implements CreateEvent {

    private SaveEventPort saveEventPort;

    @Override
    public Event create(Event event) {
        var eventStart = event.getStartDateTime();
        var eventEnd = event.getEndDateTime();
        if(eventStart.isAfter(eventEnd) || eventStart.isEqual(eventEnd)) {
            throw new InvalidEventDatesException();
        }
        return saveEventPort.saveEvent(event);
    }

}
