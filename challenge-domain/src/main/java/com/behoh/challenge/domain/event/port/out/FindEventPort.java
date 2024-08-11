package com.behoh.challenge.domain.event.port.out;


import com.behoh.challenge.domain.event.model.Event;

public interface FindEventPort {
    Event findEventById(Long eventId);
}
