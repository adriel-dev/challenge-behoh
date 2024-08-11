package com.behoh.domain.event.usecases;

import com.behoh.domain.event.model.Event;

public interface CreateEvent {
    Event create(Event event);
}
