package com.behoh.domain.event.port.out;

import com.behoh.domain.event.model.Event;

public interface FindEventPort {
    Event findEventById(Long eventId);
}
