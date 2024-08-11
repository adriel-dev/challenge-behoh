package com.behoh.domain.event.port.out;

import com.behoh.domain.event.model.Event;

public interface SaveEventPort {
    Event saveEvent(Event event);
    Event updateEvent(Event event);
}
