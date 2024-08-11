package com.behoh.domain.event.port.in.usecases;

import com.behoh.domain.event.model.Event;

public interface CreateEvent {
    Event create(Event event);
}
