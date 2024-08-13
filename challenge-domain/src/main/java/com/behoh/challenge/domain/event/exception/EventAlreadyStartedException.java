package com.behoh.challenge.domain.event.exception;

import com.behoh.challenge.domain.exception.ChallengeException;

public class EventAlreadyStartedException extends ChallengeException {
    public EventAlreadyStartedException() {
        super("Event already started. Cannot register/reserve user to this event!");
    }
}
