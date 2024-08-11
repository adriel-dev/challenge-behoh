package com.behoh.challenge.domain.event.exception;

import com.behoh.challenge.domain.exception.ChallengeException;

public class CheckInAlreadyPerformedException extends ChallengeException {
    public CheckInAlreadyPerformedException() {
        super("Cannot check in user to event. Check in has already been performed!");
    }
}
