package com.behoh.challenge.domain.event.exception;

import com.behoh.challenge.domain.exception.NotFoundException;

public class EventNotFoundException extends NotFoundException {
    public EventNotFoundException(Long eventId) {
        super("No Event with ID ["+eventId+"] was found!");
    }
}
