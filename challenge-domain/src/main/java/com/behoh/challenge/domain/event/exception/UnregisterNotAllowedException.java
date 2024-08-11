package com.behoh.challenge.domain.event.exception;

import com.behoh.challenge.domain.exception.ChallengeException;

public class UnregisterNotAllowedException extends ChallengeException {
    public UnregisterNotAllowedException() {
        super("Unregister not allowed! User already checked in to event!");
    }
}
