package com.behoh.challenge.domain.event.exception;

import com.behoh.challenge.domain.exception.NotFoundException;

import java.util.UUID;

public class EventNotFoundException extends NotFoundException {
    public EventNotFoundException(UUID eventId) {
        super("No Event with ID ["+eventId+"] was found!");
    }
}
