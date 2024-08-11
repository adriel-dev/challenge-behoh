package com.behoh.challenge.domain.event.port.in.usecases;

import com.behoh.challenge.domain.event.model.Event;

public interface CreateEvent {
    Event create(Event event);
}
