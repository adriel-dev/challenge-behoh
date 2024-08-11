package com.behoh.challenge.domain.event.exception;

import com.behoh.challenge.domain.exception.ChallengeException;

public class NoSpotsAvailableException extends ChallengeException {
    public NoSpotsAvailableException() {
        super("There are no spots available for this event!");
    }
}
