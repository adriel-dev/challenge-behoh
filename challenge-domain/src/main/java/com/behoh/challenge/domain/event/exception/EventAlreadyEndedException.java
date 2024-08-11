package com.behoh.challenge.domain.event.exception;

import com.behoh.challenge.domain.exception.ChallengeException;

public class EventAlreadyEndedException extends ChallengeException {
    public EventAlreadyEndedException() {
        super("The event has already ended. Cannot unregister user from event!");
    }
}
