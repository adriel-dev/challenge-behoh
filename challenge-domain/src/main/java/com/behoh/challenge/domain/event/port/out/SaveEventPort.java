package com.behoh.challenge.domain.event.port.out;

import com.behoh.challenge.domain.event.model.Event;

public interface SaveEventPort {
    Event saveEvent(Event event);
}
